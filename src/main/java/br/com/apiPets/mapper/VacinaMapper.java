package br.com.apiPets.mapper;

import br.com.apiPets.dto.VacinaDto;
import br.com.apiPets.entity.Pet;
import br.com.apiPets.entity.Vacina;
import br.com.apiPets.entity.Veterinario;

public final class VacinaMapper {
    private VacinaMapper() {
    }

    public static Vacina toEntity(VacinaDto.Request request, Pet pet, Veterinario veterinario) {
        Vacina vacina = new Vacina();
        updateEntity(vacina, request, pet, veterinario);
        return vacina;
    }

    public static void updateEntity(Vacina vacina, VacinaDto.Request request, Pet pet, Veterinario veterinario) {
        vacina.setNome(request.nome());
        vacina.setDataAplicacao(request.dataAplicacao());
        vacina.setDataProximaDose(request.dataProximaDose());
        vacina.setPet(pet);
        vacina.setVeterinario(veterinario);
        vacina.setObservacao(request.observacao());
    }

    public static VacinaDto.Response toResponse(Vacina vacina) {
        Long veterinarioId = vacina.getVeterinario() == null ? null : vacina.getVeterinario().getId();
        return new VacinaDto.Response(
                vacina.getId(),
                vacina.getNome(),
                vacina.getDataAplicacao(),
                vacina.getDataProximaDose(),
                vacina.getPet().getId(),
                veterinarioId,
                vacina.getObservacao()
        );
    }
}
