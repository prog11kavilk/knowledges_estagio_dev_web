**CONCEITOS INICIAIS DO JAVA**

\> LINGUAGEM DE ALTO NÍVEL, ORIENTADA A OBJETOS

\> USADA EM LARGA ESCALA PARA CONSOLIDAÇÃO DE SISTEMAS WEB

> \> **JVM** - JAVA VIRTUAL MACHINE
>
> \> ATRIBUI A EXECUÇÃO DO JAVA A PARTIR DE QUALQUER S.O

<img src="./AULA 12 - APRENDENDO JAVA/img/PARTIU JAVA /media/image1.png" style="width:5.52604in;height:0.7628in" />

**CONVENÇÕES DE CÓDIGO**

\> uma arquivo .java deverá **sempre** ter o **nome** da **classe** global

\> nome de **classes** começam com letra **maiúsculas**

\> nome de **variáveis** seguem o “**CamelCase”**

**\> n**omes de **constantes** totalmente em **letras maiúsculas**, separadas por underline.

**VARIÁVEIS**

\> de inteiros: <span class="mark">int</span>

\> de decimais maiores: <span class="mark">double</span> \> menores: **<span class="mark">float</span>**

\> de verdadeiro ou falso: <span class="mark">boolean</span>

**OPERADORES LÓGICOS/CONDICIONAIS**

\>os mesmos do Js (=, !=, \<,\>, \|\|, &&, etc.)

\> operador de pós-incremento \> ++**variavel**

**\> SWITCH/CASE**

\> funciona como o “if” e “else”, mas tem melhor legibilidade

\> **switch (expressão) {**

**case valor1:**

**/ código a ser executado se a expressão for igual a valor1**

**break;**

**case valor2:**

**// código a ser executado se a expressão for igual a valor2**

**<span class="mark">MÉTODOS JAVA</span>**

\> **SCANNER**(): lê os dados do teclado

\> <span class="mark">**import** java.util.Scanner;</span>

<span class="mark"></span>**Scanner scanner = new Scanner(System.in);**

\> **EQUALS()**: compara o conteúdo de duas strings

\> **if (senha.equals("12345")) {**

**System.out.println("Acesso autorizado!");**

**\> FORMAT():** formata um texto tipificando as variáveis

> \> **System.out.println(String.format("Meu nome é %s, eu tenho %d anos e hoje gastei %.2f reais", nome, idade, valor));**

\> **<span class="mark">%s</span>** \> chama as strings

\> **<span class="mark">%d</span>** \> chama os inteiros

\> **<span class="mark">%f</span>** \> indica os decimais

**<u>TEXTBLOCK</u> -** permite escrever em múltiplas linhas

\> **EX**: String mensagem = """

escrevendo meu texto…

Este é um Text Block.

TRUQUES E ATALHOS PARA JAVA: [<u>https://www.alura.com.br/artigos/intellij-idea-dicas-truques-usar-no-dia-a-dia</u>](https://www.alura.com.br/artigos/intellij-idea-dicas-truques-usar-no-dia-a-dia)

resolver esses em casa: [<u>https://cursos.alura.com.br/classpage/java-criando-primeira-aplicacao/task/125669</u>](https://cursos.alura.com.br/classpage/java-criando-primeira-aplicacao/task/125669)
