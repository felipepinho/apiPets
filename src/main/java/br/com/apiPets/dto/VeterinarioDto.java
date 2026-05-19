package br.com.apiPets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public final class VeterinarioDto {
    private VeterinarioDto() {
    }

    public record Request(
            @NotBlank String nome,
            @NotBlank String crmv,
            @NotBlank String especialidade,
            String telefone,
            @Email String email
    ) {
    }

    public record Response(
            Long id,
            String nome,
            String crmv,
            String especialidade,
            String telefone,
            String email
    ) {
    }
}
