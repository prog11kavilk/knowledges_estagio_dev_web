**<span class="mark">\<\<\<\<\<\<\<\<\<\<\<\<\<\<\<\<\<\<\<\<\< ECOSSISTEMA JAVA \>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\>\></span>**

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>&gt; MÁQUINA VIRTUAL - <strong>JVM</strong></p>
<p>&gt; executa o código independente do S.O</p>
<p>&gt; O <strong>Bytecode</strong> é executado (interpretado) pela <strong>JVM</strong>.</p>
<p>&gt; <strong>BIBLIOTECAS - API</strong></p>
<p>&gt; importam funcionalidades já criadas por alguém</p>
<p>&gt; <strong>LINGUAGEM</strong> <strong>JAVA</strong></p>
<p>&gt; o conjunto de classes com as instruções em si.</p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

**<span class="mark">JDK vs JDE vs JVM</span>**

\> **JDK** = JRE + ferramentas de desenvolvimento

\> **JRE** = JVM + bibliotecas

**CASTING**

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>é o processo de <strong>converter dados</strong> no Java</p>
<p><img src="./AULA 12 - APRENDENDO JAVA/img/java pt.2 - ORIENTA����O A OBJETOS/media/image1.png" style="width:1.49947in;height:3.20719in" /></p>
<p>&gt;<strong>ex</strong>: <strong><mark>double meuSalario = 1250.22;</mark></strong></p>
<p><strong><mark>int valor = (int)meuSalario;</mark></strong></p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

**<u>LIFEHACKS DA IDE</u>**

**<span class="mark">ctrl + clique na palavra \></span>** navega até onde aquele item foi definido

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p><em><strong>PARADIGMA &gt;&gt;&gt; PROCEDURAL <mark></mark>vs <mark></mark>ORIENTAÇÃO A OBJETOS</strong></em></p>
<blockquote>
<p>&gt; tudo muito manual &gt; dado e comportamento ficam juntos</p>
</blockquote>
<p>&gt; código individual &gt; atributos(dados) e métodos(comportamento)</p>
<p>&gt; sequência de ações não &gt; encapsulamento, herança, polimorfismo e</p>
<p>práticas, espalhadas abstração.</p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

**<u>CONCEITOS BASE DA POO</u>**

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>&gt;<strong><mark>classe</mark></strong> &gt; é uma especificação de um tipo, definindo atributos e comportamentos.</p>
<p>&gt; <strong><mark>Objeto</mark></strong> &gt; é uma instância de uma classe onde definimos valores para seus atributos.</p>
<p>&gt; <strong><mark>instância</mark></strong> &gt; a implementação do objeto, precisamos usar a palavra chave new</p>
<p>&gt; <strong><mark>referências</mark></strong> &gt; variáveis que armazenam o endereço de memória onde um objeto está localizado.</p>
<p>&gt; <strong><mark>métodos</mark></strong> &gt; bloco de código nomeado dentro de uma classe que executa uma tarefa específica.</p>
<p>&gt; <strong>getters</strong>: pega o valor de um atributo/variável</p>
<p>&gt; <strong>public String getNome( ) {</strong></p>
<p><strong>return this.nome; }</strong></p>
<p>&gt; <strong>setters</strong>: modifica esse valor</p>
<p>&gt; <strong>public void setNome(String nome){</strong></p>
<p><strong>this.nome = nome;</strong></p>
<p>&gt; <strong><mark>construtor</mark></strong> &gt; um bloco de código especial <strong>executado</strong> automaticamente</p>
<blockquote>
<p>&gt; quando você <strong>cria</strong> um novo <strong>objeto</strong> usando a palavra-chave <strong>new</strong></p>
</blockquote>
<p>&gt;<strong><mark>modificadores</mark> &gt;</strong> determinam como será a interação entre diferentes classes</p>
<p>&gt; <strong>PUBLIC</strong>: todos tem acesso</p>
<p>&gt; <strong>PRIVATE</strong>: A única classe que tem acesso ao atributo é a própria classe que o define</p>
<p>&gt; <strong>PROTECTED:</strong> o acesso é por pacote e por herança.</p>
<p><strong>&gt;<mark>interface</mark>&gt;</strong> funciona como um <strong>contrato</strong> que:</p>
<p>&gt; <u>define</u> um conjunto de <u>métodos</u> que uma <u>classe</u> deve obrigatoriamente <u>implementar</u></p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

**<u>CONCEITOS-CHAVE</u>**

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>&gt; <strong><em>encapsulamento</em></strong></p>
<p>&gt; protege os dados internos de uma classe</p>
<p>&gt; usando modificadores de acesso (<strong>private</strong>) e métodos <strong>get</strong> e <strong>set</strong>.</p>
<p>&gt; <strong>herança</strong></p>
<p>&gt; comportamento de uma classe <strong>reutilizar atributos</strong> e <strong>métodos</strong> de outra classe.</p>
<p>&gt; usando a palavra <strong>extends</strong>.</p>
<p>&gt; <strong>polimorfismo</strong></p>
<p>&gt; permite que um <strong>método</strong> tenha o <strong>mesmo nome</strong></p>
<p>&gt; mas <strong>comportamentos diferentes</strong> nas classes filhas</p>
<p>&gt; <strong>abstração</strong></p>
<p>&gt; esconde os detalhes complexos e <strong>mostra</strong> apenas o <strong>essencial</strong></p>
<p>&gt; utilizando a função <strong>abstract</strong> ou <strong>interfaces</strong> .</p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

**<span class="mark"><u>PACOTES</u></span>**

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>&gt; organizam o código por <u>funcionalidade</u>, com um <u>domínio invertido+nome do projeto</u></p>
<p>&gt; EX: <mark>package <a href="http://br.com"><u>br.com</u></a>.sonner.plataformaFilmes.modelos;</mark></p>
<p><mark>import <a href="http://br.com"><u>br.com</u></a>.sonner.plataformaFilmes.modelos.Filme;</mark></p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

**<u>NOTAÇÕES</u>**:

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>&gt;<strong>@Override</strong>:indica que um método está sobrescrevendo o mesmo método definido na sua classe mãe;</p>
<p>&gt;@<strong>Deprecated:</strong> indica que um método ou classe está obsoleto e não é mais usado;</p>
<p>&gt; <strong>@notNull:</strong> usada para validar que um atributo não seja null;</p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

EXERCÍCIOS

desafios: [<u>https://cursos.alura.com.br/classpage/java-aplicando-orientacao-objetos/task/146313</u>](https://cursos.alura.com.br/classpage/java-aplicando-orientacao-objetos/task/146313)

> \> interfaces - [<u>https://cursos.alura.com.br/classpage/java-aplicando-orientacao-objetos/task/146318</u>](https://cursos.alura.com.br/classpage/java-aplicando-orientacao-objetos/task/146318)

**referências** - Documentação: [<u>https://www.alura.com.br/apostila-java-orientacao-objetos</u>](https://www.alura.com.br/apostila-java-orientacao-objetos)
