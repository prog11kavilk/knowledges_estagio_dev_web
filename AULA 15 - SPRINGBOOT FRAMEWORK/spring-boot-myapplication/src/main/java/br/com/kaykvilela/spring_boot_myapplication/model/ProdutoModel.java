package br.com.kaykvilela.spring_boot_myapplication.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ProdutoModel {

    private Integer id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
