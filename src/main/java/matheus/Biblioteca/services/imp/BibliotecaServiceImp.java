package matheus.Biblioteca.services.imp;


import java.util.List;

import org.springframework.stereotype.Service;

import matheus.Biblioteca.domain.models.Livro;
import matheus.Biblioteca.domain.models.Usuario;
import matheus.Biblioteca.domain.repository.BibliotecaRepository;
import matheus.Biblioteca.domain.repository.LivroRepository;
import matheus.Biblioteca.services.BibliotecaService;

@Service
public class BibliotecaServiceImp implements  BibliotecaService{

    private final BibliotecaRepository bibliotecaRepository;
    private final LivroRepository livroRepository;

    public BibliotecaServiceImp(BibliotecaRepository bibliotecaRepository, LivroRepository livroRepository){
        this.bibliotecaRepository = bibliotecaRepository;
        this.livroRepository = livroRepository;
    }


    public Livro addLivro(Livro novoLivro){
        return this.livroRepository.save(novoLivro);
    }

    public List<Livro> todoOsLivros(Long bibliotecaId){
        return this.bibliotecaRepository.findById(bibliotecaId).map(biblioteca -> biblioteca.getLivros()).orElseThrow(()-> new RuntimeException("Erro"));
    }

    public List<Livro> procurarPorTitulo(String titulo) {
        return this.livroRepository.findByTitulo(titulo);
        
    }


    public List<Usuario> todosOsUsuarios(Long bibliotecaId) {
        return this.bibliotecaRepository.findById(bibliotecaId).map(biblioteca -> biblioteca.getUsuarios()).orElseThrow(()-> new RuntimeException("Nenhum usuario achado"));
    }

    public List<Usuario> usuarioPorId(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'usuarioPorId'");
    }

    
}
