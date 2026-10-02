***API* *REST***

\> Tipo de <u>interface de programação</u> que permite a <u>comunicação</u> <u>entre</u> diferentes <u>sistemas</u> pela internet usando o <u>protocolo HTTP</u>

\> O cliente faz uma **requisição** (request) e o servidor devolve uma **resposta** (response)

\> As ações em uma API rest são processadas por comandos do HTTP:

\> **GET:** Para **buscar** ou ler dados.

> **\> POST:** Para **criar** um novo dado. **\> \> PUT** ou **PATCH:** Para **atualizar** um dado existente.
>
> **\> DELETE:** Para **apagar** um dado.
>
> \> **Vantagens** \> independência, simplicidade e escalabilidade.

***MAVEN***

**\> CONCEITOS BÁSICOS**

> \> uma ferramenta de <u>GERENCIAMENTO</u> de <u>DEPENDÊNCIAS</u> de <u>PROJETOS</u>
>
> \> advanced settings:
>
> \> **GroupId**: [<u>br.com.sonner sistemas</u>](http://br.com) - domínio da organização
>
> \> **ArtifactId:** nome da aplicação/projeto em construção
>
> \> estrutura do projeto gerado com Maven:
>
> <img src="./AULA 15 - SPRINGBOOT FRAMEWORK/img/API s REST E SPRINGBOOT/media/image1.png" style="width:2.70313in;height:2.1996in" />

**\> LEMBRE-SE DISSO:**

> **\> Gerenciador de Dependências:** Ele serve para baixar e organizar as bibliotecas externas que seu projeto precisa, evitando que você baixe arquivos .**jar** manualmente na internet.
>
> **\> Arquivo pom.xml:** É o coração do Maven. Funciona como a "lista de compras" e configurações gerais do seu projeto Java.
>
> **\> Tags**
>
> **<span class="mark">\<dependency\></span>:** É o bloco de código que você cola dentro do pom.xml para dizer ao Maven qual biblioteca quer usar (ex: driver do banco de dados).
>
> **<span class="mark">\<scope\></span>**: Define **quando** a biblioteca será usada (ex: test apenas para testes, provided se o servidor já possuir a biblioteca
>
> **<span class="mark">\<plugin\></span>** **conjunto de tarefas (chamadas de *goals*)** que realizam ações específicas no projeto.
>
> **\> Repositório Central:** O Maven busca essas bibliotecas automaticamente em um servidor na nuvem chamado *Maven Central* e salva no seu computador.
>
> **\> Comando de Build:** O principal comando que você usará (via terminal ou botão na IDE) é o mvn clean package, que limpa o projeto e gera o arquivo final executável

***SPRING BOOT***

**\> O QUE É:**

\><u>framework</u> (coleção de reutilizáveis) de código aberto baseado em <u>Java</u>

> \>que <u>facilita</u> e agiliza a criação de <u>aplicações web</u> e <u>microsserviços</u>
>
> \> elimina necessidade de realizar configurações extensas e manuais
>
> \> **SUAS PROPRIEDADES**
>
> \> **autoconfiguração**: detecta as bibliotecas que você adicionou ao projeto e configura automaticamente os componentes necessários
>
> \> **Servidor Embutido:** Ele já vem com servidores web embutidos, possibilitando rodar a aplicação diretamente de um arquivo executável (.jar)
>
> \> **Abordagem especializada**: traz padrões de mercado e as melhores pŕaticas de decisões de arquitetura.
>
> \> **Gerenciamento de Dependências**: através de pacotes, é possível agrupar as dependências do Maven para correlacionar tudo.
>
> **\> COMO UTILIZAR:**
>
> \> a forma mais eficiente é pelo gerenciador de projetos (Maven/gradle/outros)
>
> \> inicializar a classe principal para carregar o ecossistema do Spring Boot;
>
> \> envolvem <u>toda</u> a nossa aplicação, <u>abstraindo</u> processos repetitivos
>
> \> ***Anotações***: instruem o framework a configurar automaticamente, gerenciar componentes e mapear requisições sem código repetitivo
>
> \>EX: **@Service**: Indica que a classe pertence à camada de serviços (regras de negócio).
>
> **@RestController**: Define uma classe controladora para APIs REST
>
> **@RequestMapping**: Mapeia URLs gerais para classes ou métodos controladores.
>
> **@Autowired**: Injeta a dependência ligada ao Spring em um campo ou construtor.
>
> **\> LEMBRE-SE DISSO:**
>
> **\> Inversão de Controle (IoC) e Injeção de Dependências:** O framework gerencia a criação e a execução dos objetos do seu código. Você não precisa ficar dando new Objeto() o tempo todo.
>
> **\> Componentes (@Component, @Service, @Repository):** Anotações que você coloca em cima das suas classes para avisar ao Spring: *"Essa classe é sua, gerencie ela para mim"*.
>
> **\> Controllers (@RestController):** Classes responsáveis por criar as portas de entrada da sua aplicação (URLs/Endpoints) e receber os verbos HTTP (GET, POST, etc.).
>
> **\> Spring Data JPA:** A ferramenta do Spring que traduz código Java diretamente em comandos SQL, permitindo salvar e buscar dados no banco sem escrever queries complexas manualmente.
>
> **\> Application Properties:** Um arquivo de texto simples (application.properties ou .yml) onde você coloca configurações da aplicação, como a senha do banco de dados ou a porta onde o servidor vai rodar.
>
> **\>Métodos/Interfaces Java genéricos no Intellij: <span class="mark">public class Caixa\<T\> {...}</span>**
