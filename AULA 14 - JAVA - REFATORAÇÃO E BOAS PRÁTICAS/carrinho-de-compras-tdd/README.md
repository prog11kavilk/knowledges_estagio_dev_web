# Carrinho de Compras — Projeto de Referência (TDD + JUnit)

Projeto de referência criado a partir do curso **[TDD e Java: testes automatizados com JUnit](https://www.alura.com.br/curso-online-tdd-java-testes-automatizados-junit)** (Alura), usando **apenas as ferramentas vistas no curso**: Java puro + **JUnit 5 (Jupiter)**. Sem Mockito, AssertJ ou qualquer outra biblioteca de teste.

## Estrutura

```
carrinho-de-compras-tdd/
├── pom.xml
├── README.md
└── src
    ├── main/java/com/kayk/carrinho/
    │   ├── Produto.java
    │   ├── Carrinho.java
    │   ├── ProdutoInvalidoException.java
    │   └── ProdutoNaoEncontradoException.java
    └── test/java/com/kayk/carrinho/
        ├── ProdutoTest.java
        └── CarrinhoTest.java
```

## Como rodar os testes

```bash
mvn test
```

## Como o TDD foi aplicado (RED → GREEN → REFACTOR)

Cada funcionalidade nasceu de um teste escrito **antes** do código de produção:

1. **RED** — escrevemos um teste (ex.: `deveCriarProdutoValido`) para uma classe/método que ainda não existia. O projeto nem compilava.
2. **GREEN** — criamos o código mínimo em `Produto`/`Carrinho` para o teste passar (sem se preocupar com elegância).
3. **REFACTOR** — com os testes passando como rede de segurança, organizamos o código (getters, `equals`/`hashCode`, extração de métodos como `subtotal()`), rodando os testes novamente para garantir que nada quebrou.

Esse ciclo se repetiu para cada regra de negócio: cálculo de subtotal, soma do total do carrinho, remoção de item, aplicação de cupom de desconto e validação de dados inválidos.

## Recursos do JUnit 5 usados (mapeados ao conteúdo do curso)

| Módulo do curso | Onde aparece no projeto |
|---|---|
| 1. Testes automatizados | Todos os métodos de teste em `ProdutoTest` e `CarrinhoTest` |
| 2. JUnit | `@Test`, `@DisplayName`, `@BeforeEach` (em `CarrinhoTest`) |
| 3. Asserts | `assertEquals`, `assertTrue`, `assertFalse` |
| 4. TDD | Sequência de commits/testes descrita acima (RED → GREEN → REFACTOR) |
| 5. Lidando com Exceptions | `assertThrows` + exceções de domínio (`ProdutoInvalidoException`, `ProdutoNaoEncontradoException`) |
| 6. Mais recursos | `@ParameterizedTest` + `@ValueSource` em `ProdutoTest` |

## Regras de negócio implementadas

- Um produto precisa de nome, preço (> 0) e quantidade (> 0) válidos.
- O total do carrinho é a soma dos subtotais (`preço × quantidade`) de cada produto.
- É possível remover um produto do carrinho; tentar remover um produto que não está lá lança `ProdutoNaoEncontradoException`.
- É possível aplicar um cupom de desconto percentual (0–100) sobre o total.
