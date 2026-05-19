package br.com.apiPets.dto;

import br.com.apiPets.entity.StatusConsulta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public final class ConsultaDto {
    private ConsultaDto() {
    }

    public record Request(
            @NotNull Long petId,
            @NotNull Long veterinarioId,
            @NotNull LocalDateTime dataHora,
            @NotBlank String motivo,
            String diagnostico,
            String observacoes
    ) {
    }

    public record StatusRequest(@NotNull StatusConsulta status) {
    }

    public record Response(
            Long id,
            Long petId,
            Long veterinarioId,
            LocalDateTime dataHora,
            String motivo,
            String diagnostico,
            String observacoes,
            StatusConsulta status
    ) {
    }
}
