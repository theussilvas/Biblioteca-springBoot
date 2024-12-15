package matheus.Biblioteca.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matheus.Biblioteca.domain.models.Usuario;
import matheus.Biblioteca.domain.repository.UsuarioRepository;
import matheus.Biblioteca.dto.LoginRequestDTO;
import matheus.Biblioteca.dto.RegisterRequestDTO;
import matheus.Biblioteca.dto.ResponseDTO;
import matheus.Biblioteca.infra.security.TokenService;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario usuario = this.usuarioRepository.findByEmail(body.email()).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));

        if (passwordEncoder.matches(body.password(), usuario.getPassword())){
            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok( new ResponseDTO(usuario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional <Usuario> usuario = this.usuarioRepository.findByEmail(body.email());

        if (usuario.isEmpty()){
            Usuario novUsuario = new Usuario();
            novUsuario.setPassword(passwordEncoder.encode(body.password()));
            novUsuario.setEmail(body.email());
            novUsuario.setNome(body.nome());
            this.usuarioRepository.save(novUsuario);

            String token = this.tokenService.generateToken(novUsuario);
            return ResponseEntity.ok(new ResponseDTO(novUsuario.getNome(),token));
        }

        return ResponseEntity.badRequest().build();
    }

    
}
