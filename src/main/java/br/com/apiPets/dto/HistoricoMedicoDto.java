package br.com.apiPets.dto;

import br.com.apiPets.entity.TipoHistorico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public final class HistoricoMedicoDto {
    private HistoricoMedicoDto() {
    }

    public record Request(
            @NotBlank String descricao,
            @NotNull TipoHistorico tipo
    ) {
    }

    public record Response(
            Long id,
            Long petId,
            String descricao,
            TipoHistorico tipo,
            LocalDateTime dataRegistro
    ) {
    }
}
