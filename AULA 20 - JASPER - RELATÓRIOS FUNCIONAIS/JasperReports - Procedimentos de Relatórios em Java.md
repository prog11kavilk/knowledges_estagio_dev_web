***JASPERREPORTS: PROCEDIMENTOS DE RELATÓRIOS EM JAVA***

> **\>** fonte: playlist "JasperReports — Dois Exemplos Práticos — Relatórios com JAVA" (YouTube)
>
> **\>** ferramentas: **JasperSoft Studio** (design visual do .jrxml) + biblioteca **JasperReports** (compilação/execução em Java)
>
> **\>** objetivo deste resumo: guia de consulta recorrente — só os procedimentos (conexão, campos, parâmetros, variáveis, formatação, referência de elementos), sem contexto teórico do curso

**1. CONEXÃO COM O BANCO DE DADOS**

> **\>** no JasperSoft Studio: aba **Repository** → botão direito em **Data Adapters** → **Create Data Adapter** → **Database JDBC Connection** — informar driver, URL, usuário e senha
>
> **\>** esse Data Adapter é usado só para **visualizar/testar** o relatório dentro do Studio — em produção, a conexão real vem do código Java
>
> **\>** no código Java, a conexão é um **java.sql.Connection** comum, passado direto para o método que preenche o relatório
>
> **Connection conexao = DriverManager.getConnection(  
> "jdbc:mysql://localhost:3306/meubanco", "root", "senha");  
>   
> Map\<String, Object\> parametros = new HashMap\<\>();  
> JasperPrint print = JasperFillManager.fillReport(  
> relatorioCompilado, parametros, conexao);  
>   
> conexao.close();**
>
> **\>** alternativa sem banco: usar uma **JRDataSource** (ex: **JRBeanCollectionDataSource**) no lugar da Connection, alimentando o relatório com uma lista de objetos Java em vez de SQL

**2. A QUERY DO RELATÓRIO**

> **\>** fica na aba **Dataset and Query** do Studio — um SQL normal, executado contra o Data Adapter ativo
>
> **\>** ao rodar a query no Studio, os campos do SELECT são detectados automaticamente e viram **Fields** disponíveis pro relatório
>
> **SELECT nome, cargo, salario, data_admissao  
> FROM funcionario  
> WHERE departamento = \$P{departamentoParam}  
> ORDER BY \$P!{ordenacao}**
>
> **\> \$P{...}** → vira um *prepared statement* (placeholder ?) — o valor é injetado com segurança pelo driver JDBC (contra SQL injection)
>
> **\> \$P!{...}** → substitui texto direto na query (concatenação) — usado quando o parâmetro é parte da *sintaxe* do SQL (ex: cláusula ORDER BY, nome de tabela) e não um valor

**3. PARÂMETROS (\$P) — INJEÇÃO DE VARIÁVEIS EXTERNAS**

> **\>** parâmetro = valor que vem de **fora** do relatório (do código Java) — filtro, título dinâmico, data de geração, logo, etc.
>
> **\>** criado na aba **Outline → Parameters** do Studio, com nome e classe Java (String, Integer, Date etc.)
>
> **\>** referenciado em qualquer expressão do relatório com **\$P{nomeParametro}**
>
> **\>** no Java, é injetado via **Map\<String, Object\>** passado para o fillReport(...)
>
> **Map\<String, Object\> parametros = new HashMap\<\>();  
> parametros.put("departamentoParam", "TI");  
> parametros.put("dataGeracao", new Date());  
> parametros.put("ordenacao", "salario DESC");  
>   
> JasperPrint print = JasperFillManager.fillReport(  
> relatorioCompilado, parametros, conexao);**
>
> **\>** parâmetro também pode ter **Default Value Expression** no Studio — usado quando o relatório é testado sem passar valor nenhum pelo Java

**4. CAMPOS (\$F) — DADOS DA QUERY**

> **\>** campo = cada coluna retornada pela query — populado automaticamente **linha a linha** enquanto o relatório é preenchido
>
> **\>** referenciado com **\$F{nomeCampo}** — normalmente dentro de um **Text Field** arrastado na banda Detail
>
> **\$F{nome} + " - " + \$F{cargo}**
>
> **\>** diferente de parâmetro: campo **não** é passado pelo Java — ele nasce direto do resultado da query (ou dos beans, se usar JRDataSource)

**5. VARIÁVEIS (\$V) — CÁLCULOS AGREGADOS**

> **\>** variável = valor **calculado** pelo próprio Jasper durante o preenchimento (soma, contagem, média...) — não vem pronta do banco
>
> **\>** criada em **Outline → Variables**, com **Calculation** (Sum, Count, Average, Highest, Lowest...) e **Reset Type** (quando ela zera: Report, Page, Group, Column)
>
> **\<variable name="totalSalarios" class="java.lang.Double" calculation="Sum"\>  
> \<variableExpression\>\<\![CDATA\[\$F{salario}\]\]\>\</variableExpression\>  
> \</variable\>**
>
> **\>** referenciada com **\$V{totalSalarios}** — normalmente num Text Field da banda Summary ou Group Footer
>
> **\> Reset Type = Group** é o mais comum pra subtotal por grupo (ex: total de salário por departamento, resetando a cada novo departamento)

**6. FORMATAÇÃO DE CAMPOS**

> **\>** formatação fica na propriedade **Pattern** do Text Field (painel Properties, com o campo selecionado no Studio)
>
> **\>** moeda/número: **R\$ \#,##0.00** ou \#,##0.00
>
> **\>** data: **dd/MM/yyyy** ou dd/MM/yyyy HH:mm (segue os padrões do java.text.SimpleDateFormat)
>
> **\>** percentual: **\#0.00%**
>
> **\>** campo precisa ter o **tipo de classe** certo (java.math.BigDecimal, java.util.Date...) — Pattern não converte tipo, só formata exibição

**7. REFERENCIANDO ELEMENTOS DO RELATÓRIO (BANDAS)**

> **\> Title** → aparece uma vez, no início do relatório (capa/cabeçalho geral)
>
> **\> Page Header / Page Footer** → repete em toda página (ex: logo, número da página)
>
> **\> Column Header / Column Footer** → título e rodapé das colunas da tabela
>
> **\> Detail** → repete uma vez **por linha** do resultado da query — é onde ficam os \$F{...}
>
> **\> Group Header / Group Footer** → aparece ao entrar/sair de um grupo (ex: agrupado por departamento) — é onde ficam subtotais com \$V{...}
>
> **\> Summary** → aparece uma vez, no final do relatório (total geral)
>
> **\>** página/hora do sistema: campos prontos **\$V{PAGE_NUMBER}**, \$V{PAGE_NUMBER} + " de " + \$V{PAGE_NUMBER} (total via Evaluation Time = Report)

**8. COMPILAR E EXECUTAR NO JAVA**

> **\>** o arquivo **.jrxml** (XML, editável no Studio) precisa virar **.jasper** (compilado) antes de rodar
>
> **JasperReport relatorioCompilado =  
> JasperCompileManager.compileReport("relatorio.jrxml");  
>   
> JasperPrint print = JasperFillManager.fillReport(  
> relatorioCompilado, parametros, conexao);  
>   
> JasperExportManager.exportReportToPdfFile(print, "saida.pdf");**
>
> **\>** fluxo completo: **Compile** (.jrxml → .jasper) → **Fill** (injeta parâmetros + conexão/dados) → **Export** (PDF, XLSX, HTML, CSV...)
>
> **\>** visualizar sem exportar: **JasperViewer.viewReport(print, false)** abre uma janela de preview
>
> **\>** em projeto Maven, a dependência é: **net.sf.jasperreports:jasperreports**

**LINKS ÚTEIS**

> **\>** Playlist de origem (YouTube) — [<u>https://www.youtube.com/watch?v=8YebDzDBvOo&list=PLydhlxPpuc-SX4VGw7FaR7-aEkm6dChVh</u>](https://www.youtube.com/watch?v=8YebDzDBvOo&list=PLydhlxPpuc-SX4VGw7FaR7-aEkm6dChVh)
>
> **\>** Documentação oficial JasperReports — Query/parâmetros — [<u>https://jasperreports.sourceforge.net/sample.reference/query/README.html</u>](https://jasperreports.sourceforge.net/sample.reference/query/README.html)
>
> **\>** Repositório oficial (GitHub) — Jaspersoft/jasperreports — [<u>https://github.com/Jaspersoft/jasperreports</u>](https://github.com/Jaspersoft/jasperreports)
>
> **\>** Referência de sintaxe (Parameters/Variables) — TutorialsPoint — [<u>https://www.tutorialspoint.com/jasper_reports/jasper_quick_guide.htm</u>](https://www.tutorialspoint.com/jasper_reports/jasper_quick_guide.htm)
