package br.com.alura.mitraSeeks.repository;

import br.com.alura.mitraSeeks.model.Categoria;
import br.com.alura.mitraSeeks.model.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filmes, Long> {
    Optional<Filmes> findByTituloIgnoreCase(String titulo);

    List<Filmes> findAllByOrderByTituloAsc();

    List<Filmes> findTop10ByAvaliacaoNotNullOrderByAvaliacaoDesc();

    List<Filmes> findTop10ByAnoLancamentoNotNullOrderByAnoLancamentoDescAvaliacaoDesc();

    List<Filmes> findByGeneroOrderByAvaliacaoDesc(Categoria genero);

    List<Filmes> findTop20ByTituloContainingIgnoreCaseOrderByAvaliacaoDesc(String trecho);

    List<Filmes> findByAvaliacaoIsNull();
}
