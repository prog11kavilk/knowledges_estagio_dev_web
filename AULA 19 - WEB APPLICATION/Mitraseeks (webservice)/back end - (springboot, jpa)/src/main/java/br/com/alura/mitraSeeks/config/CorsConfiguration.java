package br.com.alura.mitraSeeks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Origens que podem chamar a API de outro domínio (Live Server, e depois o domínio da Vercel).
// Configurável no .env, separado por vírgula: CORS_ORIGINS=http://localhost:5500,https://meudominio.com
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${CORS_ORIGINS:http://localhost:5500,http://127.0.0.1:5500,http://localhost:5501,http://127.0.0.1:5501}")
    private String[] origens;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(origens)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD");
    }
}
