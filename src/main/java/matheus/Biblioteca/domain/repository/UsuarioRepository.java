package matheus.Biblioteca.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import matheus.Biblioteca.domain.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);
}
