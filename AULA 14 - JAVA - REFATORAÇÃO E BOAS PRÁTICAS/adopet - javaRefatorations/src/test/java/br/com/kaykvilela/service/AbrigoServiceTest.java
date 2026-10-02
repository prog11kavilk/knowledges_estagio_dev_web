package br.com.kaykvilela.service;
import br.com.kaykvilela.client.ClientHttpConfiguration;
import br.com.kaykvilela.domain.Abrigo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.mock;


public class AbrigoServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "34990002921", "abrigo_teste@gmail.com");

    @Test
    public void deveVerificarSeVaiDispararRequisicao() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdNome = "0 - Teste";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.out.println(baos);
    }
}
