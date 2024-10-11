package br.com.fiap.coletaSeletiva.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "E-mail obrigatório!")
        @Email(message = "E-mail inválido!")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(min = 6, max = 20, message = "Senha deve conter entre 6 e 20 caracteres")
        String senha
) {

}
