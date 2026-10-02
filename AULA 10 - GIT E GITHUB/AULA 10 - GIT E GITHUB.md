**GIT E GITHUB**

**O Q É O GIT?**

\> sistema de controle de versões

\> repositórios distribuídos

\> busca, adiciona, edita e monitora arquivos

\> permite o trabalho em equipe

**COMANDOS GIT - TERMINAL LINUX**

**\>** **<span class="mark">git</span>**: abre o git pelo terminal

\> **<span class="mark">git config –global.user name</span>** ou <span class="mark">email:</span> identifica o usuário pro Git

\> **<span class="mark">git init:</span>** inicializa um repositório no seu diretório atual

\> <span class="mark">git init –bare</span>: guarda só os metadados e histórico das versões

\>EX:

\> <span class="mark">git status:</span> Para saber se o Git está vendo esse arquivo e se ele já foi salvo

\> **<span class="mark">sudo apt install ‘nome do pacote’</span>**: instala pacotes no linux

\> **<span class="mark">ls</span>**: lista todas as pastas/arquivos dentro do diretório

\> **<span class="mark">git --version</span>**: diz qual a versão do git instalada

\> **<span class="mark">pwd</span>**: imprime o diretório no qual estamos trabalhando

\> **<span class="mark">git add “nome do arquivo”:</span>** adiciona nosso arquivo dentro da pasta

\> <span class="mark">git log</span> vê o histórico do nossos commits(atualizações)

\> alguns tipos: <span class="mark">git log –oneline</span>: lista todos commits em uma só linha

<span class="mark">git log –pretty</span>: formatados usando siglas significativas

outros git log \> [<u>https://devhints.io/git-log</u>](https://devhints.io/git-log)

\><span class="mark">git stash</span> para armazenar temporariamente algumas de nossas alterações.

\> **<span class="mark">git diff</span>**: Mostra o que mudou na pasta de trabalho e ainda não foi preparado para o commit

**COMMIT NO GIT:**

\> **<span class="mark">git commit -m</span>** “mensagem da operação feita”

**QUANDO COMMITAR:**

\> a cada alteração relevante no arquivo/diretório

\> **nunca** commitar um código com falhas

**-**

**MANIPULANDO DIRETÓRIOS**

\> **no terminal:**

\> **<span class="mark">~</span>** indica que estou no diretório home

\> <span class="mark">touch</span> : cria novas pastas

**TRUQUE “TAB”**

\> evita passar nomes de diretórios inexistentes por case sensitive

\> Digite o início do comando: <span class="mark">cd ~/Downloads/</span>

\> Digite apenas as primeiras letras da pasta: <span class="mark">cd EST</span> \> Aperte a tecla **Tab** no seu teclado

\> o terminal Linux completa a pasta a ser aberta

**REPOSITÓRIO REMOTOS**

\> são repositórios externos, não permeados pelo Git ou em outra máquina

\><span class="mark">git remote:</span> gerencia conexões com repositórios externos.

\> <span class="mark">**git clone** Realiza a cópia de um repositório **remoto** para uma pasta **local**, trazendo todo o histórico de mudanças, conhecidos como commits, e todos os arquivos **desde o inicio**.</span>

<span class="mark">\> git remote add</span> Permite interligar um repositório **local** já existente a um repositório **remoto**, assim podemos começar a enviar mudanças e commits para esse repositório remoto com o **git push.**

**\> <span class="mark">git push</span>** Envia todas as alterações para o repositório remoto, isso inclui arquivos novos, mudanças em arquivos e até mensagens de commits.

\> **<span class="mark">git pull</span>** Traz todas as alterações que estão presentes no repositório remoto, e se diferencia do **git clone** pelo fato de não criar um novo repositório local que tem que trazer todo o conteúdo, trazendo **apenas as alterações** que o repositório local ainda não tem, acelerando o trabalho para repositórios muito grandes.

**BRANCHES**

**\>** <span class="mark">("ramos") são utilizados para desenvolver <u>funcionalidades isoladas</u> umas das outras. A <u>*branch* master</u> é a <u>*branch* "padrão"</u> quando você cria um repositório.</span>

<span class="mark">\> **git merge** entrelaça o commit entre as branches, mantendo os repositórios integrados em ordem</span>

<span class="mark">\> **git rebase** atualiza a branch Master com os commits da branch de trabalho, apagando então a branch de trabalho ao final do comando.</span>

\> O **merge** junta os trabalhos e gera um merge commit. O **rebase** aplica os *commits* de outra *branch* na *branch* atual.

**DESFAZENDO ALTERAÇÕES**

**\><span class="mark">git checkout: </span>**<span class="mark">desfaz se ainda não foi adicionada ao index</span>

<span class="mark">\> **git reset:** desfaz depois de add index e antes do commit</span>

<span class="mark">\> **git revert:** comando para desfazer um commit indesejado</span>

<span class="mark"></span>

**<span class="mark">COMANDOS EXTRAS</span>**

**<span class="mark">\></span>** tag: uma referência estática a um ponto específico na história do seu repositório.

**<span class="mark">\></span> release<span class="mark">: </span>**versão específica do seu projeto que é publicada para download ou deploy.
