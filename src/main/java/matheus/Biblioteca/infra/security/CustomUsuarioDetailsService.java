package matheus.Biblioteca.infra.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import matheus.Biblioteca.domain.models.Usuario;
import matheus.Biblioteca.domain.repository.UsuarioRepository;

@Component
public class CustomUsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = this.usuarioRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario nao encontrado"));

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>());
    }
   
    
}
