package br.com.apiPets.mapper;

import br.com.apiPets.dto.VeterinarioDto;
import br.com.apiPets.entity.Veterinario;

public final class VeterinarioMapper {
    private VeterinarioMapper() {
    }

    public static Veterinario toEntity(VeterinarioDto.Request request) {
        Veterinario veterinario = new Veterinario();
        updateEntity(veterinario, request);
        return veterinario;
    }

    public static void updateEntity(Veterinario veterinario, VeterinarioDto.Request request) {
        veterinario.setNome(request.nome());
        veterinario.setCrmv(request.crmv());
        veterinario.setEspecialidade(request.especialidade());
        veterinario.setTelefone(request.telefone());
        veterinario.setEmail(request.email());
    }

    public static VeterinarioDto.Response toResponse(Veterinario veterinario) {
        return new VeterinarioDto.Response(
                veterinario.getId(),
                veterinario.getNome(),
                veterinario.getCrmv(),
                veterinario.getEspecialidade(),
                veterinario.getTelefone(),
                veterinario.getEmail()
        );
    }
}
