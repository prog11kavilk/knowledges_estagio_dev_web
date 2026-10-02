package br.com.alura.mitraSeeks.repository;

import br.com.alura.mitraSeeks.model.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FraseRepository extends JpaRepository<Frase, Long> {
    Optional<Frase> findFirstByTipoAndTituloIgnoreCaseAndPersonagemIgnoreCase(String tipo, String titulo, String personagem);

    List<Frase> findAllByOrderByTituloAsc();

    List<Frase> findByPosterIsNullOrCoresIsNull();

    // Sorteio feito pelo próprio Postgres; "exceto" evita repetir a frase que já está na tela
    @Query(value = "SELECT * FROM frases WHERE id <> :exceto ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Frase> sortearExceto(@Param("exceto") Long exceto);
}
