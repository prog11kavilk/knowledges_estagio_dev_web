# **JPA & Hibernate**

*Persistência orientada a objetos — resumo de referência baseado na ementa oficial do curso "Java e JPA: Persista seus objetos com a JPA2 e Hibernate" (Alura, instrutor Leonardo Cordeiro)*

## **1. JPA é especificação, Hibernate é implementação**

JPA (Java Persistence API) é um conjunto de interfaces/regras definido pela plataforma Java — não faz nada sozinha. Hibernate é a implementação mais usada dessa especificação (existem outras, como EclipseLink).

Isso significa que seu código fala com a JPA (@Entity, EntityManager, etc.), e o Hibernate trabalha por trás traduzindo isso em SQL — trocar de implementação, em teoria, não deveria exigir reescrever as entidades.

## **2. Os problemas do JDBC puro que motivaram a JPA**

Com JDBC, cada mudança no banco exige escrever SQL manualmente e mapear ResultSet para objetos "na mão" — repetitivo e propenso a erro.

JPA resolve isso com **mapeamento objeto-relacional (ORM)**: uma classe Java vira uma tabela, um atributo vira uma coluna, automaticamente. Você continua podendo escrever SQL quando precisa (JPQL/Criteria/native query), mas o CRUD básico deixa de exigir isso.

## **3. Configuração: persistence.xml e o dialeto**

O arquivo persistence.xml define a "persistence unit": qual banco, qual driver, credenciais e o **dialeto**.

**Dialeto** é a "tradução" do SQL genérico da JPA para o SQL específico daquele SGBD (MySQL, PostgreSQL, Oracle geram sintaxes ligeiramente diferentes) — o Hibernate usa isso para gerar comandos compatíveis.

A partir dessa configuração, a JPA pode até gerar as tabelas automaticamente com base nas entidades (útil em estudo/protótipo; arriscado em produção sem controle de migração).

## **4. Entidades: a classe que vira tabela**

> @Entity  
> public class Aluno {  
> @Id  
> @GeneratedValue(strategy = GenerationType.IDENTITY)  
> private Long id;  
>   
> private String nome;  
> }

- @Entity marca a classe como mapeável

- @Id define a chave primária; @GeneratedValue delega a geração do valor ao banco

- Toda entidade precisa de um construtor vazio (exigência da especificação, para o Hibernate conseguir instanciar via reflection)

## **5. EntityManager — o coração das operações**

É o objeto que substitui o Connection/Statement do JDBC puro. Vem de uma EntityManagerFactory, criada uma única vez a partir da persistence unit.

| Operação      | Método                              |
|---------------|-------------------------------------|
| Salvar (novo) | entityManager.persist(objeto)       |
| Buscar por ID | entityManager.find(Aluno.class, id) |
| Atualizar     | entityManager.merge(objeto)         |
| Remover       | entityManager.remove(objeto)        |

## **6. Os estados de uma entidade**

- **Transient (transiente):** objeto recém-criado com new, ainda não tem relação nenhuma com o banco

- **Managed (gerenciado/persistente):** depois de persist() ou find() — o EntityManager está "vigiando" esse objeto; qualquer alteração nos atributos é detectada e sincronizada com o banco automaticamente ao final da transação (*dirty checking*)

- **Detached (destacado):** o EntityManager foi fechado ou o objeto foi explicitamente desanexado — as mudanças não são mais rastreadas

- **Removed (removido):** marcado para exclusão via remove(), some do banco ao final da transação

## **7. Transações**

Toda escrita (persist/merge/remove) precisa acontecer dentro de uma transação: entityManager.getTransaction().begin() ... commit(). Em caso de erro, rollback() desfaz tudo — mesmo princípio de atomicidade que já vimos no JDBC.

## **8. Mapeando relacionamentos**

@OneToOne, @OneToMany, @ManyToOne, @ManyToMany — anotações que descrevem como as entidades se relacionam, refletindo as FKs do modelo relacional.

**Relacionamento bidirecional:** as duas entidades "se conhecem" (ex: Aluno tem lista de Matricula, e Matricula tem referência ao Aluno) — usa-se mappedBy do lado que não é "dono" da relação, pra evitar que a JPA tente criar duas FKs.

> // Lado "muitos" (dono da relação, tem a FK)  
> @ManyToOne  
> private Aluno aluno;  
>   
> // Lado "um" (inverso, não é dono)  
> @OneToMany(mappedBy = "aluno")  
> private List\<Matricula\> matriculas;

## **9. Lazy Loading vs Eager Loading**

**Lazy (preguiçoso):** os dados relacionados só são buscados no banco quando você realmente acessa aquele atributo — padrão para coleções (@OneToMany).

**Eager (ansioso):** os dados relacionados vêm junto na mesma query — padrão para @ManyToOne.

> **Cuidado clássico:** acessar uma coleção lazy depois que o EntityManager já fechou gera LazyInitializationException — um dos erros mais comuns pra quem está aprendendo JPA.

## **10. JPQL — consultando com orientação a objetos**

JPQL (Java Persistence Query Language) parece SQL, mas opera sobre **entidades e atributos**, não tabelas e colunas.

> TypedQuery\<Aluno\> query = entityManager.createQuery(  
> "SELECT a FROM Aluno a WHERE a.nota \>= :notaMinima", Aluno.class);  
> query.setParameter("notaMinima", 7.0);  
> List\<Aluno\> aprovados = query.getResultList();

Repare: FROM Aluno a (nome da classe, maiúsculo) e a.nota (atributo Java), não FROM aluno nem coluna SQL. Parâmetros nomeados (:notaMinima) cumprem o mesmo papel de segurança do PreparedStatement no JDBC.

## **11. Onde isso te leva: Spring Data JPA**

Tudo isso é a base do que já vimos no plano de Spring Boot (JpaRepository) — o Spring gera a implementação do EntityManager por trás dos panos; entender JPA "crua" é o que permite depurar quando o Spring Data não se comporta como esperado.
