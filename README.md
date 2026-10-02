# Knowledges · Estágio em Desenvolvimento Web

Este repositório reúne, de forma sucinta, os módulos de desenvolvimento que estudei e pratiquei durante o estágio:

- Conexões e testes HTTP
- Controle de projetos com Git e GitHub
- Docker
- Gerenciamento de projetos com Maven
- Bancos de dados relacionais com SQL (PostgreSQL, SQL Server…)
- Java e frameworks: POO, JPA, Hibernate, Spring Boot
- Front-end: HTML, CSS e JavaScript
- Endpoints, URLs e APIs REST

Cada pasta corresponde a uma aula ou módulo, com as anotações em Markdown e os exercícios e projetos feitos na prática.

> Estou aberto a sugestões e acréscimos de todos! Abra uma *issue* ou um *pull request*.

## Índice

| Aula | O que estudei | Projetos |
|---|---|---|
| [AULA 01 - APRENDENDO JAVASCRIPT](./AULA%2001%20-%20APRENDENDO%20JAVASCRIPT) | Lógica com JavaScript: variáveis, condicionais, template strings, ternário | Jogo do número secreto, desafios |
| [AULA 02 - CONCEITOS DE HTML](./AULA%2002%20-%20CONCEITOS%20DE%20HTML) | HTML + Canvas: desenho, funções, eventos de clique, `setInterval` | Jogo do alvo, animações, bandeira |
| [AULA 03 - JOGO PONG NO JAVASCRIPT](./AULA%2003%20-%20JOGO%20PONG%20NO%20JAVASCRIPT) | Jogo com p5.js: colisão, placar, sons | Pong (JS e Scratch) |
| [AULA 04 - LAÇOS E LISTAS EM JS](./AULA%2004%20-%20LA%C3%87OS%20E%20LISTAS%20EM%20JS) | Arrays, `for`, refatoração de funções | Jogo de travessia (gabarito final) |
| [AULA 05 E 06 - HTML5 E CSS3](./AULA%2005%20E%2006%20-%20HTML5%20E%20CSS3) | Tags e semântica, box model, flexbox, variáveis CSS, `:hover` | Portfólios e página de projeto |
| [AULA 07 - RESPONSIVIDADE E PUBLICAÇÃO DE PROJETOS](./AULA%2007%20-%20RESPONSIVIDADE%20E%20PUBLICA%C3%87%C3%83O%20DE%20PROJETOS) | Unidades relativas, media queries, publicação no GitHub | Portfólio pessoal |
| [AULA 08 - AVANÇANDO EM JS](./AULA%2008%20-%20AVAN%C3%87ANDO%20EM%20JS) | DOM: `querySelector`, eventos, `classList`, laços | Teclado de sons (Drum Sounds) |
| [AULA 09 - PROTOCOLO HTTP](./AULA%2009%20-%20PROTOCOLO%20HTTP) | HTTP/HTTPS, DNS, requisição e resposta, status codes, verbos, REST, HTTP/2 | — |
| [AULA 10 - GIT E GITHUB](./AULA%2010%20-%20GIT%20E%20GITHUB) | Commits, branches, merge × rebase, remotos, desfazer alterações | Repositório de exercício (`git init --bare`) |
| [AULA 11 - CONHECENDO O DOCKER](./AULA%2011%20-%20CONHECENDO%20O%20DOCKER) | Imagens, contêineres, flags, volumes, redes, Compose | Dockerfile de exemplo |
| [AULA 12 - APRENDENDO JAVA](./AULA%2012%20-%20APRENDENDO%20JAVA) | JVM/JDK, sintaxe, POO, coleções, exceções, consumo de APIs e JSON | byteBank, menuBanco, reprodutor de músicas, produtos, entre outros |
| [AULA 13 - JAVA MODERNO E TDD](./AULA%2013%20-%20JAVA%20MODERNO%20E%20TDD) | Lambdas e streams, Maven, API REST e Spring Boot | screenmatch-appSpring, maven-produtos |
| [AULA 14 - JAVA - REFATORAÇÃO E BOAS PRÁTICAS](./AULA%2014%20-%20JAVA%20-%20REFATORA%C3%87%C3%83O%20E%20BOAS%20PR%C3%81TICAS) | TDD com JUnit e Mockito, refatoração | carrinho-de-compras-tdd, adopet |
| [AULA 15 - SPRINGBOOT FRAMEWORK](./AULA%2015%20-%20SPRINGBOOT%20FRAMEWORK) | Spring Boot, IoC/DI, anotações, `application.properties` | Pesquisa CEP, Tabela FIPE, spring-boot-myapplication |
| [AULA 16 - DATABASES COM SQL](./AULA%2016%20-%20DATABASES%20COM%20SQL) | SQL (DDL, DML, DQL, DCL, TCL), JOINs, JDBC | database_postgres, byte-bank JDBC |
| [AULA 17 - JAVA JPA E HIBERNATE](./AULA%2017%20-%20JAVA%20JPA%20E%20HIBERNATE) | ORM, entidades, EntityManager, estados, relacionamentos, JPQL | alunos-projetoJPA |
| [AULA 18 - JPA E SPRING - PROJETOS](./AULA%2018%20-%20JPA%20E%20SPRING%20-%20PROJETOS) | Spring Data JPA: repositórios, query methods, `@Query` | back all |
| [AULA 19 - WEB APPLICATION](./AULA%2019%20-%20WEB%20APPLICATION) | API REST com Spring Web, DTOs, `ResponseEntity`, CORS e front com `fetch` | Mitraseeks (back Spring + front HTML/CSS/JS) |
| [AULA 20 - JASPER - RELATÓRIOS FUNCIONAIS](./AULA%2020%20-%20JASPER%20-%20RELAT%C3%93RIOS%20FUNCIONAIS) | Relatórios com JasperReports em Java | — |

## Como navegar

- As anotações de cada aula estão em arquivos `.md` dentro da pasta (convertidos dos meus resumos).
- Projetos Java/Spring abrem no IntelliJ IDEA como projeto Maven (`pom.xml`).
- Projetos front-end abrem direto no navegador (`index.html`) ou com o Live Server do VS Code.
- Projetos que consomem APIs externas leem as chaves de variáveis de ambiente. Nenhuma chave fica no código.

## Licença

[MIT](LICENSE)
