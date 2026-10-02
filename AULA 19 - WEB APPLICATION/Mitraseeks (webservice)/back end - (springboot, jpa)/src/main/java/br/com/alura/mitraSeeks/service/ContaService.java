package br.com.alura.mitraSeeks.service;

import br.com.alura.mitraSeeks.dto.ContaDTO;
import br.com.alura.mitraSeeks.dto.SessaoDTO;
import br.com.alura.mitraSeeks.model.Sessao;
import br.com.alura.mitraSeeks.model.Usuario;
import br.com.alura.mitraSeeks.model.Usuario.FinalidadeCodigo;
import br.com.alura.mitraSeeks.repository.SessaoRepository;
import br.com.alura.mitraSeeks.repository.UsuarioRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ContaService {

    private static final Pattern NOME_USUARIO = Pattern.compile("^[A-Za-z0-9_.]{3,20}$");
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private static final Pattern FOTO = Pattern.compile("^data:image/(png|jpeg|webp);base64,[A-Za-z0-9+/=]+$");
    private static final int MINUTOS_CODIGO = 15;
    private static final int MAX_TENTATIVAS_CODIGO = 5;
    private static final int DIAS_SESSAO = 30;

    private final BCryptPasswordEncoder criptografia = new BCryptPasswordEncoder();
    private final SecureRandom aleatorio = new SecureRandom();
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private SessaoRepository sessoes;

    @Autowired
    private EmailService email;

    // ---------- cadastro e confirmação ----------

    @Transactional
    public void cadastrar(String nomeUsuario, String emailInformado, String senha) {
        nomeUsuario = obrigatorio(nomeUsuario, "Informe o nome de usuário").trim();
        String emailNormalizado = obrigatorio(emailInformado, "Informe o email").trim().toLowerCase();
        validarNomeUsuario(nomeUsuario);
        validarEmail(emailNormalizado);
        validarSenha(senha);

        Optional<Usuario> comMesmoEmail = usuarios.findByEmailIgnoreCase(emailNormalizado);
        if (comMesmoEmail.isPresent() && comMesmoEmail.get().isEmailConfirmado()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma conta com esse email. Entre ou redefina a senha");
        }

        Optional<Usuario> comMesmoNome = usuarios.findByUserNameIgnoreCase(nomeUsuario);
        if (comMesmoNome.isPresent() && !comMesmoNome.get().getEmail().equals(emailNormalizado)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse nome de usuário já está em uso");
        }

        // Cadastro ainda não confirmado com o mesmo email: reaproveita e manda um código novo
        Usuario usuario = comMesmoEmail.orElseGet(() -> new Usuario(null, emailNormalizado, null));
        usuario.setUserName(nomeUsuario);
        usuario.setSenhaHash(criptografia.encode(senha));
        enviarNovoCodigo(usuario, FinalidadeCodigo.CONFIRMACAO);
    }

    // Sem @Transactional: a tentativa/código salvo precisa ficar gravado mesmo quando lança exceção
    public SessaoDTO confirmarEmail(String emailInformado, String codigo) {
        Usuario usuario = buscarPorEmail(emailInformado)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código inválido ou expirado"));
        validarCodigo(usuario, codigo, FinalidadeCodigo.CONFIRMACAO);
        usuario.setEmailConfirmado(true);
        usuario.limparCodigo();
        usuarios.save(usuario);
        return abrirSessao(usuario);
    }

    @Transactional
    public void reenviarCodigoConfirmacao(String emailInformado) {
        buscarPorEmail(emailInformado)
                .filter(u -> !u.isEmailConfirmado())
                .ifPresent(u -> enviarNovoCodigo(u, FinalidadeCodigo.CONFIRMACAO));
    }

    // ---------- login e sessão ----------

    // Sem @Transactional: a tentativa/código salvo precisa ficar gravado mesmo quando lança exceção
    public SessaoDTO entrar(String login, String senha) {
        login = obrigatorio(login, "Informe o usuário ou email").trim();
        obrigatorio(senha, "Informe a senha");

        Optional<Usuario> encontrado = login.contains("@")
                ? usuarios.findByEmailIgnoreCase(login)
                : usuarios.findByUserNameIgnoreCase(login);

        Usuario usuario = encontrado
                .filter(u -> criptografia.matches(senha, u.getSenhaHash()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha incorretos"));

        if (!usuario.isEmailConfirmado()) {
            enviarNovoCodigo(usuario, FinalidadeCodigo.CONFIRMACAO);
            throw new EmailNaoConfirmadoException(usuario.getEmail());
        }
        return abrirSessao(usuario);
    }

    public Usuario usuarioDoToken(String cabecalhoAuthorization) {
        String token = extrairToken(cabecalhoAuthorization);
        Sessao sessao = sessoes.findById(token)
                .filter(s -> !s.expirada())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sessão expirada. Entre novamente"));
        return sessao.getUsuario();
    }

    public void sair(String cabecalhoAuthorization) {
        sessoes.deleteById(extrairToken(cabecalhoAuthorization));
    }

    public void sairDeTodosOsDispositivos(Usuario usuario) {
        sessoes.deleteByUsuario(usuario);
    }

    // ---------- senha ----------

    @Transactional
    public void esqueciSenha(String emailInformado) {
        buscarPorEmail(emailInformado)
                .filter(u -> !u.isAdmin())
                .ifPresent(u -> enviarNovoCodigo(u, FinalidadeCodigo.REDEFINICAO));
    }

    // Sem @Transactional: a tentativa/código salvo precisa ficar gravado mesmo quando lança exceção
    public SessaoDTO redefinirSenha(String emailInformado, String codigo, String novaSenha) {
        validarSenha(novaSenha);
        Usuario usuario = buscarPorEmail(emailInformado)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código inválido ou expirado"));
        exigirQueNaoSejaAdmin(usuario);
        validarCodigo(usuario, codigo, FinalidadeCodigo.REDEFINICAO);

        usuario.setSenhaHash(criptografia.encode(novaSenha));
        usuario.setEmailConfirmado(true); // quem recebeu o código provou que o email é dele
        usuario.limparCodigo();
        usuarios.save(usuario);

        // Senha nova: derruba as sessões antigas e abre uma nova só para quem redefiniu
        sessoes.deleteByUsuario(usuario);
        return abrirSessao(usuario);
    }

    @Transactional
    public void alterarSenha(Usuario usuario, String senhaAtual, String novaSenha) {
        exigirQueNaoSejaAdmin(usuario);
        if (senhaAtual == null || !criptografia.matches(senhaAtual, usuario.getSenhaHash())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha atual está incorreta");
        }
        validarSenha(novaSenha);
        if (criptografia.matches(novaSenha, usuario.getSenhaHash())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nova senha precisa ser diferente da atual");
        }
        usuario.setSenhaHash(criptografia.encode(novaSenha));
        usuarios.save(usuario);
    }

    // ---------- perfil ----------

    public ContaDTO obterConta(Usuario usuario) {
        return converter(usuario);
    }

    @Transactional
    public ContaDTO alterarFoto(Usuario usuario, String foto) {
        if (foto == null || foto.isBlank()) {
            usuario.setFoto(null);
        } else {
            if (foto.length() > 700_000 || !FOTO.matcher(foto).matches()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Envie uma imagem PNG, JPG ou WEBP de até 500 KB");
            }
            usuario.setFoto(foto);
        }
        return converter(usuarios.save(usuario));
    }

    @Transactional
    public ContaDTO salvarPreferencias(Usuario usuario, Map<String, Object> preferencias) {
        try {
            String json = mapper.writeValueAsString(preferencias == null ? Map.of() : preferencias);
            if (json.length() > 5000) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preferências grandes demais");
            }
            usuario.setPreferencias(json);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preferências inválidas");
        }
        return converter(usuarios.save(usuario));
    }

    // ---------- auxiliares ----------

    // A senha do admin é a do .env (ADMIN_SENHA) e volta a ela a cada inicialização do back
    private void exigirQueNaoSejaAdmin(Usuario usuario) {
        if (usuario.isAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "A senha do admin é definida no .env do back (ADMIN_SENHA)");
        }
    }

    private void enviarNovoCodigo(Usuario usuario, FinalidadeCodigo finalidade) {
        String codigo = String.format("%06d", aleatorio.nextInt(1_000_000));
        usuario.definirCodigo(codigo, finalidade, LocalDateTime.now().plusMinutes(MINUTOS_CODIGO));
        usuarios.save(usuario);
        email.enviarCodigo(usuario, codigo, finalidade);
    }

    private void validarCodigo(Usuario usuario, String codigo, FinalidadeCodigo finalidade) {
        if (usuario.getCodigo() == null || usuario.getFinalidadeCodigo() != finalidade
                || usuario.getCodigoExpiraEm().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código inválido ou expirado. Peça um novo código");
        }
        if (usuario.getTentativasCodigo() >= MAX_TENTATIVAS_CODIGO) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Muitas tentativas. Peça um novo código");
        }
        boolean confere = codigo != null && MessageDigest.isEqual(
                usuario.getCodigo().getBytes(StandardCharsets.UTF_8),
                codigo.trim().getBytes(StandardCharsets.UTF_8));
        if (!confere) {
            usuario.registrarTentativaCodigo();
            usuarios.save(usuario);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código incorreto");
        }
    }

    private SessaoDTO abrirSessao(Usuario usuario) {
        byte[] bytes = new byte[32];
        aleatorio.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        sessoes.save(new Sessao(token, usuario, LocalDateTime.now().plusDays(DIAS_SESSAO)));
        return new SessaoDTO(token, converter(usuario));
    }

    private String extrairToken(String cabecalho) {
        if (cabecalho == null || !cabecalho.startsWith("Bearer ") || cabecalho.length() <= 7) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Entre na sua conta para continuar");
        }
        return cabecalho.substring(7).trim();
    }

    private Optional<Usuario> buscarPorEmail(String emailInformado) {
        if (emailInformado == null || emailInformado.isBlank()) {
            return Optional.empty();
        }
        return usuarios.findByEmailIgnoreCase(emailInformado.trim());
    }

    private ContaDTO converter(Usuario usuario) {
        Map<String, Object> preferencias = Map.of();
        if (usuario.getPreferencias() != null) {
            try {
                preferencias = mapper.readValue(usuario.getPreferencias(), new TypeReference<>() {});
            } catch (Exception ignorado) {
                // preferências corrompidas voltam ao padrão
            }
        }
        return new ContaDTO(usuario.getUserName(), usuario.getEmail(), usuario.getFoto(), preferencias, usuario.getCriadoEm(), usuario.isAdmin());
    }

    private void validarNomeUsuario(String nomeUsuario) {
        if (!NOME_USUARIO.matcher(nomeUsuario).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O nome de usuário deve ter de 3 a 20 caracteres: letras, números, ponto ou _");
        }
    }

    private void validarEmail(String email) {
        if (email.length() > 120 || !EMAIL.matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe um email válido");
        }
    }

    private void validarSenha(String senha) {
        if (senha == null || senha.length() < 8 || senha.length() > 72
                || !senha.matches(".*[A-Za-z].*") || !senha.matches(".*\\d.*")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A senha deve ter de 8 a 72 caracteres, com pelo menos uma letra e um número");
        }
    }

    private String obrigatorio(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagem);
        }
        return valor;
    }
}
