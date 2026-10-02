**O QUE LEMBRAR DA AULA?**

**PARA COMEÇAR:**

\> adicionar controls nas tags \<aúdio\> do html

\> para liberar o mini player de cada som

\> podemos implantar atributos do Js no Html, como no projeto:

\<script src="main.js"\>\</script\>

\> não se deve fazer o JS “in line”

\> abrir um arquivo Js próprio amarrado aos outros

**NO NOSSO ARQUIVO JS**

\> usar o devTools do navegador (F12)

\> testar primeiro na aba console

\> funcionalidade <span class="mark">querySelector</span>(‘’)

\> seletor de busca para o elemento entre ’ ’

\> funcionalidade <span class="mark">querySelectorAll</span>(‘’)

\> seleciona tudo que aponta para aquela classe.

\> <span class="mark">document</span>: seleciona o elemento do seletor informado

\>EX: <span class="mark">document.querySelectorAll('.tecla_sons')</span>

**FUNÇÕES**

**\> <span class="mark">function nomeDaFunção(parâmetros)</span>**

**\>funções anônimas:** úteis para executar alguma coisa uma única vez ou em apenas um lugar

\> EXEMPLO: **<span class="mark">listaDeTeclas\[contador\].onclick= function(){aciona outra função}</span>**

**ATRIBUTOS**

**\>** **play:** vai ao final da linha e literalmente reproduz o som

\>document.querySelector('#som_tecla_pom').play

\> **onclick**: faz algo com o clique no botão escolhido.

<span class="mark">\> document.querySelector('.tecla_pom').onclick = tocaSomPom;</span>

\>**classList**\[classe1, classe2,...\]: mantém o conteúdo do índice que se quer da lista de classes

\>**<span class="mark">const instrumento = tecla.classList\[1\];</span>**

**\>.add:** adiciona um valor à lista \> **<span class="mark">tecla.classList.add('ativa');</span>**

**\>remove:** remove da lista, o oposto do .add **<span class="mark">tecla.classList.remove('ativa');</span>**

**\>onkeyup, onkeydown:** muda o aspecto de algo no momento em que

**\> tecla desce: <span class="mark">tecla.onkeydown = function(evento){</span>**

**\> tecla sobe: <span class="mark">tecla.onkeyup = function(){</span>**

**OPERADORES LÓGICOS**

**\>** <span class="mark">operador de igualdade **(==)**</span>

**<span class="mark">\></span>** estritamente igual **<span class="mark">(===)</span>**

**\>** operador **or** **<span class="mark">(\|\|)</span>**

**\>** operador lógico **and** **<span class="mark">(&&)</span>**

\> valor vazio <span class="mark">(**null**)</span>

**LOOPS/LAÇOS DE REPETIÇÃO**

**\> while**: quando a parada depende de algo externo ou incerto

\> **<span class="mark">let i = 0; while (i \< 10) { i++; }</span>**

\> **for**: sabe quantas vezes o código deve se repetir, reunindo a contagem em uma única linha

**\> <span class="mark">for (let i = 0; i \< 10; i++) { }</span>**

**LISTAS**

\> serve sempre que precisar repetir várias vezes algo:

**\> <span class="mark">const listaDeTeclas = document.querySelectorAll('.tecla')</span>;**

**REFERÊNCIAS**

**\>** usamos **const** para criar constantes (valores fixos)

\> e **let** ou para criar variáveis (valores mutáveis)
