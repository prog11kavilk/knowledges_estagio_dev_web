SQL - GERENCIAMENTO DE DADOS

SQL (**Structured Query Language**) é a linguagem padrão para gerenciar e consultar bancos de dados relacionais, onde os dados são organizados em tabelas formadas por linhas e colunas.

O que é um Banco de Dados Relacional?

- **Tabelas:** Estruturas compostas por linhas (registros) e colunas (campos).

- **Chave Primária (Primary Key):** Identificador único para cada linha em uma tabela.

- **Chave Estrangeira (Foreign Key):** Campo que conecta uma tabela à chave primária de outra.

- **Relacionamentos:** Associações entre tabelas do tipo 1 para 1 (1:1), 1 para muitos (1:N) ou muitos para muitos (N:N).

> *<u>Principais Categorias de Comandos SQL</u>*
>
> **1. DDL (Data Definition Language) - Definição de Dados**
>
> Controlam a estrutura do banco de dados. \[[<u>1</u>](https://www.devmedia.com.br/guia/guia-completo-de-sql/38314)\]

- **CREATE**: Cria novos objetos (bancos, tabelas, índices).

- **ALTER**: Modifica a estrutura de tabelas existentes.

- **DROP**: Remove tabelas ou bancos inteiros.

- **TRUNCATE**: Apaga todos os dados de uma tabela mantendo sua estrutura. \[[<u>1</u>](https://www.estrategiaconcursos.com.br/blog/banco-dados-principais-comandos-sql/), [<u>2</u>](https://4linux.com.br/comandos-basicos-linguagem-sql/), [<u>3</u>](https://www.devmedia.com.br/guia/guia-completo-de-sql/38314)\]

> **2. DQL (Data Query Language) - Consulta de Dados**
>
> Usado para recuperar informações. \[[<u>1</u>](https://www.devmedia.com.br/guia/guia-completo-de-sql/38314)\]

- **SELECT**: Extrai dados de uma ou mais tabelas.

  - Cláusulas comuns: FROM, WHERE (filtra linhas), **GROUP BY** (agrupa dados), **HAVING** (filtra grupos), ORDER BY (ordena resultados).

  - Junções (**JOIN**): **INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL JOIN** para combinar dados de múltiplas tabelas. <img src="./AULA 16 - DATABASES COM SQL/img/SQL - GERENCIAMENTO DE DADOS/media/image1.png" style="width:3.75431in;height:2.60309in" />

> **3. DML (Data Manipulation Language) - Manipulação de Dados**
>
> Modificam o conteúdo das tabelas. \[[<u>1</u>](https://www.estrategiaconcursos.com.br/blog/banco-dados-principais-comandos-sql/)\]

- **INSERT**: Insere novas linhas de dados.

- **UPDATE**: Atualiza dados já existentes.

- **DELETE**: Remove linhas específicas de uma tabela. \[[<u>1</u>](https://www.youtube.com/watch?v=72aA00MOsJs&t=391)\]

> **4. DCL (Data Control Language) - Controle de Acesso**
>
> Gerenciam permissões e segurança. \[[<u>1</u>](https://www.estrategiaconcursos.com.br/blog/banco-dados-principais-comandos-sql/)\]

- **GRANT**: Concede privilégios de acesso aos usuários.

- **REVOKE**: Remove privilégios concedidos. \[[<u>1</u>](https://www.estrategiaconcursos.com.br/blog/banco-dados-principais-comandos-sql/)\]

> **5. TCL / DTL (Transaction Control Language) - Controle de Transações**
>
> Garantem a integridade das operações em bloco. \[[<u>1</u>](https://www.alura.com.br/artigos/o-que-e-sql)\]

- **COMMIT**: Salva as alterações de uma transação permanentemente.

- **ROLLBACK**: Reverte alterações caso ocorra algum erro.
