package br.com.alura.mitraSeeks.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ContaDTO(String nomeUsuario,
                       String email,
                       String foto,
                       Map<String, Object> preferencias,
                       LocalDateTime criadoEm,
                       boolean admin) {
}
