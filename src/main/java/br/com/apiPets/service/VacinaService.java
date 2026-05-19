package br.com.apiPets.service;

import br.com.apiPets.dto.VacinaDto;
import br.com.apiPets.entity.Pet;
import br.com.apiPets.entity.Vacina;
import br.com.apiPets.entity.Veterinario;
import br.com.apiPets.exception.BusinessException;
import br.com.apiPets.exception.ResourceNotFoundException;
import br.com.apiPets.mapper.VacinaMapper;
import br.com.apiPets.repository.VacinaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VacinaService {

    private final VacinaRepository vacinaRepository;
    private final PetService petService;
    private final VeterinarioService veterinarioService;

    @Transactional
    public VacinaDto.Response create(Long petId, VacinaDto.Request request) {
        validateDates(request);
        Pet pet = petService.getEntity(petId);
        Veterinario veterinario = findVeterinarioOrNull(request.veterinarioId());
        return VacinaMapper.toResponse(vacinaRepository.save(VacinaMapper.toEntity(request, pet, veterinario)));
    }

    @Transactional(readOnly = true)
    public List<VacinaDto.Response> findByPet(Long petId) {
        petService.getEntity(petId);
        return vacinaRepository.findByPetId(petId).stream().map(VacinaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public VacinaDto.Response findById(Long id) {
        return VacinaMapper.toResponse(getEntity(id));
    }

    @Transactional
    public VacinaDto.Response update(Long id, VacinaDto.Request request) {
        validateDates(request);
        Vacina vacina = getEntity(id);
        Veterinario veterinario = findVeterinarioOrNull(request.veterinarioId());
        VacinaMapper.updateEntity(vacina, request, vacina.getPet(), veterinario);
        return VacinaMapper.toResponse(vacina);
    }

    @Transactional
    public void delete(Long id) {
        Vacina vacina = getEntity(id);
        vacinaRepository.delete(vacina);
    }

    private Vacina getEntity(Long id) {
        return vacinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacina não encontrada."));
    }

    private Veterinario findVeterinarioOrNull(Long veterinarioId) {
        return veterinarioId == null ? null : veterinarioService.getEntity(veterinarioId);
    }

    private void validateDates(VacinaDto.Request request) {
        if (request.dataProximaDose() != null && request.dataProximaDose().isBefore(request.dataAplicacao())) {
            throw new BusinessException("A próxima dose não pode ser anterior à data de aplicação.");
        }
    }
}
