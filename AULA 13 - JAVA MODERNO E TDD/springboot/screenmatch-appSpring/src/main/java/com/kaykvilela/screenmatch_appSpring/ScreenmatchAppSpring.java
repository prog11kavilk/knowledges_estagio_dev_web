package com.kaykvilela.screenmatch_appSpring;
//importação dos pacotes aplicados

import com.kaykvilela.screenmatch_appSpring.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication		//indica ao springBoot que essa é a classe de inicialização
public class ScreenmatchAppSpring implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchAppSpring.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();


	}
}