package br.com.apiPets.mapper;

import br.com.apiPets.dto.HistoricoMedicoDto;
import br.com.apiPets.entity.HistoricoMedico;
import br.com.apiPets.entity.Pet;

public final class HistoricoMedicoMapper {
    private HistoricoMedicoMapper() {
    }

    public static HistoricoMedico toEntity(HistoricoMedicoDto.Request request, Pet pet) {
        HistoricoMedico historico = new HistoricoMedico();
        updateEntity(historico, request, pet);
        return historico;
    }

    public static void updateEntity(HistoricoMedico historico, HistoricoMedicoDto.Request request, Pet pet) {
        historico.setPet(pet);
        historico.setDescricao(request.descricao());
        historico.setTipo(request.tipo());
    }

    public static HistoricoMedicoDto.Response toResponse(HistoricoMedico historico) {
        return new HistoricoMedicoDto.Response(
                historico.getId(),
                historico.getPet().getId(),
                historico.getDescricao(),
                historico.getTipo(),
                historico.getDataRegistro()
        );
    }
}
