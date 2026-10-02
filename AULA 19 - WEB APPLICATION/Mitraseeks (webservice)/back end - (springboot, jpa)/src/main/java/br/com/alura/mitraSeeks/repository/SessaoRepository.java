package br.com.alura.mitraSeeks.repository;

import br.com.alura.mitraSeeks.model.Sessao;
import br.com.alura.mitraSeeks.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SessaoRepository extends JpaRepository<Sessao, String> {
    @Transactional
    void deleteByUsuario(Usuario usuario);
}
