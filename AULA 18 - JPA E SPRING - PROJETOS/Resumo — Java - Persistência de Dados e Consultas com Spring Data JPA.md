<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p><strong>☕ Java: Persistência de Dados e Consultas com Spring Data JPA</strong></p>
<p><em>Resumo por capítulo — projeto ScreenMatch · curso oficial Alura (Iasmin Araújo &amp; Jacqueline Oliveira)</em></p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p><strong>📋 Sumário dos capítulos</strong></p>
<p>● <strong>1.</strong> Evoluindo o projeto ScreenMatch</p>
<p>● <strong>2.</strong> Persistindo os dados da série</p>
<p>● <strong>3.</strong> Mapeando relacionamentos</p>
<p>● <strong>4.</strong> Buscando informações do banco</p>
<p>● <strong>5.</strong> Utilizando a linguagem de consulta da JPA</p>
<p>● <strong>6.</strong> Consolidando conhecimentos</p></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

| **① Evoluindo o projeto ScreenMatch** |
|---------------------------------------|

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p><strong>Contexto:</strong> este capítulo retoma o projeto ScreenMatch (app que consulta a API OMDb para buscar dados de séries) e o prepara para receber persistência em banco — sem isso ainda, os dados existiam só durante a execução.</p>
<p><strong>Evoluindo nossa aplicação Java</strong> — reorganização do código do console anterior: separar responsabilidades entre a classe principal (menu/fluxo) e as classes de modelo, preparando terreno para o Spring cuidar da persistência.</p>
<p><strong>Modelando categorias e séries</strong> — criação da entidade Serie e do enum Categoria (gêneros como AÇÃO, COMÉDIA, DRAMA), convertendo o texto de gênero vindo da API para um valor fixo e controlado no domínio da aplicação.</p>
<p><strong>Utilizando a classe mais completa</strong> — evolução do record que representa a resposta da API (DadosSerie), incorporando mais campos (temporadas, avaliação, atores, poster) para enriquecer o que é salvo no banco.</p>
<p><strong>Usando o ChatGPT para traduzir a sinopse</strong> — integração com a API do ChatGPT para traduzir automaticamente a sinopse (que vem em inglês da OMDb) antes de exibir/persistir — mostra como consumir duas APIs externas na mesma aplicação.</p>
<blockquote>
<p>💡 <strong>Ponto-chave:</strong> antes de existir persistência, é preciso ter um modelo de domínio bem definido — é isso que este capítulo prepara.</p>
</blockquote></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

| **② Persistindo os dados da série** |
|-------------------------------------|

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>Este é o salto do JDBC/JPA "crus" (vistos nos cursos anteriores da trilha) para o <strong>Spring Data JPA</strong> — a camada que elimina quase todo o boilerplate de persistência.</p>
<ul>
<li><blockquote>
<p>Adicionar a dependência spring-boot-starter-data-jpa + driver do banco (MySQL) no pom.xml</p>
</blockquote></li>
<li><blockquote>
<p>Configurar a conexão em application.properties (ou .env, como vimos ser recomendado no fórum do curso)</p>
</blockquote></li>
<li><blockquote>
<p>Anotar a classe de domínio com @Entity e @Id/@GeneratedValue</p>
</blockquote></li>
</ul>
<blockquote>
<p>public interface SerieRepository extends JpaRepository&lt;Serie, Long&gt; {<br />
// CRUD completo já vem pronto: save(), findAll(), findById(), delete()...<br />
}</p>
</blockquote>
<p>Basta uma interface <strong>vazia</strong> estendendo JpaRepository&lt;Entidade, TipoDoId&gt; para ganhar um CRUD inteiro — nenhuma implementação manual, nenhum EntityManager explícito.</p>
<blockquote>
<p>💡 <strong>Ponto-chave:</strong> onde antes (JDBC/JPA puro) você escrevia SQL ou chamava entityManager.persist() manualmente, agora é só repositorio.save(serie).</p>
</blockquote></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

| **③ Mapeando relacionamentos** |
|--------------------------------|

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>O projeto ganha uma segunda entidade, Episodio, relacionada a Serie — cada série tem vários episódios, então é uma relação <strong>um-para-muitos</strong>.</p>
<blockquote>
<p>// Lado "muitos" — dono da relação, guarda a FK<br />
@ManyToOne<br />
private Serie serie;<br />
<br />
// Lado "um" — inverso, não é dono<br />
@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)<br />
private List&lt;Episodio&gt; episodios = new ArrayList&lt;&gt;();</p>
</blockquote>
<ul>
<li><blockquote>
<p>mappedBy evita que a JPA tente criar duas chaves estrangeiras para o mesmo relacionamento</p>
</blockquote></li>
<li><blockquote>
<p>cascade = CascadeType.ALL permite salvar a série <strong>e</strong> todos os seus episódios com uma única chamada a repositorio.save(serie)</p>
</blockquote></li>
</ul>
<blockquote>
<p>💡 <strong>Ponto-chave:</strong> cuidado com toString() em relacionamentos bidirecionais — se Serie imprime seus episódios e Episodio imprime sua série de volta, você cai numa recursão infinita.</p>
</blockquote></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

| **④ Buscando informações do banco** |
|-------------------------------------|

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>A grande sacada do Spring Data JPA: <strong>consultas derivadas</strong> (query methods) — o Spring interpreta o nome do método e monta o SQL sozinho, sem você escrever nada além da assinatura.</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th>Palavra-chave</th>
<th>Exemplo</th>
</tr>
<tr class="odd">
<th>Containing / IgnoreCase</th>
<th>findByTituloContainingIgnoreCase(String)</th>
</tr>
<tr class="header">
<th>GreaterThanEqual</th>
<th>findByAvaliacaoGreaterThanEqual(Double)</th>
</tr>
<tr class="odd">
<th>Top N + OrderBy</th>
<th>findTop5ByOrderByAvaliacaoDesc()</th>
</tr>
<tr class="header">
<th>Between</th>
<th>findByAnoLancamentoBetween(int, int)</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
<blockquote>
<p>⚠️ <strong>Cuidado clássico:</strong> o nome do método precisa bater <strong>exatamente</strong> com os atributos da entidade (maiúsculas incluídas) — findTop5ByOrderbyAvaliacao (com "b" minúsculo) já quebra, tem que ser OrderBy.</p>
</blockquote></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

| **⑤ Utilizando a linguagem de consulta da JPA** |
|-------------------------------------------------|

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>Quando a consulta fica complexa demais para caber no nome do método, entra o <strong>JPQL</strong> via @Query — a mesma linguagem já vista no resumo de JPA/Hibernate, agora aplicada dentro dos repositórios do Spring Data.</p>
<blockquote>
<p>public interface SerieRepository extends JpaRepository&lt;Serie, Long&gt; {<br />
<br />
@Query("SELECT s FROM Serie s WHERE s.categoria = :categoria")<br />
List&lt;Serie&gt; buscarPorCategoria(@Param("categoria") Categoria categoria);<br />
}</p>
</blockquote>
<ul>
<li><blockquote>
<p>Parâmetros nomeados (:categoria + @Param) — mais legíveis que posicionais (?1)</p>
</blockquote></li>
<li><blockquote>
<p>Quando nem o JPQL resolve, existe nativeQuery = true para escrever SQL puro específico do banco — último recurso, não o padrão</p>
</blockquote></li>
</ul>
<blockquote>
<p>💡 <strong>Ponto-chave:</strong> a ordem de prioridade é sempre <strong>query method → JPQL → SQL nativo</strong> — vá para a próxima opção só quando a anterior não conseguir expressar o que você precisa.</p>
</blockquote></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

| **⑥ Consolidando conhecimentos** |
|----------------------------------|

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<thead>
<tr class="header">
<th><p>Capítulo de fechamento: o projeto ScreenMatch reúne, num único fluxo funcional, tudo visto nos capítulos anteriores.</p>
<ul>
<li><blockquote>
<p>Modelo de domínio bem definido (Cap. 1)</p>
</blockquote></li>
<li><blockquote>
<p>Persistência via Spring Data JPA, sem SQL manual (Cap. 2)</p>
</blockquote></li>
<li><blockquote>
<p>Relacionamento Serie ↔︎ Episodio mapeado corretamente (Cap. 3)</p>
</blockquote></li>
<li><blockquote>
<p>Consultas derivadas para os filtros mais comuns (Cap. 4)</p>
</blockquote></li>
<li><blockquote>
<p>JPQL para as consultas que precisam de mais controle (Cap. 5)</p>
</blockquote></li>
</ul>
<p>É também o ponto de virada da trilha: o próximo curso da formação (<em>Java e JPA: consultas avançadas, performance e modelos complexos</em>) parte exatamente daqui.</p>
<blockquote>
<p>🎯 <strong>Ponto-chave:</strong> se algum dos 5 blocos acima ainda não fez sentido isoladamente, vale revisar aquele capítulo específico antes de seguir — este é o momento em que tudo precisa se encaixar junto.</p>
</blockquote></th>
</tr>
</thead>
<tbody>
</tbody>
</table>

Fonte: ementa oficial do curso "Java: persistência de dados e consultas com Spring Data JPA" — Alura (16h, 83 atividades).
