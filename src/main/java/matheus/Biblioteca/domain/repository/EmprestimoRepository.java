package matheus.Biblioteca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import matheus.Biblioteca.domain.models.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {

}
