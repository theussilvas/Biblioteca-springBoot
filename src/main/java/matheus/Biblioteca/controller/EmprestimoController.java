package matheus.Biblioteca.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import matheus.Biblioteca.domain.models.Emprestimo;
import matheus.Biblioteca.services.exceptions.ResourceNotFoundException;
import matheus.Biblioteca.services.imp.EmprestimoServiceImp;

@RestController
@RequestMapping("emprestimo/")
public class EmprestimoController {

    @Autowired
    private EmprestimoServiceImp emprestimoServiceImp;

    @GetMapping("/emprestimos")
    public ResponseEntity<List<Emprestimo>> todosEmprestimos(){
        var emprestimos = emprestimoServiceImp.findAll();

        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping("/create")
    public ResponseEntity<Emprestimo> novoEmprestimo(@RequestBody Emprestimo emprestimo){
        var novoEmprestimo = emprestimoServiceImp.create(emprestimo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoEmprestimo.getId())
                .toUri();

        return ResponseEntity.created(location).body(novoEmprestimo);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolverEmprestimo(@PathVariable Long id, @RequestBody Emprestimo devolucao){

        try {
            if (devolucao.getDataDevolucao() != null && devolucao.getDataDevolucao().isAfter(LocalDate.now())){
                emprestimoServiceImp.update(id, devolucao);
            }

            return ResponseEntity.ok(devolucao);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
   
}
