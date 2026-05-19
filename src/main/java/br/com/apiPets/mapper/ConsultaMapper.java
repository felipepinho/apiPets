package br.com.apiPets.mapper;

import br.com.apiPets.dto.ConsultaDto;
import br.com.apiPets.entity.Consulta;
import br.com.apiPets.entity.Pet;
import br.com.apiPets.entity.StatusConsulta;
import br.com.apiPets.entity.Veterinario;

public final class ConsultaMapper {
    private ConsultaMapper() {
    }

    public static Consulta toEntity(ConsultaDto.Request request, Pet pet, Veterinario veterinario) {
        Consulta consulta = new Consulta();
        updateEntity(consulta, request, pet, veterinario);
        consulta.setStatus(StatusConsulta.AGENDADA);
        return consulta;
    }

    public static void updateEntity(Consulta consulta, ConsultaDto.Request request, Pet pet, Veterinario veterinario) {
        consulta.setPet(pet);
        consulta.setVeterinario(veterinario);
        consulta.setDataHora(request.dataHora());
        consulta.setMotivo(request.motivo());
        consulta.setDiagnostico(request.diagnostico());
        consulta.setObservacoes(request.observacoes());
    }

    public static ConsultaDto.Response toResponse(Consulta consulta) {
        return new ConsultaDto.Response(
                consulta.getId(),
                consulta.getPet().getId(),
                consulta.getVeterinario().getId(),
                consulta.getDataHora(),
                consulta.getMotivo(),
                consulta.getDiagnostico(),
                consulta.getObservacoes(),
                consulta.getStatus()
        );
    }
}
