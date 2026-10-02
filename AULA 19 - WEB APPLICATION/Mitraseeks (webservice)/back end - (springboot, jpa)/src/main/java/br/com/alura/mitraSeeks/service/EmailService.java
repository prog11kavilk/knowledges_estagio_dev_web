package br.com.alura.mitraSeeks.service;

import br.com.alura.mitraSeeks.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Properties;

/**
 * Envia os códigos de confirmação/redefinição por SMTP, usando as variáveis MAIL_* do .env.
 * Sem MAIL_HOST configurado, funciona em "modo dev": o código só aparece no console do back.
 */
@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Value("${MAIL_HOST:}")
    private String host;
    @Value("${MAIL_PORT:587}")
    private int porta;
    @Value("${MAIL_USERNAME:}")
    private String usuario;
    @Value("${MAIL_PASSWORD:}")
    private String senha;
    @Value("${MAIL_FROM:}")
    private String remetente;

    public void enviarCodigo(Usuario destinatario, String codigo, Usuario.FinalidadeCodigo finalidade) {
        String assunto = finalidade == Usuario.FinalidadeCodigo.CONFIRMACAO
                ? "MitraSeeks - confirme seu email"
                : "MitraSeeks - redefinição de senha";
        String texto = """
                Olá, %s!

                Seu código %s é: %s

                Ele vale por 15 minutos. Se não foi você que pediu, ignore este email.

                Equipe MitraSeeks
                """.formatted(destinatario.getUserName(),
                finalidade == Usuario.FinalidadeCodigo.CONFIRMACAO ? "de confirmação" : "para redefinir a senha",
                codigo);

        if (host.isBlank()) {
            log.warn("[MODO DEV - sem SMTP no .env] Código {} para {}: {}", finalidade, destinatario.getEmail(), codigo);
            return;
        }

        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom(remetente.isBlank() ? usuario : remetente);
            mensagem.setTo(destinatario.getEmail());
            mensagem.setSubject(assunto);
            mensagem.setText(texto);
            criarRemetente().send(mensagem);
        } catch (Exception e) {
            log.error("Falha ao enviar email para {}", destinatario.getEmail(), e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Não foi possível enviar o email agora. Tente novamente em alguns minutos");
        }
    }

    private JavaMailSenderImpl criarRemetente() {
        JavaMailSenderImpl remetenteSmtp = new JavaMailSenderImpl();
        remetenteSmtp.setHost(host);
        remetenteSmtp.setPort(porta);
        remetenteSmtp.setUsername(usuario);
        remetenteSmtp.setPassword(senha);
        remetenteSmtp.setDefaultEncoding("UTF-8");
        Properties propriedades = remetenteSmtp.getJavaMailProperties();
        propriedades.put("mail.smtp.auth", "true");
        propriedades.put("mail.smtp.starttls.enable", "true");
        propriedades.put("mail.smtp.connectiontimeout", "10000");
        propriedades.put("mail.smtp.timeout", "10000");
        return remetenteSmtp;
    }
}
