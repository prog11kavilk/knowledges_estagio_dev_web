AULA 09 - HTTP e SERVIÇOS WEB

O QUE É O HTTP?

\> protocolo que transmite dados do cliente ao servidor e <span class="mark">vice-versa</span>

\> usado em todas as aplicações web

\> trabalha aplicando modelos de <u>requisição</u> e <u>resposta</u>

HTTPS: A VERSÃO SEGURA DO HTTP

\> difere do http pois tem uma camada de segurança infalível

\> certificado digital (identidade)

\> chaves (PÚBLICA E PRIVADA) para acessar os dados criptografados

\> começa com criptografia assimétrica para depois mudar para criptografia simétrica.

**ENDEREÇOS**

\> **domínio**: nome da página web, normalmente fácil de gravar .

\> no domínio, há:

\> [<u>https://google.com.br</u>](https://google.com.br)

\> **endereços IP:** tipo de endereço numérico legível ao computador bem mais complicado

\> http://172.217.29.68

> \> **servidores DNS:** realiza a tradução do <u>nome de um domínio</u> para o <u>endereço de IP.</u>

\>URL são endereços da Web

\> começam com o **protocolo** <span class="mark">(http://)</span> seguido pelo **domínio** <span class="mark">([<u>www.alura.com.br</u>](http://www.alura.com.br))</span>

\> após o domínio é determinado o caminho para um **recurso** <span class="mark">(course/intro-html-css)</span>

**MODELO REQUISIÇÃO E RESPOSTA**

**\>requisição:** o “pedido” gerado no navegador por dados do servidor

**\>resposta:** as informações ofertadas pelo servidor após a requisição

\> **Sessão:** é a sequência de passos em que um cliente (como um navegador) abre uma conexão, faz requisições ao servidor e recebe de volta os dados pedidos**.**

> \>Ex: faço o login no site da Alura e vejo o progresso dos meus cursos
>
> \> **Cookies**: arquivo de texto que <u>guarda infos</u> sobre o usuário (login, notificações, etc).
>
> \> O servidor <u>responde</u> apenas aquilo que o <u>cliente requisitou</u> e <u>nunca inicia</u> a comunicação :)
>
> \> HTTP não mantém informações entre requisições, isso quem faz são as sessões.
>
> <img src="./AULA 09 - PROTOCOLO HTTP/img/AULA 09 - HTTP/media/image4.png" style="width:3.9561in;height:1.79066in" />

**CONSOLE DE DEPURAÇÃO**

**\>** serve para monitorar, analisar e testar requisições e respostas de rede entre o seu navegador ou aplicativo e um servidor**.**

**\>** pela aba “network”(rede) nas ferramentas do desenvolvedor no browser

**STATUS CODE**

\> são <u>códigos</u> diretos do http exibidos no browser que qualificam a requisição

\> <span class="mark">200</span> - OK (funcionou)

\> <span class="mark">301</span> - Moved Permanently (redireciona a outra URL)

\> 404 - Not Found (cliente requisitou incorretamente)

\> 500 - Internal Server Error (erro interno de desenvolvimento)

\> Tipos mais comuns:

<img src="./AULA 09 - PROTOCOLO HTTP/img/AULA 09 - HTTP/media/image3.png" style="width:2.94271in;height:2.29167in" />

**PARÂMETROS DE REQUISIÇÃO**

**\> declaração do parâmetro:** ?nome_do_parametro=seu_valor&nome_do_outro_param=valor

\> especificam como e onde deve ser colocada a requisição

\>**GET**: envia os parâmetros visíveis na URL para buscar dados

\>recebe dados

\>**POST**: envia os dados ocultos no body da requisição para processar informações.

\> submeter dados no servidor

\>**DELETE**: Apagar um recurso específico do servidor.

\> **PUT**: Atualizar ou substituir um recurso inteiro no servidor.

<img src="./AULA 09 - PROTOCOLO HTTP/img/AULA 09 - HTTP/media/image1.png" style="width:2.63021in;height:1.29167in" />

site https que explica os códigos http:: [<u>httpstatusdogs</u>](https://httpstatusdogs.com/)

**O QUE É O REST:**

\> estilo de arquitetura de software para comunicação entre aplicações

<img src="./AULA 09 - PROTOCOLO HTTP/img/AULA 09 - HTTP/media/image2.png" style="width:4.26787in;height:2.40068in" />

\> aproveita da estrutura que o HTTP proporciona

\> **Cabeçalhos** (accept/content-type)

\> especificar a representação(JSON, XML,etc).

\> definidos na ordem **TIPO/SUBTIPO**

**\>EX:** <span class="mark">text/javascript</span>

<span class="mark">\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_—-----</span>

**HTTP2 - POR UMA WEB MAIS EFICIENTE**

**\>novidades da versão:**

\> os dados são criptografados (**TLS**) e compactados (**GZIP**) por padrão

\> cabeçalhos **statefull**

\> permitem que <u>apenas os cabeçalhos que mudem</u> sejam enviados a cada requisição

\>**serverPush**

\> o servidor pode empurrar certas respostas para o cliente antes mesmo delas serem requisitadas.

\> **multiplexação**

\>várias requisições com suas respostas ocorrendo ao mesmo tempo.
