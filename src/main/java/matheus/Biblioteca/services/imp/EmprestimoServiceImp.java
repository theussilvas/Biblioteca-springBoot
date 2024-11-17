package matheus.Biblioteca.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import matheus.Biblioteca.domain.models.Emprestimo;
import matheus.Biblioteca.domain.repository.EmprestimoRepository;
import matheus.Biblioteca.services.EmprestimoService;
import matheus.Biblioteca.services.exceptions.ResourceNotFoundException;

@Service
public class EmprestimoServiceImp implements EmprestimoService{

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoServiceImp(EmprestimoRepository emprestimoRepository){
        this.emprestimoRepository = emprestimoRepository;
    }

    public List<Emprestimo> findAll(){
        return this.emprestimoRepository.findAll();
    }

    public Emprestimo create(Emprestimo novEmprestimo){
        return this.emprestimoRepository.save(novEmprestimo);
    }

    public Emprestimo update(Long id,Emprestimo updateEmprestimo){
        Emprestimo dbEmprestimo = this.findById(id);

        if (dbEmprestimo == null) {
            throw new ResourceNotFoundException("Empréstimo não encontrado com o id: " + id);
        }

        if (updateEmprestimo.getDataDevolucao() != null) {
            dbEmprestimo.setDataDevolucao(updateEmprestimo.getDataDevolucao());
        }

        return this.emprestimoRepository.save(dbEmprestimo);
    }

   public void delete(Long id){
        Emprestimo dbEmprestimo = findById(id);
        
        this.emprestimoRepository.delete(dbEmprestimo);
   }

    public Emprestimo findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
