package br.com.fiap.coletaSeletiva.controller;

import br.com.fiap.coletaSeletiva.config.security.TokenService;
import br.com.fiap.coletaSeletiva.dto.LoginDTO;
import br.com.fiap.coletaSeletiva.dto.TokenDTO;
import br.com.fiap.coletaSeletiva.dto.UsuarioCadastroDTO;
import br.com.fiap.coletaSeletiva.dto.UsuarioExibicaoDTO;
import br.com.fiap.coletaSeletiva.model.Usuario;
import br.com.fiap.coletaSeletiva.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.senha()
                );

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO){
        UsuarioExibicaoDTO usuarioSalvo = null;
        usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDTO);
        return usuarioSalvo;
    }

}
