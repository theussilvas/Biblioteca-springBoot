package matheus.Biblioteca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import matheus.Biblioteca.domain.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
