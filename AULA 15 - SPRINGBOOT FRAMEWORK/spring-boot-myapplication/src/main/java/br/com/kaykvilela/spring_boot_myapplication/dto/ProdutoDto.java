package br.com.kaykvilela.spring_boot_myapplication.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ProdutoDto {

    private String nome;
    private Double preco;
    private Integer quantidade;
}
