package br.com.fiap.coletaSeletiva.dto;

import br.com.fiap.coletaSeletiva.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO (
        Long usuarioId,

        @NotBlank(message = "Nome obrigatório")
        String nome,

        @NotBlank(message = "E-mail obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(min = 6, max = 20, message = "Senha deve conter entre 6 e 20 caracteres")
        String senha,
        UsuarioRole role
){


}
