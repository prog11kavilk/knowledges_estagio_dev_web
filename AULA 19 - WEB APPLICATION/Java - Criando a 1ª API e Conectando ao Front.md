***JAVA: APLICAÇÃO WEB FULL-STACK (API’s rest, DATABASE E JAVA)***

> \> Tal material tem como objetivo <u>instruir</u> e <u>demonstrar</u> ao programador as propriedades de <u>integração</u> entre o <u>back-end</u> e <u>front-end.</u>

**1. TRANSFORMANDO A APLICAÇÃO EM API**

> **\>** objetivo do módulo: sair da aplicação de console do ScreenMatch e expor os dados via HTTP, seguindo a arquitetura **MVC** (Model-View-Controller)
>
> **\>** dependência Maven necessária: **spring-boot-starter-web** — traz o servidor embutido (Tomcat) e todo o suporte a requisições HTTP
>
> **\<dependency\>  
> \<groupId\>org.springframework.boot\</groupId\>  
> \<artifactId\>spring-boot-starter-web\</artifactId\>  
> \</dependency\>**
>
> **@RestController** → marca a classe como controlador que devolve dados diretamente (não uma página HTML); equivale a **@Controller + @ResponseBody**
>
> **\> @GetMapping("/caminho")** → mapeia um método para responder requisições HTTP GET nesse endereço
>
> **@RestController  
> public class SerieController {  
> @GetMapping("/")  
> public String mensagem() {  
> return "Bem-vindo ao ScreenMatch!";  
> }  
> }**
>
> **\>** ao rodar a aplicação, o Spring sobe um servidor local (porta **8080** por padrão) e o endpoint já pode ser acessado direto no navegador

**ANOTAÇÕES PRINCIPAIS DO SPRING WEB**

> **\> @RestController** → habilita a classe a responder requisições REST, serializando o retorno em JSON automaticamente (via Jackson)
>
> **\> @GetMapping / @PostMapping / @PutMapping / @DeleteMapping** → mapeiam os respectivos verbos HTTP (GET, POST, PUT, DELETE)
>
> **\> @PathVariable** → captura um valor dentro da própria URL, ex: **/series/{id}**
>
> **\> @RequestParam** → captura um valor enviado por query string, ex: **?nome=...**
>
> **\> @CrossOrigin(origins = "...")** → libera o acesso de um domínio de front-end específico, contornando o bloqueio padrão de CORS

**2. FORNECENDO DADOS PARA O FRONT**

> **\>** devolver uma entidade JPA direto no Controller (ex: **List\<Serie\>**) até funciona — o Spring serializa em JSON automaticamente — mas expõe campos internos e pode gerar relacionamentos circulares
>
> **\>** solução: **DTO** (Data Transfer Object) — um **record** enxuto que carrega só os dados que o front realmente precisa
>
> **public record DadosListagemSerie(Long id, String titulo, String categoria) {  
> public DadosListagemSerie(Serie serie) {  
> this(serie.getId(), serie.getTitulo(), serie.get Categoria().toString());  
> }  
> }**
>
> **\>** conversão da lista de entidades para DTOs com Stream API: **repositorio.findAll().stream().map(DadosListagemSerie::new).toList()**
>
> **@GetMapping("/series")  
> public List\<DadosListagemSerie\> obterSeries() {  
> return repositorio.findAll().stream()  
> .map(DadosListagemSerie::new).toList();  
> }**
>
> **\>** busca de uma série específica: **@PathVariable Long id** em **GET /series/{id}**
>
> **\>** tratamento de id inexistente: **Optional\<Serie\>** + **ResponseEntity**, devolvendo **404 (Not Found)** em vez de erro 500
>
> **@GetMapping("/series/{id}")  
> public ResponseEntity detalhar(@PathVariable Long id) {  
> Optional\<Serie\> serie = repositorio.findById(id);  
> if (serie.isPresent()) {  
> return ResponseEntity.ok(new DadosSerie(serie.get()));  
> }  
> return ResponseEntity.notFound().build();  
> }**

**3. MAPEANDO A PÁGINA INICIAL DO SCREENMATCH**

> **\>** o front do projeto (HTML, CSS e JavaScript) foi dado prontamente no curso… e consome a API via **fetch()**
>
> **\>** front e back rodam em endereços diferentes (ex: front na porta **5500** via Live Server, back na porta **8080**) — simula um cenário real de aplicações desacopladas
>
> **\>** por rodarem em origens diferentes, o navegador bloqueia a resposta por política de mesma origem — erro de **CORS** no console do navegador
>
> **\>** solução aplicada: **@CrossOrigin(origins = "http://127.0.0.1:5500")** no Controller, autorizando explicitamente aquele domínio a consumir a API
>
> **@RestController  
> @CrossOrigin(origins = "http://127.0.0.1:5500")  
> public class SerieController {  
> // endpoints da API  
> }**
>
> **\>** no front, o **fetch("http://localhost:8080/series")** chama o endpoint, recebe o JSON e monta dinamicamente os cards de séries na página inicial

**4. DETALHANDO O SCREENMATCH**

> **\>** endpoint de detalhes: **GET /series/{id}**, retornando um DTO mais completo (sinopse, avaliação, atores, poster)
>
> **\>** temporadas e episódios entram no detalhe — DTOs aninhados, um **record** dentro do outro, representando cada temporada com sua lista de episódios
>
> **public record DadosTemporada(Integer numero, List\<DadosEpisodio\> episodios) {}  
>   
> public record DadosEpisodio(String titulo, Integer numeroEpisodio) {  
> public DadosEpisodio(Episodio episodio) {  
> this(episodio.getTitulo(), episodio.getNumeroEpisodio());  
> }  
> }**
>
> **\>** no repository, um novo método (derivado ou **@Query**) filtra os episódios daquela série, agrupados por temporada
>
> **\>** no front, o clique em "ver detalhes" de um card dispara um novo **fetch()** para o endpoint de detalhe e popula a página de detalhes dinamicamente

**5. CONSOLIDANDO CONHECIMENTOS**

> **\>** revisão da arquitetura MVC completa: **Model** (entidades + repositories), **Controller** (endpoints REST), **View** (agora um front separado, não mais páginas geradas pelo servidor)
>
> **\>** boas práticas reforçadas: nunca expor entidade JPA diretamente — sempre devolver **DTO**; usar **ResponseEntity** para controlar o status HTTP; nomear endpoints no plural (**/series**, não **/serie**)
>
> **\>** o ScreenMatch termina o curso como uma API REST funcional, consumida por um front real — essa base é reaproveitada nos próximos cursos da formação (segurança, deploy em nuvem, etc.)

**PROJETO DE REFERÊNCIA**

> **\>** aplicação: **ScreenMatch** (Spring Boot + Spring Web + Spring Data JPA), API REST consumida por um front em HTML/CSS/JS — repositório oficial: alura-cursos/3356-java-screenmatch-web

**LINKS ÚTEIS**

> **\>** Curso: Java — criando sua primeira API e conectando ao front (Alura) — [<u>https://www.alura.com.br/curso-online-java-criando-primeira-api-conectando-front</u>](https://www.alura.com.br/curso-online-java-criando-primeira-api-conectando-front)
>
> **\>** Documentação oficial Spring Framework — CORS — [<u>https://docs.spring.io/spring-framework/reference/web/webmvc-cors.html</u>](https://docs.spring.io/spring-framework/reference/web/webmvc-cors.html)
>
> **\>** MDN — Cross-Origin Resource Sharing (CORS) — [<u>https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/CORS</u>](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/CORS)
