package br.com.alura.mitraSeeks.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Descobre as cores predominantes de um cartaz (usadas no gradiente de fundo da página de frases).
 * Baixa a imagem, agrupa os pixels em "baldes" de cores parecidas e devolve as mais frequentes,
 * com um peso extra para cores saturadas e pulando tons quase iguais aos já escolhidos.
 */
public class CoresDoCartaz {

    private static final int QUANTIDADE = 3;
    // Distância mínima (RGB) entre as cores escolhidas, para o gradiente não ficar de uma cor só
    private static final double DISTANCIA_MINIMA = 70;

    private static final HttpClient cliente = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    // Devolve até 3 cores em hex (ex.: "#1a2b3c"), da mais para a menos predominante; lista vazia se falhar
    public static List<String> predominantes(String urlPoster) {
        try {
            var requisicao = HttpRequest.newBuilder(URI.create(urlPoster))
                    .timeout(Duration.ofSeconds(10))
                    .build();
            byte[] bytes = cliente.send(requisicao, HttpResponse.BodyHandlers.ofByteArray()).body();
            BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(bytes));
            return imagem == null ? List.of() : predominantes(imagem);
        } catch (Exception e) {
            return List.of();
        }
    }

    static List<String> predominantes(BufferedImage imagem) {
        // Amostra de ~60x90 pixels já basta e deixa o cálculo instantâneo
        int passoX = Math.max(1, imagem.getWidth() / 60);
        int passoY = Math.max(1, imagem.getHeight() / 90);

        // Balde = 3 bits por canal (512 grupos); guarda contagem e soma para tirar a média real do grupo
        Map<Integer, long[]> baldes = new HashMap<>();
        for (int y = 0; y < imagem.getHeight(); y += passoY) {
            for (int x = 0; x < imagem.getWidth(); x += passoX) {
                int rgb = imagem.getRGB(x, y);
                int r = (rgb >> 16) & 0xff, g = (rgb >> 8) & 0xff, b = rgb & 0xff;
                int chave = (r >> 5) << 6 | (g >> 5) << 3 | (b >> 5);
                long[] balde = baldes.computeIfAbsent(chave, k -> new long[4]);
                balde[0]++;
                balde[1] += r;
                balde[2] += g;
                balde[3] += b;
            }
        }

        List<Candidata> candidatas = new ArrayList<>();
        for (long[] balde : baldes.values()) {
            int[] cor = {(int) (balde[1] / balde[0]), (int) (balde[2] / balde[0]), (int) (balde[3] / balde[0])};
            candidatas.add(new Candidata(cor, balde[0] * (0.5 + saturacao(cor))));
        }
        candidatas.sort((a, b) -> Double.compare(b.pontos(), a.pontos()));

        List<int[]> escolhidas = new ArrayList<>();
        for (Candidata candidata : candidatas) {
            int[] cor = candidata.cor();
            if (escolhidas.stream().allMatch(e -> distancia(e, cor) >= DISTANCIA_MINIMA)) {
                escolhidas.add(cor);
                if (escolhidas.size() == QUANTIDADE) {
                    break;
                }
            }
        }
        return escolhidas.stream().map(CoresDoCartaz::hex).toList();
    }

    private record Candidata(int[] cor, double pontos) {
    }

    private static double saturacao(int[] cor) {
        int maior = Math.max(cor[0], Math.max(cor[1], cor[2]));
        int menor = Math.min(cor[0], Math.min(cor[1], cor[2]));
        return maior == 0 ? 0 : (maior - menor) / (double) maior;
    }

    private static double distancia(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2) + Math.pow(a[2] - b[2], 2));
    }

    private static String hex(int[] cor) {
        return String.format("#%02x%02x%02x", cor[0], cor[1], cor[2]);
    }
}
