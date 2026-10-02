**O QUE É O DOCKER?**

> \> plataforma que utiliza a tecnologia de **contêineres** para atenuar a implantação e utilização de **aplicações**
>
> \> serve para empacotar uma aplicação e todas as suas dependências em um único bloco chamado “**contêiner**”
>
> \> acaba com o problema de o código não funcionar em outros computadores, garantindo que o **código** rode em **qualquer** **lugar** .
>
> **NAMESPACES - ISOLAMENTOS DE PROCESSOS**
>
> \> **PID (Process ID):** Isola os números de identificação dos processos **\> NET (Network):** Isola as interfaces de rede, portas e tabelas de roteamento.
>
> **\> MNT (Mount):** Isola os pontos de montagem do sistema de arquivos. \> **IPC (Interprocess Communication):** Isola recursos de comunicação entre processos e memória compartilhada. **\> UTS (UNIX Time Sharing System):** Isola o nome do host e o domínio da rede. **\> USER:** Isola os IDs de usuário (UID) e de grupo (GID). **\> Cgroup:** Isola a visualização dos grupos de controle de recursos do sistema
>
> **COMO FUNCIONA O CONTÊINER?**
>
> \> são gerados através de **namespaces** (recurso q cria uma versão isolada do S.O para cada contêiner)
>
> \> para manter o **isolamento** de contextos e o versionamento de aplicações
>
> \> um contêiner **fecha** **automaticamente** se **não** há **processos** em execução nele

\> <u>IMAGENS</u> : carregam todas as configurações necessárias para executar um contêiner.

<img src="./AULA 11 - CONHECENDO O DOCKER/img/AULA 11 - GERANDO O DOCKER/media/image1.png" style="width:3.5704in;height:1.50677in" />

**COMANDOS CHAVE**

\> **docker run** \> cria e roda os contêineres em nosso host

\> **docker stop** \> para os contêineres

\> **docker start** \> re-executa o contêiner encerrado

\> **docker ps** / **docker container ls \>** mostra as características do contêiner ou do diretório

\> **docker exec -it** “#id Container \> executa um comando em um contêiner em execução

\> **docker port** \> visualiza como o mapeamento de portas de um container está sendo feito.

\> **docker pause** \> para pausar um container

> \> **docker unpause** \> para iniciar um container pausado;
>
> \> d**ocker history** \> lista as camadas de uma imagem Po
>
> \> **mkDir** \<nome da pasta\>/\<nome do arquivo\> \> cria um arquivo pelo terminal
>
> \> **cd** \> (change directory) navega entre os diretórios
>
> ory
>
> **FLAGS DO DOCKER**
>
> ajudam a controlar, personalizar de modo útil a execução dos comandos do Docker.
>
> **\> Execução e Modo de Terminal**

- **-a (all / todos)**: Mostra **todos** os containers, inclusive aqueles que já foram desligados ou parados.

<!-- -->

- \-**d** (**detach**): Roda o contêiner em segundo plano (background).

- **-it:** Junta -i (interativo) com -t (tty) para abrir um terminal funcional dentro do contêiner.

- **--rm:** Apaga o contêiner automaticamente assim que ele para de rodar.

- **--name**: Define um nome personalizado para o contêiner. \[[<u>1</u>](https://translate.google.com/translate?u=https://stackoverflow.com/questions/48368411/what-is-docker-run-it-flag&hl=pt&sl=en&tl=pt&client=sge), [<u>2</u>](https://www.youtube.com/watch?v=URTds0RHcHM), [<u>3</u>](https://www.youtube.com/watch?v=jHv--QWnfxQ&t=731), [<u>4</u>](https://www.hostinger.com/br/tutoriais/docker-cheat-sheet/), [<u>5</u>](https://gist.github.com/d297d374e1af46905b58)\] **\>Rede e Portas**

<!-- -->

- **-p (publish**): Mapeia uma porta do seu computador para uma porta do contêiner (ex: -p 8080:80).

- \-**-network:** Conecta o contêiner a uma rede específica do Docker.

- **--hostname:** Define um nome de host específico para o contêiner. \[[<u>1</u>](https://cursos.alura.com.br/forum/topico-duvida-flag-p-316536), [<u>2</u>](https://www.vivaolinux.com.br/artigo/Docker-Containers-em-Linux-parte-2/), [<u>3</u>](https://labex.io/pt/tutorials/docker-how-to-use-docker-container-run-command-to-manage-containers-555121), [<u>4</u>](https://www.hostinger.com/br/tutoriais/docker-cheat-sheet/)\] **\> Armazenamento e Dados**

- **-s (size / tamanho)**: Mostra o **tamanho** de disco que o container está usando.(tamanho real e virtual)

<!-- -->

- **-v (volume**): Cria ou conecta um volume ou diretório do seu computador para dentro do contêiner (ex: -v /host/path:/container/path).

- \-**-env** ou -**e:** Define variáveis de ambiente (ex: -e DB_HOST=localhost).

- **--env-file:** Lê variáveis de ambiente de um arquivo externo. \[[<u>1</u>](https://forums.docker.com/t/what-does-these-flag-do/43090), [<u>2</u>](https://www.youstable.com/pt/blog/usar-docker-no-linux/), [<u>3</u>](https://medium.com/@brunobaruffi/utilizando-docker-para-rodar-seu-projeto-java-8c9290eee197), [<u>4</u>](https://serverspace.com.br/support/help/o-que-e-um-dockerfile-guia-passo-a-passo-para-escrever-um-dockerfile/), [<u>5</u>](https://frankenphp.dev/pt-br/docs/config/)\]

- **\>Recursos e Sistema**

<!-- -->

- **-m** ou **--memory**: Limita o uso máximo de memória RAM pelo contêiner.

- **--cpus:** Limita a quantidade de núcleos de CPU que o contêiner pode usar.

- **--restart**: Define a regra de reinicialização automática (ex: --restart always)

> **VOLUMES - PERSISTIR OS DADOS** servem principalmente para **escrever os dados** em uma camada **persistente.** Vale saber: \> **Criar volume:** docker volume create meu-volume \> **Listar volumes:** docker volume ls
>
> **\> Ver detalhes:** docker volume inspect meu-volume \>**Apagar volume:** docker volume rm meu-volume \> \> **Limpar inutilizados:** docker volume prune \[[<u>1</u>](https://medium.com/@habbema/docker-volumes-network-5311bf74918c), [<u>2</u>](https://www.youtube.com/watch?v=TQmUeka6xzA), [<u>3</u>](https://www.youtube.com/watch?v=ZEzirQKCuzc&t=9)\]

**\> Bind mounts:** dependem da estrutura de pastas do host entrelaçadas ao contêiner

**docker run -it -–mount type=bind,source=/home/diretorio,target=/app nginx**

****

\> **volumes** - mais recomendadas por serem gerenciados pelo Docker, passando instruções através do docker \> **docker run -d --name meu-app -v meu-volume:/caminho/no/container imagem**

\> **tmpfs -** representados pela flag --**tmpfs** que atua como um volume temporário , salvando na memória \> **docker run –it –tmpfs=/caminho/no/container**

> **REDES DOCKER - COMUNICAÇÃO INTER-CONTAINERS \>BRIDGE:** driver padrão, cria uma rede interna no host compatível com resolução de nomes entre os containers (DNS) \>**HOST**: remove o isolamento entre o container e o sistema \>**NONE**:remove a interface de rede.
>
> \> docker network create \>começa uma rede de nossa autoria
>
> **DOCKER COMPOSE** \>ferramenta de **composição** e **coordenação** de **containers** \> evita executar cada comando individualmente nos containers \> gera arquivos de configuração no formato **ymls**
>
> link documentação Docker oficial: [<u>https://hub.docker.com/</u>](https://hub.docker.com/)
>
> link passo a passo com o docker: [<u>https://www.google.com/search?q=localhost%3A%2Fdocker+image%2F%3A80&sca_esv=182ba94e7364ae48&sxsrf=APpeQnuNfT8cmQWk51HnsTN3PKyxeU5q4A%3A1787323075724&source=chrome.ob&fbs=ABfTbFVGaQeaqnsRPI5sOMG32KszkLt6nAp8aiRKj5vMjqZApKYr2wv-EHakX1SS4JF8fY1_A0DfPLoyd61yD2Gjy0hF5xCGdMIu1T4OpsNqfBilMBy-wr_0h3_BvPs-ESj4m8vUTLVm3QwdIDM_SOkiQGWliErhL73ZM_upaweMkmglOqGhBN6R5o-JHPAPiSGsykRxDMw9Vd_IlrrIMnnXeI8EH36Hfw&aep=1&ntc=1&cs=1&sa=X&ved=2ahUKEwj-zpuo-bGWAxVPGbkGHU1UDS0Q2J8OegQIEBAD&biw=2560&bih=1232&dpr=0.75&atvm=2&mstk=AUtExfAT4RHKGZG3BtBFiQsIAqaS77zZEgPd7TpcJlOo08wrYkw3tFfxBR-HnExNtlw2MpEzRyO5dEhMgcxiyiSX7jV3ZiQ5BreACgXPf0twrDAfpgBz06ZZmg48EuSa_7oz4MFbxsD4AvWlLKC0G-sxUpE6ztBIeTF9uw4w5JC_v0y56anvFdTb2TP0mo3Msi2-T6Q8uOcby7XuOx6XP4vDWW55ZTlm5LNxJ8KmN35twnnY0NITL_ikQY952g&csuir=1&udm=50</u>](https://www.google.com/search?q=localhost%3A%2Fdocker+image%2F%3A80&sca_esv=182ba94e7364ae48&sxsrf=APpeQnuNfT8cmQWk51HnsTN3PKyxeU5q4A%3A1787323075724&source=chrome.ob&fbs=ABfTbFVGaQeaqnsRPI5sOMG32KszkLt6nAp8aiRKj5vMjqZApKYr2wv-EHakX1SS4JF8fY1_A0DfPLoyd61yD2Gjy0hF5xCGdMIu1T4OpsNqfBilMBy-wr_0h3_BvPs-ESj4m8vUTLVm3QwdIDM_SOkiQGWliErhL73ZM_upaweMkmglOqGhBN6R5o-JHPAPiSGsykRxDMw9Vd_IlrrIMnnXeI8EH36Hfw&aep=1&ntc=1&cs=1&sa=X&ved=2ahUKEwj-zpuo-bGWAxVPGbkGHU1UDS0Q2J8OegQIEBAD&biw=2560&bih=1232&dpr=0.75&atvm=2&mstk=AUtExfAT4RHKGZG3BtBFiQsIAqaS77zZEgPd7TpcJlOo08wrYkw3tFfxBR-HnExNtlw2MpEzRyO5dEhMgcxiyiSX7jV3ZiQ5BreACgXPf0twrDAfpgBz06ZZmg48EuSa_7oz4MFbxsD4AvWlLKC0G-sxUpE6ztBIeTF9uw4w5JC_v0y56anvFdTb2TP0mo3Msi2-T6Q8uOcby7XuOx6XP4vDWW55ZTlm5LNxJ8KmN35twnnY0NITL_ikQY952g&csuir=1&udm=50)
>
> **DD**
