package matheus.Biblioteca.infra.security;


import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matheus.Biblioteca.domain.models.Usuario;
import matheus.Biblioteca.domain.repository.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if(login != null){
            Usuario usuario = usuarioRepository.findByEmail(login).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));

            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(usuario,null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    filterChain.doFilter(request, response);    
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null) return null;
        return authHeader.replace("Bearer","");
    }
          
    

    
}
