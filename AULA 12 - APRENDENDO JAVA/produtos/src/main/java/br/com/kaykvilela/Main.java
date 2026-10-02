package br.com.kaykvilela;      //pacote da classe

//bibliotecas importadas
import br.com.kaykvilela.model.Produto;
import br.com.kaykvilela.service.TraduzProdutoService;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
//
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // estrutura de integração da lista com os dados do nosso csv
        List<Produto> produtos = new CsvToBeanBuilder(new FileReader("src/main/resources/products.csv"))
                .withType(Produto.class).build().parse();

        //for each para imprimir todos os produtos da base de dados
        for(Produto produto: produtos){
            System.out.println(produto);
        }
        //abrindo objeto para tradução dos dados
        TraduzProdutoService traducaoProdutos = new TraduzProdutoService();
        // para cada produto, faz a tradução e retorna traduzido
        for(Produto produto: produtos){
            traducaoProdutos.traduzir(produto);
            System.out.println(produto);
        }
        }
    }
