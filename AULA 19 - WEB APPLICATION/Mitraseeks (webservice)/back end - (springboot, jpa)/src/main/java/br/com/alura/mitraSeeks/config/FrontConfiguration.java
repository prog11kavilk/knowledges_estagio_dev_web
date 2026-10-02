package br.com.alura.mitraSeeks.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Serve a pasta do front direto de onde ela está (sem copiar para dentro do back),
 * então abrir http://localhost:8080 já abre o site com a API na mesma origem.
 * A pasta continua independente para ser publicada sozinha (ex.: Vercel).
 * Caminho configurável no .env: FRONT_DIR=../front all - (css, html, js)
 */
@Configuration
public class FrontConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(FrontConfiguration.class);

    @Value("${FRONT_DIR:../front all - (css, html, js)}")
    private String pastaFront;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path pasta = Path.of(pastaFront).toAbsolutePath().normalize();
        if (!Files.isDirectory(pasta)) {
            log.warn("Pasta do front não encontrada em {}. O back vai responder só a API", pasta);
            return;
        }
        // Só os arquivos do site; as rotas da API (/auth, /series...) seguem com os controllers
        registry.addResourceHandler("/*.html", "/*.css")
                .addResourceLocations(pasta.toUri().toString())
                .setCachePeriod(0);
        for (String subpasta : new String[]{"css", "scripts", "img"}) {
            registry.addResourceHandler("/" + subpasta + "/**")
                    .addResourceLocations(pasta.resolve(subpasta).toUri().toString())
                    .setCachePeriod(0);
        }
        log.info("Front servido de {} em http://localhost:8080", pasta);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
