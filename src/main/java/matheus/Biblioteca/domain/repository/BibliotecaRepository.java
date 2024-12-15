package matheus.Biblioteca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import matheus.Biblioteca.domain.models.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

    // boolean existeEmailRegistrado(String email);
    

    
}   
