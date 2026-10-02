package br.com.kaykvilela.tabelafipe;

import br.com.kaykvilela.tabelafipe.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication        //indica ao springBoot que essa é a classe de inicialização
public class TabelaFipeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TabelaFipeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();

    }
}
