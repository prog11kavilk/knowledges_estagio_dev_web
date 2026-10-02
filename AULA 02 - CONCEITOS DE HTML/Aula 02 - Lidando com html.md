**Aula 02 - Lógica com desenhos, animações e um jogo**

<span class="mark">Proceder \> abrindo arquivo.html no Vscode</span>

> <span class="mark">\> implementar as **tags** html:</span>
>
> <span class="mark">\<meta charset="UTF-8"\> \</script\> ”código em Js \</script\></span>
>
> <span class="mark">\> biblioteca canvas(quadro em branco) definido pelos parâmetros:</span>
>
> <span class="mark">\> **width**(largura) e \>**height** (altura)</span>
>
> <span class="mark">\> **cor**: fillStyle = ‘cor’</span>
>
> <span class="mark">\></span>
>
> <span class="mark">\> **retângulo**:fillRect(x,y, largura, altura)</span>
>
> <span class="mark">\> **pincel:**vai fazer o desenho na tela</span>
>
> <span class="mark">\> pincel.fillStyle = ‘cor</span>
>
> <span class="mark">\> pincel.BeginPath(); \#indica um caminho de início</span>
>
> <span class="mark">\> pincel.fill(); \#preenche a forma selecionada na ‘cor’</span>
>
> <span class="mark"></span>
>
> <span class="mark">\> **function:** define funções para automatizar o programa</span>
>
> <span class="mark">\> <u>estrutura</u>: nome, (parâmetro) e dentro os processos</span>
>
> <span class="mark">\></span> function dispara(evento) {
>
> var x = evento.pageX - tela.offsetLeft;
>
> var y = evento.pageY - tela.offsetTop;}

**\> métodos e funções úteis:**

**\>** <u>setInterval</u>: (função, intervalo em milissegundos)

\> <span class="mark">setInterval(atualizaTela,1000);</span>

<span class="mark">\> <u>Math.floor, Math.random, [Math.PI](http://math.pi):</u></span>

<span class="mark">\> biblioteca (API) de recursos matemáticos</span>

<span class="mark">\> **return Math.floor(Math.random() \* maximo);**</span>
