package br.com.alura.mitraSeeks.controller;

import br.com.alura.mitraSeeks.dto.AlterarSenhaDTO;
import br.com.alura.mitraSeeks.dto.ContaDTO;
import br.com.alura.mitraSeeks.dto.FotoDTO;
import br.com.alura.mitraSeeks.dto.MensagemDTO;
import br.com.alura.mitraSeeks.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// Todas as rotas exigem o cabeçalho "Authorization: Bearer <token>" recebido no login
@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService servico;

    @GetMapping
    public ContaDTO obterConta(@RequestHeader(value = "Authorization", required = false) String autorizacao) {
        return servico.obterConta(servico.usuarioDoToken(autorizacao));
    }

    @PutMapping("/senha")
    public MensagemDTO alterarSenha(@RequestHeader(value = "Authorization", required = false) String autorizacao,
                                    @RequestBody AlterarSenhaDTO dados) {
        servico.alterarSenha(servico.usuarioDoToken(autorizacao), dados.senhaAtual(), dados.novaSenha());
        return new MensagemDTO("Senha alterada");
    }

    @PutMapping("/foto")
    public ContaDTO alterarFoto(@RequestHeader(value = "Authorization", required = false) String autorizacao,
                                @RequestBody FotoDTO dados) {
        return servico.alterarFoto(servico.usuarioDoToken(autorizacao), dados.foto());
    }

    @PutMapping("/preferencias")
    public ContaDTO salvarPreferencias(@RequestHeader(value = "Authorization", required = false) String autorizacao,
                                      @RequestBody Map<String, Object> preferencias) {
        return servico.salvarPreferencias(servico.usuarioDoToken(autorizacao), preferencias);
    }

    @DeleteMapping("/sessoes")
    public MensagemDTO sairDeTodosOsDispositivos(@RequestHeader(value = "Authorization", required = false) String autorizacao) {
        servico.sairDeTodosOsDispositivos(servico.usuarioDoToken(autorizacao));
        return new MensagemDTO("Você saiu de todos os dispositivos");
    }
}
