package br.com.apiPets.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public final class VacinaDto {
    private VacinaDto() {
    }

    public record Request(
            @NotBlank String nome,
            @NotNull @PastOrPresent LocalDate dataAplicacao,
            @FutureOrPresent LocalDate dataProximaDose,
            Long veterinarioId,
            String observacao
    ) {
    }

    public record Response(
            Long id,
            String nome,
            LocalDate dataAplicacao,
            LocalDate dataProximaDose,
            Long petId,
            Long veterinarioId,
            String observacao
    ) {
    }
}
