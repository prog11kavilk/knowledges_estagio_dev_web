package br.com.alura.mitraSeeks.service.traducao;

import br.com.alura.mitraSeeks.service.ConsumoApi;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class ConsultaMyMemory {

    // Quando a cota gratuita do dia acaba, não adianta chamar de novo até o dia seguinte
    private static LocalDate diaCotaEsgotada;

    // Traduz para pt-br; se a tradução falhar ou a cota tiver acabado, devolve o texto original
    public static String obterTraducao(String text) {
        if (text == null || text.isBlank() || "N/A".equals(text) || LocalDate.now().equals(diaCotaEsgotada)) {
            return text == null ? "" : text;
        }

        ObjectMapper mapper = new ObjectMapper();

        ConsumoApi consumo = new ConsumoApi();

        String texto = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        try {
            String json = consumo.obterDados(url);
            DadosTraducao traducao = mapper.readValue(json, DadosTraducao.class);

            if (Boolean.TRUE.equals(traducao.cotaEsgotada()) || "429".equals(traducao.status())) {
                diaCotaEsgotada = LocalDate.now();
                return text;
            }

            String traduzido = traducao.dadosResposta() == null ? null : traducao.dadosResposta().textoTraduzido();
            if (!"200".equals(traducao.status()) || traduzido == null || traduzido.startsWith("MYMEMORY WARNING")) {
                return text;
            }
            return traduzido;
        } catch (Exception e) {
            return text;
        }
    }
}
