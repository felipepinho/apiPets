package br.com.apiPets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public final class TutorDto {
    private TutorDto() {
    }

    public record Request(
            @NotBlank String nome,
            @NotBlank @Email String email,
            @NotBlank String telefone,
            @NotBlank String cpf
    ) {
    }

    public record Response(
            Long id,
            String nome,
            String email,
            String telefone,
            String cpf,
            LocalDateTime dataCadastro
    ) {
    }
}
