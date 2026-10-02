package br.com.alura.mitraSeeks.dto;

import br.com.alura.mitraSeeks.model.Categoria;

public record FilmeDTO(Long id,
                       String titulo,
                       Integer duracao,
                       Integer anoLancamento,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String sinopse,
                       String poster) {
}
