package br.com.fiap.coletaSeletiva.controller;

import br.com.fiap.coletaSeletiva.dto.UsuarioCadastroDTO;
import br.com.fiap.coletaSeletiva.dto.UsuarioExibicaoDTO;
import br.com.fiap.coletaSeletiva.model.Usuario;
import br.com.fiap.coletaSeletiva.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

//    @PostMapping("/usuarios")
//    @ResponseStatus(HttpStatus.CREATED)
//    public UsuarioExibicaoDTO salvar(@RequestBody UsuarioCadastroDTO usuario){
//        return usuarioService.salvarUsuario(usuario);
//    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> listarTodos(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/usuarios/{usuarioId}")
    public UsuarioExibicaoDTO buscarPorId(@PathVariable Long usuarioId){
        return usuarioService.buscarUsuarioPorId(usuarioId);
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        usuarioService.excluirUsuario(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(usuario);
    }

}