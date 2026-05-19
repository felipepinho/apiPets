package br.com.apiPets.dto;

import br.com.apiPets.entity.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class PetDto {
    private PetDto() {
    }

    public record Request(
            @NotBlank String nome,
            @NotBlank String especie,
            String raca,
            @Positive Integer idade,
            @Positive BigDecimal peso,
            @NotNull Sexo sexo,
            boolean castrado,
            @NotNull Long tutorId
    ) {
    }

    public record PatchRequest(
            String nome,
            String especie,
            String raca,
            @Positive Integer idade,
            @Positive BigDecimal peso,
            Sexo sexo,
            Boolean castrado,
            Long tutorId
    ) {
    }

    public record Response(
            Long id,
            String nome,
            String especie,
            String raca,
            Integer idade,
            BigDecimal peso,
            Sexo sexo,
            boolean castrado,
            TutorDto.Response tutor,
            LocalDateTime dataCadastro
    ) {
    }
}
