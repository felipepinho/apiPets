package br.com.apiPets.mapper;

import br.com.apiPets.dto.PetDto;
import br.com.apiPets.entity.Pet;
import br.com.apiPets.entity.Tutor;

public final class PetMapper {
    private PetMapper() {
    }

    public static Pet toEntity(PetDto.Request request, Tutor tutor) {
        Pet pet = new Pet();
        updateEntity(pet, request, tutor);
        return pet;
    }

    public static void updateEntity(Pet pet, PetDto.Request request, Tutor tutor) {
        pet.setNome(request.nome());
        pet.setEspecie(request.especie());
        pet.setRaca(request.raca());
        pet.setIdade(request.idade());
        pet.setPeso(request.peso());
        pet.setSexo(request.sexo());
        pet.setCastrado(request.castrado());
        pet.setTutor(tutor);
    }

    public static void patchEntity(Pet pet, PetDto.PatchRequest request, Tutor tutor) {
        if (request.nome() != null) pet.setNome(request.nome());
        if (request.especie() != null) pet.setEspecie(request.especie());
        if (request.raca() != null) pet.setRaca(request.raca());
        if (request.idade() != null) pet.setIdade(request.idade());
        if (request.peso() != null) pet.setPeso(request.peso());
        if (request.sexo() != null) pet.setSexo(request.sexo());
        if (request.castrado() != null) pet.setCastrado(request.castrado());
        if (tutor != null) pet.setTutor(tutor);
    }

    public static PetDto.Response toResponse(Pet pet) {
        return new PetDto.Response(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getIdade(),
                pet.getPeso(),
                pet.getSexo(),
                pet.isCastrado(),
                TutorMapper.toResponse(pet.getTutor()),
                pet.getDataCadastro()
        );
    }
}
