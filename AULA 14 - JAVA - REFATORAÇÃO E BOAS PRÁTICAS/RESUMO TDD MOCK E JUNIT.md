**TDD E JAVA: TESTES AUTOMATIZADOS COM JUNIT**

> **\>** curso Alura — disciplina de **<span class="mark">testes automatizados</span>** e **<span class="mark">TDD</span>** em aplicações Java
>
> **\>** pré-requisito: conhecimento em **Java** e **Orientação a Objetos**
>
> **\>** carga horária: 8h — 41 atividades

**1. TESTES AUTOMATIZADOS — CONCEITOS INICIAIS**

> **\>** teste automatizado **<span class="mark">substitui a verificação manual</span>** (rodar a aplicação e clicar/testar na mão)
>
> **\>** garante que o código **<span class="mark">continua funcionando</span>** após alterações — evita regressões
>
> **\>** permite refatorar com **<span class="mark">segurança</span>**: se os testes continuam passando, o comportamento não mudou
>
> **\>** um teste é, na prática, um **método Java** que chama o código a testar e verifica o resultado esperado

**2. JUNIT**

> **\>** biblioteca principal para escrever e rodar testes automatizados em Java (versão usada: **JUnit 5 / Jupiter**)
>
> **\>** dependência **Maven**:
>
> **\<dependency\>  
> \<groupId\>org.junit.jupiter\</groupId\>  
> \<artifactId\>junit-jupiter\</artifactId\>  
> \<version\>5.10.2\</version\>  
> \<scope\>test\</scope\>  
> \</dependency\>**
>
> **\>** classe de teste segue a convenção **NomeDaClasseTest**, nos testes ficam em **src/test/java**

**ANOTAÇÕES PRINCIPAIS**

> **\> <span class="mark">@Test</span>** → marca um método como um caso de teste
>
> **\> <span class="mark">@BeforeEach</span>** / **<span class="mark">@AfterEach</span>** → executam antes/depois de **cada** teste (ex: recriar objetos)
>
> **\> <span class="mark">@BeforeAll</span>** / **<span class="mark">@AfterAll</span>** → executam uma única vez, antes/depois de **todos** os testes da classe (precisam ser **static**)
>
> **\> <span class="mark">@DisplayName</span>**("...") → dá um nome legível ao teste, exibido no relatório
>
> **\> <span class="mark">@Disabled</span>** → ignora um teste temporariamente

**3. ASSERTS (VERIFICAÇÕES)**

> **\> assertEquals(esperado, obtido)** → compara valor esperado com o valor retornado
>
> **\> assertTrue(condição)** / **assertFalse(condição)**
>
> **\> assertNull(objeto)** / **assertNotNull(objeto)**
>
> **\> assertThrows(Classe.class, () -\> { ... })** → verifica que uma exceção é lançada
>
> **@Test  
> @DisplayName("Deve calcular o total do carrinho")  
> void deveCalcularTotal() {  
> Carrinho carrinho = new Carrinho();  
> carrinho.adicionar(new Produto("Caneta", 2.5, 3));  
> assertEquals(7.5, carrinho.total());  
> }**

**4. TDD: TEST DRIVEN DEVELOPMENT**

> **\>** metodologia em que o **<span class="mark">teste é escrito antes</span>** do código de produção
>
> **\>** ciclo **<span class="mark">RED → GREEN → REFACTOR</span>**:
>
> **\> RED** → escreve um teste que falha (a funcionalidade ainda não existe)
>
> **\> GREEN** → escreve o código mínimo para o teste passar
>
> **\> REFACTOR** → melhora o código (limpeza, organização) mantendo os testes passando
>
> **\>** benefício: o design da classe nasce **<span class="mark">a partir do uso</span>** (do teste), não do código em si

**5. LIDANDO COM EXCEPTIONS**

> **\>** testar caminhos de erro é tão importante quanto testar o caminho feliz
>
> **\>** usa-se **assertThrows** para garantir que uma regra de negócio inválida lança a exceção correta
>
> **@Test  
> @DisplayName("Não deve aceitar quantidade zero ou negativa")  
> void deveLancarExcecaoQuantidadeInvalida() {  
> assertThrows(IllegalArgumentException.class, () -\> {  
> new Produto("Caneta", 2.5, 0);  
> });  
> }**

**6. MAIS RECURSOS**

> **\> <span class="mark">@ParameterizedTest</span>** + **<span class="mark">@ValueSource</span>** → roda o mesmo teste com vários valores de entrada
>
> **\>** cobertura de testes (ex: **JaCoCo**) → mede quanto do código é exercitado pelos testes
>
> **\>** boas práticas: um **<span class="mark">assert</span>** por conceito testado, nomes de teste descritivos, testes independentes entre si

**PROJETO DE REFERÊNCIA**

> **\>** aplicação: **<span class="mark">Carrinho de Compras</span>** (Maven + JUnit 5), aplicando TDD do zero — ver pasta do projeto anexada

**LINKS ÚTEIS**

[<u>Curso: TDD e Java — testes automatizados com JUnit (Alura)</u>](https://www.alura.com.br/curso-online-tdd-java-testes-automatizados-junit)

[<u>Documentação oficial JUnit 5 (User Guide)</u>](https://junit.org/junit5/docs/current/user-guide/)
