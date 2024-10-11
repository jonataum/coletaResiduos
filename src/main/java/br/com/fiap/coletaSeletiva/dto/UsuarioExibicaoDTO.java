package br.com.fiap.coletaSeletiva.dto;

import br.com.fiap.coletaSeletiva.model.Usuario;
import br.com.fiap.coletaSeletiva.model.UsuarioRole;

public record UsuarioExibicaoDTO (
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDTO(Usuario usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
