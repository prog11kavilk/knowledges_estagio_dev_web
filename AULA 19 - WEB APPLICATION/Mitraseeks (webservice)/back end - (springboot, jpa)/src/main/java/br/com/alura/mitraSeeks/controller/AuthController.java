package br.com.alura.mitraSeeks.controller;

import br.com.alura.mitraSeeks.dto.CadastroDTO;
import br.com.alura.mitraSeeks.dto.ConfirmacaoDTO;
import br.com.alura.mitraSeeks.dto.EmailDTO;
import br.com.alura.mitraSeeks.dto.LoginDTO;
import br.com.alura.mitraSeeks.dto.MensagemDTO;
import br.com.alura.mitraSeeks.dto.RedefinirSenhaDTO;
import br.com.alura.mitraSeeks.dto.SessaoDTO;
import br.com.alura.mitraSeeks.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ContaService servico;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemDTO cadastrar(@RequestBody CadastroDTO dados) {
        servico.cadastrar(dados.nomeUsuario(), dados.email(), dados.senha());
        return new MensagemDTO("Enviamos um código de confirmação para o seu email");
    }

    @PostMapping("/confirmar")
    public SessaoDTO confirmar(@RequestBody ConfirmacaoDTO dados) {
        return servico.confirmarEmail(dados.email(), dados.codigo());
    }

    @PostMapping("/reenviar-codigo")
    public MensagemDTO reenviarCodigo(@RequestBody EmailDTO dados) {
        servico.reenviarCodigoConfirmacao(dados.email());
        return new MensagemDTO("Se houver um cadastro pendente para esse email, um novo código foi enviado");
    }

    @PostMapping("/login")
    public SessaoDTO entrar(@RequestBody LoginDTO dados) {
        return servico.entrar(dados.login(), dados.senha());
    }

    @PostMapping("/esqueci-senha")
    public MensagemDTO esqueciSenha(@RequestBody EmailDTO dados) {
        servico.esqueciSenha(dados.email());
        return new MensagemDTO("Se existir uma conta com esse email, enviamos um código para redefinir a senha");
    }

    @PostMapping("/redefinir-senha")
    public SessaoDTO redefinirSenha(@RequestBody RedefinirSenhaDTO dados) {
        return servico.redefinirSenha(dados.email(), dados.codigo(), dados.novaSenha());
    }

    @PostMapping("/logout")
    public MensagemDTO sair(@RequestHeader(value = "Authorization", required = false) String autorizacao) {
        servico.sair(autorizacao);
        return new MensagemDTO("Sessão encerrada");
    }
}
