package matheus.Biblioteca.services;

import java.util.List;

import matheus.Biblioteca.domain.models.Livro;
import matheus.Biblioteca.domain.models.Usuario;

public interface BibliotecaService {

     //Livros
    List<Livro> todoOsLivros(Long bibliotecaId);
    List<Livro> procurarPorTitulo(String titulo);

    //Usuarios
    List<Usuario> todosOsUsuarios(Long bibliotecaId);
    List<Usuario> usuarioPorId(Long id);
}
