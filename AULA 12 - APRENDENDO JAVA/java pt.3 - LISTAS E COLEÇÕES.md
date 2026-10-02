***///\~~/// LISTAS E COLEÇÕES JAVA ///\~~///***

***List (Lista)***

- ***O que é:** Uma coleção ordenada de elementos onde a* *[<u>posição (índice)</u>](https://josafa.com.br/blog/java-collections-saiba-quando-usar-set-map-list-ou-queue) importa.*

- ***Características:** Permite valores duplicados e mantém a ordem exata em que os itens foram inseridos.*

- ***Principais Implementações:** ArrayList (ótima para buscas rápidas) e LinkedList (ótima para inserções e remoções frequentes).*

- ***Quando usar:** Quando você precisa guardar itens em uma sequência específica, como uma lista de tarefas ou um histórico de mensagens. \[[<u>1</u>](https://www.youtube.com/watch?v=YWtSH4Vjtg8&t=231),* *[<u>2</u>](https://medium.com/@cleitonestefenon/principais-diferen%C3%A7as-entre-list-set-e-map-no-java-collections-framework-e1416fa7ea49),* *[<u>3</u>](https://www.youtube.com/watch?v=OH-EdlZ5xPA&t=220),* *[<u>4</u>](https://www.youtube.com/shorts/l-zMVz7TCHw),* *[<u>5</u>](https://josafa.com.br/blog/java-collections-saiba-quando-usar-set-map-list-ou-queue)\]*

***Set (Conjunto)***

- ***O que é:** Um conjunto que armazena **elementos únicos**.*

- ***Características:** **Não permite duplicidades**. Se você tentar adicionar um item igual a um já existente, ele é ignorado.*

- ***Principais Implementações:** HashSet (sem ordem garantida e muito rápido), LinkedHashSet (mantém a ordem de inserção) e TreeSet (mantém os dados ordenados automaticamente).*

- ***Quando usar:** Quando você precisa garantir que não haverá valores repetidos na sua coleção, como CPFs cadastrados ou emails únicos. \[[<u>1</u>](https://blog.vepo.dev/posts/java-101-collections),* *[<u>2</u>](https://medium.com/@cleitonestefenon/principais-diferen%C3%A7as-entre-list-set-e-map-no-java-collections-framework-e1416fa7ea49),* *[<u>3</u>](https://josafa.com.br/blog/java-collections-saiba-quando-usar-set-map-list-ou-queue),* *[<u>4</u>](https://www.youtube.com/watch?v=YWtSH4Vjtg8&t=231)\]*

***Map (Mapa)***

- ***O que é:** Uma estrutura baseada em pares de **chave e valor**. \[[<u>1</u>](https://www.devmedia.com.br/conhecendo-a-interface-map-do-java/37463),* *[<u>2</u>](https://medium.com/@cleitonestefenon/principais-diferen%C3%A7as-entre-list-set-e-map-no-java-collections-framework-e1416fa7ea49)\]*

- ***Características:** Cada **chave deve ser única**, mas os valores podem se repetir. Ao fornecer uma chave, você recupera o valor correspondente. \[[<u>1</u>](https://www.youtube.com/watch?v=_CvCSgBx4pk),* *[<u>2</u>](https://medium.com/@cleitonestefenon/principais-diferen%C3%A7as-entre-list-set-e-map-no-java-collections-framework-e1416fa7ea49),* *[<u>3</u>](https://www.devmedia.com.br/conhecendo-a-interface-map-do-java/37463)\]*

- ***Principais Implementações:** HashMap (rápido e sem ordem), LinkedHashMap (mantém ordem de inserção) e TreeMap (ordena pelas chaves). \[[<u>1</u>](https://www.devmedia.com.br/conhecendo-a-interface-map-do-java/37463),* *[<u>2</u>](https://josafa.com.br/blog/java-collections-saiba-quando-usar-set-map-list-ou-queue)\]*

- ***Quando usar:** Quando você precisa buscar dados rapidamente usando um identificador único, como um dicionário (palavra e significado) ou um cadastro de usuário buscado por ID*

**ARRAY**

\> são <u>estruturas de dados</u> que permitem <u>armazenar uma coleção</u> de elementos do <u>mesmo tipo</u>

\> **Problemas com array**

> \>o <u>tamanho</u> de um array é <u>fixo</u> e não pode ser alterado após a sua criação

**ArrayList**

\> <u>classe</u> do Java que <u>encapsula</u> e <u>abstrai</u> um <u>array</u>

\> **<span class="mark">ArrayList\<Pessoa\> lista De Pessoas = new ArrayList\<\>();</span>**

**Método <u>toString();</u>**

\> serve para retornar uma representação em texto de um objeto

\> mostra por padrão o nome da classe e o endereço de memória

\> Pessoa@15db9742

\> podemos <u>sobrescrever</u> com o **@Override** para retornar os <u>valores</u> reais do <u>objeto</u>

\> **<span class="mark">@Override public String toString() {</span>**

> **<span class="mark">return "Nome: " + nome + ", Idade: " + idade;}</span>**

**CONSTRUTORES**

\> tem sempre o mesmo nome da classe

\> não declara nenhum retorno, nem mesmo void

\> **<span class="mark">public class Titulo {</span>**

**<span class="mark">//atributos da classe</span>**

**<span class="mark">// construtor e seus argumentos</span>**

**<span class="mark">public Titulo(String nome, int anoDeLancamento) {</span>**

**this.nome = nome;**

**this.anoDeLancamento = anoDeLancamento;**

**MÉTODOS DE PERCORRER A LISTA**

\> **for each**

> \> laço de repetição simplificado usado para <u>percorrer</u> <u>arrays</u>

\> de forma limpa e <u>sem</u> o uso de <u>contadores</u> ou <u>índices</u>

> \> maneiras de implementar:
>
> \> **<span class="mark">for (Tipo elemento : array Coleção) {</span>**
>
> <span class="mark">// Código executado para cada item</span>}
>
> \> **<span class="mark">nomes.forEach(nome -\> System.out.println(nome));</span>**
