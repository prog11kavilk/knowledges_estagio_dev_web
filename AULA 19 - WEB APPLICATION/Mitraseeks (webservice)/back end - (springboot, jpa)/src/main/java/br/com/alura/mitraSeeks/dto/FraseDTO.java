package br.com.alura.mitraSeeks.dto;

import java.util.List;

public record FraseDTO(Long id,
                       String tipo,
                       Long tituloId,
                       String titulo,
                       String personagem,
                       String frase,
                       String poster,
                       List<String> cores) {
}
