package br.com.apiPets.mapper;

import br.com.apiPets.dto.TutorDto;
import br.com.apiPets.entity.Tutor;

public final class TutorMapper {
    private TutorMapper() {
    }

    public static Tutor toEntity(TutorDto.Request request) {
        Tutor tutor = new Tutor();
        updateEntity(tutor, request);
        return tutor;
    }

    public static void updateEntity(Tutor tutor, TutorDto.Request request) {
        tutor.setNome(request.nome());
        tutor.setEmail(request.email());
        tutor.setTelefone(request.telefone());
        tutor.setCpf(request.cpf());
    }

    public static TutorDto.Response toResponse(Tutor tutor) {
        return new TutorDto.Response(
                tutor.getId(),
                tutor.getNome(),
                tutor.getEmail(),
                tutor.getTelefone(),
                tutor.getCpf(),
                tutor.getDataCadastro()
        );
    }
}
