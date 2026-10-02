package br.com.alura.mitraSeeks.repository;

import br.com.alura.mitraSeeks.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailIgnoreCase(String email);

    Optional<Usuario> findByUserNameIgnoreCase(String userName);

    Optional<Usuario> findFirstByAdminTrue();
}
