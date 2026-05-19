package br.com.apiPets.service;

import br.com.apiPets.dto.PetDto;
import br.com.apiPets.entity.Pet;
import br.com.apiPets.entity.Tutor;
import br.com.apiPets.exception.BusinessException;
import br.com.apiPets.exception.ResourceNotFoundException;
import br.com.apiPets.mapper.PetMapper;
import br.com.apiPets.repository.PetRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final TutorService tutorService;

    @Transactional
    public PetDto.Response create(PetDto.Request request) {
        Tutor tutor = tutorService.getEntity(request.tutorId());
        return PetMapper.toResponse(petRepository.save(PetMapper.toEntity(request, tutor)));
    }

    @Transactional(readOnly = true)
    public List<PetDto.Response> findAll() {
        return petRepository.findAll().stream().map(PetMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PetDto.Response findById(Long id) {
        return PetMapper.toResponse(getEntity(id));
    }

    @Transactional
    public PetDto.Response update(Long id, PetDto.Request request) {
        Pet pet = getEntity(id);
        Tutor tutor = tutorService.getEntity(request.tutorId());
        PetMapper.updateEntity(pet, request, tutor);
        return PetMapper.toResponse(pet);
    }

    @Transactional
    public PetDto.Response patch(Long id, PetDto.PatchRequest request) {
        Pet pet = getEntity(id);
        Tutor tutor = request.tutorId() == null ? null : tutorService.getEntity(request.tutorId());
        PetMapper.patchEntity(pet, request, tutor);
        validateRequiredAfterPatch(pet);
        return PetMapper.toResponse(pet);
    }

    @Transactional
    public void delete(Long id) {
        Pet pet = getEntity(id);
        petRepository.delete(pet);
    }

    @Transactional(readOnly = true)
    public List<PetDto.Response> findByTutor(Long tutorId) {
        tutorService.getEntity(tutorId);
        return petRepository.findByTutorId(tutorId).stream().map(PetMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Pet getEntity(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado."));
    }

    private void validateRequiredAfterPatch(Pet pet) {
        if (isBlank(pet.getNome()) || isBlank(pet.getEspecie()) || pet.getSexo() == null || pet.getTutor() == null) {
            throw new BusinessException("Nome, espécie, sexo e tutor são obrigatórios para o pet.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
