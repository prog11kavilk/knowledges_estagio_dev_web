package br.com.alura.mitraSeeks.config;

import br.com.alura.mitraSeeks.service.FraseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * A cada start, grava as frases novas de resources/catalogo/frases.txt e, em segundo plano,
 * completa cartaz e cores predominantes (baixar os cartazes não atrasa a subida do back).
 */
@Component
public class FraseInicializador implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(FraseInicializador.class);

    @Autowired
    private FraseService servico;

    @Override
    public void run(ApplicationArguments args) {
        try {
            int novas = servico.carregarArquivo("catalogo/frases.txt");
            log.info("Frases clássicas: {} novas carregadas", novas);
        } catch (Exception e) {
            log.error("Não foi possível carregar catalogo/frases.txt", e);
            return;
        }

        new Thread(() -> {
            try {
                servico.completarCartazesECores();
                log.info("Frases clássicas: cartazes e cores prontos");
            } catch (Exception e) {
                log.error("Erro ao completar cartazes e cores das frases", e);
            }
        }, "cores-das-frases").start();
    }
}
