package br.com.alura.mitraSeeks.config;

import br.com.alura.mitraSeeks.model.Usuario;
import br.com.alura.mitraSeeks.repository.SessaoRepository;
import br.com.alura.mitraSeeks.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

/**
 * Garante que exista uma conta de admin toda vez que o back sobe, sem passar pelo cadastro
 * nem pela confirmação de email. Usuário, email e senha vêm do .env:
 *   ADMIN_USUARIO=admin  ADMIN_EMAIL=admin@mitraseeks.local  ADMIN_SENHA=<senha>
 * O .env é a fonte da verdade: mudou a senha lá, ela passa a valer no próximo start.
 */
@Component
public class AdminInicializador implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminInicializador.class);

    @Value("${ADMIN_USUARIO:admin}")
    private String nomeUsuario;

    @Value("${ADMIN_EMAIL:admin@mitraseeks.local}")
    private String email;

    @Value("${ADMIN_SENHA:}")
    private String senha;

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private SessaoRepository sessoes;

    private final BCryptPasswordEncoder criptografia = new BCryptPasswordEncoder();

    @Override
    public void run(ApplicationArguments args) {
        String nome = nomeUsuario.trim();
        String emailAdmin = email.trim().toLowerCase();

        if (senha.isBlank()) {
            // Sem ADMIN_SENHA no .env: gera uma senha só para esta execução e mostra no console
            byte[] bytes = new byte[12];
            new SecureRandom().nextBytes(bytes);
            senha = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
            log.warn("ADMIN_SENHA não definida no .env. Senha temporária do admin '{}': {}", nome, senha);
        }

        Optional<Usuario> existente = usuarios.findFirstByAdminTrue();

        // Não promove uma conta comum a admin só porque ela tem o mesmo nome/email
        boolean conflito = usuarios.findByUserNameIgnoreCase(nome)
                .or(() -> usuarios.findByEmailIgnoreCase(emailAdmin))
                .filter(u -> !u.isAdmin())
                .isPresent();
        if (conflito) {
            log.error("Não foi possível criar o admin: já existe um usuário comum com o nome '{}' ou o email '{}'. "
                    + "Troque ADMIN_USUARIO/ADMIN_EMAIL no .env", nome, emailAdmin);
            return;
        }

        Usuario admin = existente.orElseGet(() -> new Usuario(nome, emailAdmin, criptografia.encode(senha)));
        admin.setUserName(nome);
        admin.setEmail(emailAdmin);
        admin.setAdmin(true);
        admin.setEmailConfirmado(true);

        boolean senhaMudou = !criptografia.matches(senha, admin.getSenhaHash());
        if (senhaMudou) {
            admin.setSenhaHash(criptografia.encode(senha));
        }
        usuarios.save(admin);

        // Senha do admin trocada no .env: derruba as sessões abertas com a senha antiga
        if (senhaMudou && existente.isPresent()) {
            sessoes.deleteByUsuario(admin);
        }
        log.info("Admin '{}' pronto para entrar (sem cadastro)", nome);
    }
}
