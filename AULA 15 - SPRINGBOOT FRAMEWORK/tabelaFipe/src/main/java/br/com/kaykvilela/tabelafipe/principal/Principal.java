package br.com.kaykvilela.tabelafipe.principal;

import br.com.kaykvilela.tabelafipe.model.dadosVeiculo;
import br.com.kaykvilela.tabelafipe.model.Modelos;
import br.com.kaykvilela.tabelafipe.model.Veiculo;
import br.com.kaykvilela.tabelafipe.service.ConsumoApi;
import br.com.kaykvilela.tabelafipe.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    String endereco;
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        var menu = """
               -------------------
               ** |TABELA FIPE| **
               -------------------
                   * Carro *
                  * Caminhão *
                    * Moto *
               
               Digite o tipo do veículo para consulta: 
               """;

        System.out.println(menu);
        var opcao = leitura.nextLine();

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else if (opcao.toLowerCase().contains("camin")) {
            endereco = URL_BASE + "caminhoes/marcas";
        } else {
            System.out.println("Opção inválida!");
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);
        var marcas = conversor.obterLista(json, dadosVeiculo.class);
        marcas.stream()
                .sorted(Comparator.comparing(dadosVeiculo::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codigoMarca = leitura.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(dadosVeiculo::nome))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var nomeVeiculo = leitura.nextLine();

        List<dadosVeiculo> modelosFiltrados = modeloLista.modelos().stream()
                .filter(modelos -> modelos.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação: ");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<dadosVeiculo> anos = conversor.obterLista(json, dadosVeiculo.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);

    }
}