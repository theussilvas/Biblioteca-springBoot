package matheus.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import matheus.Biblioteca.domain.models.Livro;
import matheus.Biblioteca.services.imp.BibliotecaServiceImp;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    @Autowired
    private BibliotecaServiceImp bibliotecaServiceImp;

    @PostMapping("/add")
    public ResponseEntity<Livro> novoLivro(@RequestBody Livro livro){
        var novoLivro = bibliotecaServiceImp.addLivro(livro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoLivro.getId()).toUri();

        return ResponseEntity.created(location).body(novoLivro);
    }

    @GetMapping("/{bibliotecaId}/livros")
    public List<Livro> getAll(@PathVariable Long bibliotecaId){
        return bibliotecaServiceImp.todoOsLivros(bibliotecaId);
    }

    @GetMapping("/{bibliotecaId}/livros/titulo")
    public List<Livro> livroPorTitulo(@PathVariable Long bibliotecaId, @RequestParam String titulo){
        return bibliotecaServiceImp.procurarPorTitulo(titulo);
    }
}
