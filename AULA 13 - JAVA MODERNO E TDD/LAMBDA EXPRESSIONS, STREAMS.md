**LAMBDA EXPRESSIONS, STREAMS**

**O QUE SÃO LAMBDA EXPRESSIONS?**

\> “<u>funções</u>” anônimas destinadas a escrever <u>códigos</u> mais <u>limpos</u>, <u>curtos</u> e com foco no paradigma funcional.

\> sintaxe padrão \> **parâmetros) -\> { corpo da função }**

**\>** exemplos de uso:

> **<span class="mark">\> numeros.sort((a, b) -\> a.compareTo(b));</span>**
>
> <span class="mark">\> **temporadas.forEach (t -\> t.episodios().forEach (e -\> System.*out*.println (e.título( ))));**</span>

**ddd**

**STREAMS - FLUXO DE OPERAÇÕES EM UMA SÓ LINHA**

\> recurso do Java que permitem encadear dados de dorma funcional

\> servem para <u>colocar</u> no <u>mesmo fluxo</u> as funções / métodos que queremos

\> pode ter duas famílias de operações:

<img src="./AULA 13 - JAVA MODERNO E TDD/img/LAMBDA EXPRESSIONS, STREAMS/media/image3.png" style="width:3.91146in;height:1.58123in" />

\> **finais**: fecham o fluxo da **<span class="mark">stream( )</span>**

\> <u>ex</u>: forEach(), count(), etc…  
\> **intermediárias**: executam um novo stream

\> <u>ex</u>: map(), sort(),etc…

\> recurso **peek()**

\> uma operação intermediária de uma stream.

\> usada para "<u>espiar</u>" os elementos da stream sem alterá-los, ótima para <u>depuração</u>

<img src="./AULA 13 - JAVA MODERNO E TDD/img/LAMBDA EXPRESSIONS, STREAMS/media/image1.png" style="width:3.92208in;height:1.3631in" />

\> **Optional** \<\>- um container especial que pode conter ou não um valor

<img src="./AULA 13 - JAVA MODERNO E TDD/img/LAMBDA EXPRESSIONS, STREAMS/media/image2.png" style="width:3.95313in;height:0.84352in" />

\> Classe **DoubleSummaryStatistics**

\> classe utilitária que permite calcular estatísticas como:

> \> soma, média, valor mínimo e máximo, e total de elementos do tipo double collection
>
> \> possui métodos como **getSum()**, **getAverage()**, **getMin(),** **getMax()** e **getCount()**

EXERCÍCIOS CURSO

[<u>https://cursos.alura.com.br/classpage/java-trabalhando-lambdas-streams-spring-framework/task/174953</u>](https://cursos.alura.com.br/classpage/java-trabalhando-lambdas-streams-spring-framework/task/174953)

[<u>https://cursos.alura.com.br/classpage/java-trabalhando-lambdas-streams-spring-framework/task/174954</u>](https://cursos.alura.com.br/classpage/java-trabalhando-lambdas-streams-spring-framework/task/174954)
