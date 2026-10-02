**AULA 5 - PROGRAMANDO EM HTML5 E CSS3**

**ANTES DE MAIS NADA…**

<span class="mark">\>**HTML**: linguagem de marcação</span>

<span class="mark">\>**CSS**: linguagem de estilização</span>

**<span class="mark"><u>simulando no docs:</u></span>**

isso é um Título

isso é um parágrafo

<img src="./AULA 05 E 06 - HTML5 E CSS3/img/AULA 05 06 - HTML E CSS NA WEB/media/image1.png" style="width:1.31356in;height:1.31356in" />

**O QUE É O HTML?**

\> uma linguagem de marcação de texto (isso é tal coisa, isso é outra)

> \> link documentação: [<u>https://www.w3schools.com/html/html_intro.asp</u>](https://www.w3schools.com/html/html_intro.asp)
>
> \> EXEMPLO:
>
> \<!DOCTYPE html\>
>
> \<html\> \<!--comentário--\>
>
> \<head\>
>
> \<title\>Page Title\</title\>
>
> \</head\>
>
> \<body\>
>
> \<h1\>My First Heading\</h1\>
>
> \<p\>My first paragraph.\</p\>
>
> \</body\>
>
> \</html\>

**<span class="mark">O QUE SÃO AS TAGS?</span>**

<span class="mark">**\>** representadas por \<abertura\> \</fechamento\></span>

<span class="mark">\> são as **divisões funcionais** no código</span>

<span class="mark">\> parecem com funções no Js</span>

- **<span class="mark">TAGS PADRÃO</span>**

> <span class="mark">\> **\<html\>\</html\>** : puxa a linguagem para o código</span>
>
> <span class="mark">\> **\<title\>\</title\>** : adiciona o título da página</span>
>
> <span class="mark">\> **\<body\>\</body\>** : escreve o texto, dentro da página</span>
>
> <span class="mark">\> **\<h1\>\</h1\> :** aumenta o tamanho da fonte, dentro de \<body\></span>
>
> <span class="mark">\> quanto maior o n°, menor a fonte (h3, h4, h5…)</span>
>
> <span class="mark">\> **\<p\>\</p\>:** introduz um parágrafo de texto formatado</span>
>
> <span class="mark">\> **\<strong\>\</strong\>:** destaca o texto que estiver dentro</span>
>
> <span class="mark">\> \<**img**\> tag sem fechamento, vai abrir a imagem com o endereço fornecido</span>
>
> <span class="mark">\> propriedade **\<src=””\>:** chamo dentro de \<img\> e passo “nome_da_img”</span>
>
> <span class="mark">\> propriedade **\<alt=””\>:** exibe texto de acessibilidade da imagem</span>
>
> <span class="mark">\> \<**a\>\</a\>:** tag âncora \> serve para redirecionar a outra página(link)</span>

- **<span class="mark">EXTENSÕES PARA HTML</span>**

<span class="mark">\></span>liveServer: <span class="mark">dá o refresh na página automaticamente</span>

<span class="mark">\></span> emmit: <span class="mark">usa uma série de abreviações para o código</span>

- **<span class="mark">USANDO O FIGMA</span>**

<span class="mark">\> ferramenta para personalizar a página e extração de informações</span>

- **<span class="mark">ATALHO PARA AS TAGS</span>**

> <span class="mark">\> ‘!’ + Enter \> já coloca as tags principais.</span>

- **<span class="mark">SEMÂNTICA DO CÓDIGO</span>**

> <span class="mark">\> se refere ao <u>*significado*</u> de um trecho de código</span>

<span class="mark"></span>

***<span class="mark">CSS</span>***

**<span class="mark">PRA QUE SERVE O CSS?</span>**

<span class="mark">\></span> CSS <span class="mark">\> folhas de estilo em cascata</span>

<span class="mark">\> controla o layout das páginas web.</span>

<span class="mark">\> com um <u>arquivo.css externo</u>, você pode alterar a aparência de uma <u>página toda!</u></span>

**<span class="mark">EXEMPLO - ARQUIVO EXTERNO CSS</span>**

> <span class="mark">**body{**</span>
>
> **<span class="mark">background-color: black;</span>**
>
> **<span class="mark">color: white;</span>**

<span class="mark">**}**</span>

**<span class="mark">CORES NO CSS</span>**

<span class="mark">\> as cores devem ser informadas nas <u>coordenadas RGB</u></span>

> <span class="mark">\>sitecores:</span>[<u>https://colorhunt.co/</u>](https://colorhunt.co/)

**<span class="mark">SELETORES DE CLASSE</span>**

> <span class="mark">\> trabalhando com</span> **class**
>
> <span class="mark">\> permite ao CSS selecionar e acessar elementos específicos através dos seletores de classe, iniciados por um ponto”.”</span>
>
> <span class="mark">\> EXEMPLO: .titulo{</span>
>
> <span class="mark">color: red;</span>
>
> <span class="mark">}</span>

**<span class="mark">BOX MODEL</span>**

**<span class="mark">\></span>** é essencialmente uma caixa que envolve <u>todos os elementos HTML</u>.

\> **Conteúdo** - O conteúdo da caixa, onde aparecem texto e imagens.

> **Preenchimento** - Cria uma área livre ao redor do conteúdo. O preenchimento é transparente.
>
> **Borda** - Uma borda que circunda o preenchimento e o conteúdo.
>
> **Margem** - Deixa uma área livre além da borda. A margem é transparente.

\> reset CSS \> mudar o padrão do navegador, colocando tudo conforme regra externa determinada

**BOX SIZING**

\> relaciona as dimensões do elemento-filho e elemento-pai

**FLEXBOX**

\> <span class="mark">usado para <u>layouts unidimensionais,</u> com linhas OU colunas.</span>

<span class="mark">\>como utilizar: nomear a classe no html, definir no CSS com:</span>

<span class="mark">\> display:flex /\**algumas funções\**/</span>

<span class="mark">\></span> align-items: center;

<span class="mark">\>alinha tudo para o centro do maior elemento</span>

<span class="mark">\> j</span>ustify-content: space between;

<span class="mark">\> justifica o conteúdo com espaço</span>

<span class="mark"></span>\>flex-direction: columns

> <span class="mark">\> direção de execução do flex</span>

**<span class="mark">\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_</span>**

**AULA 06 - REFINANDO NOSSO CÓDIGO**

**<span class="mark">\>NOVAS PROPRIEDADES:</span>**

<span class="mark">\>**border**</span>

<span class="mark">\> **border: 2px solid \#22D4FD; <u>\> (espessura, estilo, cor)</u>**</span>

<span class="mark">\>\]\></span> [<span class="mark"></span><u>https://www.w3schools.com/css/css_border.asp</u>](https://www.w3schools.com/css/css_border.asp)

<span class="mark">\> **hover**</span>

<span class="mark">\></span> .<span class="mark">**apresentacao\_\_links\_\_link:hover{** \>fará algo definido abaixo quando receber o mouse **\>** display: flex;</span> \>liga a formatação flex

<span class="mark">flex-direction: column; \> muda para em coluna</span>

<span class="mark">justify-content: space-between;</span> \> espaça os elementos do meio

> <span class="mark">align-items:center;</span> \> centraliza o texto
>
> <span class="mark">gap:32px; \> distância entre os elementos dessa classe</span>

**<span class="mark">ATRIBUINDO VARIÁVEIS NO CSS:</span>**

<span class="mark">\> define a classe :root no início e nela declarar as variáveis (--nome-da variável: valor;)</span>

<span class="mark">\>em seguida, chamar nas classes em que aparecem</span>

<span class="mark">**\> EXEMPLO: :root {--primary-bg-color: \#1e90ff;}**</span>

**<span class="mark">(…)</span>**

<span class="mark">**body {background-color: var(--primary-bg-color);}**</span>

**<span class="mark">ATALHOS USADOS NA AULA:</span>**

<span class="mark">\> **ctrl+k+c** \> transforma uma linha em comentário</span>

<span class="mark">\> **! + ente**r \>traz o escopo do arquivo html pronto</span>

<span class="mark">\> **CTRL + SHIFT + M \>** abre o devTools do navegador</span>

**<span class="mark">\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_</span>**

**AULA 07 - RESPONSIVIDADE E PUBLICAÇÃO DE PROJETOS**

**<span class="mark">UNIDADES DE MEDIDA:</span>**

<span class="mark">\> **absolutas**: não variam independente do navegador ou aparelho (cm, px…)</span>

<span class="mark">\>**relativas**:</span> especificam um comprimento em relação a outra propriedade de comprimento

\> rem: medida responsiva às config. do browser (cada 1 rem = 16px)

**RESPONSIVIDADE PARA PÁGINA:**

\> media-queries: remodelam a página por base de um parâmetro

\>EXEMPLO: <span class="mark">@media:(min-width: 700px)</span> { ... }

\> propriedade auto: define largura do conteúdo, etc em relação ao elemento pai

> \><span class="mark">width:auto; ou width: 100%</span>

**<span class="mark">GITHUB - DISSEMINANDO O PROJETO</span>**

<span class="mark">**\>** acessar o site:</span> [<u>https://github.com/dashboard</u>](https://github.com/dashboard)

<span class="mark">\> ir em “+” \> novo repositório</span>

<span class="mark">\>elaborar nome, descrição e tipo (púb ou priv) do projeto.</span>

<span class="mark">\> baixar os arquivos do projeto e enfim publicar</span>

**<span class="mark">DOCUENTAÇÕES IMPORTANTES:</span>** [<u>https://cursos.alura.com.br/classpage/html-css-responsividade-publicacao-projetos/task/147968</u>](https://cursos.alura.com.br/classpage/html-css-responsividade-publicacao-projetos/task/147968)

<span class="mark">MISSÃO DO CURSO:</span>

<span class="mark">\> criar <u>do zero</u> minha página integrada</span>

<span class="mark">\> video complementar:</span> [<u>https://youtu.be/y4ltLH9iK8E?si=eYeqm4zNz7PPg9ZA</u>](https://youtu.be/y4ltLH9iK8E?si=eYeqm4zNz7PPg9ZA)

\> o que é github? [<u>https://youtu.be/DqTITcMq68k?si=jmBISyiq2uWtXQZ0</u>](https://youtu.be/DqTITcMq68k?si=jmBISyiq2uWtXQZ0)

> <span class="mark">\> como usar git e github:</span> [<u>https://youtu.be/UBAX-13g8OM?si=aFKAPx0zbcLmgU9s</u>](https://youtu.be/UBAX-13g8OM?si=aFKAPx0zbcLmgU9s)
