***API’s, SERVIÇOS E ARQUIVOS WEB***

**<u>API</u>**

\> conjunto de padrões, protocolos e ferramentas para construir software e aplicativos.

\> serve para integrar sistemas com padrão e segurança

\> **<u>API WEB</u>**

\> mais usada, troca dados entre bases via protocolo Http

**<u>JSON</u>**

\> formato de dados leve e popular para troca de informações entre sistemas

\> composto por dois tipos de estruturas de dados

\> **objetos** \> uma coleção de pares **chave-valor**

**\> arrays \>** uma coleção ordenada de valores

\> usado para enviar e receber dados de APIs, através também de bibliotecas

**<u>POSTMAN</u>**

**\>** aplicação de testes e depurações de API’s

**<u>GSON</u>**

\> é o conversor de webservices em Json para a máquina local

\> como chamar no projeto:

> \> No menu superior, vá em **File** \> **Project Structure** (ou use o atalho Ctrl + Alt + Shift + S).

1.  No menu esquerdo, clique em **Libraries**.

2.  Clique no botão de adição **+** (topo da segunda coluna) e escolha **From Maven...**.

3.  Na barra de pesquisa, digite com.google.code.gson:gson:2.11.0 e clique na lupa.

4.  Marque a caixa **Transitive dependencies** para garantir que tudo funcione.

5.  Clique em **OK**, selecione o seu módulo atual e clique em **Apply** e **OK**.

**<u>TRATANDO EXCEÇÕES</u>**

**\> try/catch/finally**

\> são blocos que testam o código e caso tenha exceção, emite o erro.

<img src="./AULA 12 - APRENDENDO JAVA/img/java pt.4 - API���s, SERVI��OS E ARQUIVOS WEB/media/image1.png" style="width:2.56079in;height:1.43404in" />

\> **criando minha própria exceção**

**<span class="mark">\> throw new nomeDaExcecao(“mensagem da exceção”)</span>**

**CLASSE ARQUIVOS - CRIAÇÃO E EDIÇÃO**

\> **<span class="mark">File file = new File("C:\\meuArquivo.txt");</span>**

DESAFIOS

[<u>https://cursos.alura.com.br/classpage/java-consumindo-api-gravando-arquivos-lidando-erros/task/147675</u>](https://cursos.alura.com.br/classpage/java-consumindo-api-gravando-arquivos-lidando-erros/task/147675)

REFERÊNCIAS

[<u>https://cursos.alura.com.br/classpage/java-consumindo-api-gravando-arquivos-lidando-erros/task/145164</u>](https://cursos.alura.com.br/classpage/java-consumindo-api-gravando-arquivos-lidando-erros/task/145164)
