package br.com.apiPets.service;

import br.com.apiPets.dto.HistoricoMedicoDto;
import br.com.apiPets.entity.HistoricoMedico;
import br.com.apiPets.entity.Pet;
import br.com.apiPets.exception.ResourceNotFoundException;
import br.com.apiPets.mapper.HistoricoMedicoMapper;
import br.com.apiPets.repository.HistoricoMedicoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoricoMedicoService {

    private final HistoricoMedicoRepository historicoMedicoRepository;
    private final PetService petService;

    @Transactional
    public HistoricoMedicoDto.Response create(Long petId, HistoricoMedicoDto.Request request) {
        Pet pet = petService.getEntity(petId);
        return HistoricoMedicoMapper.toResponse(historicoMedicoRepository.save(HistoricoMedicoMapper.toEntity(request, pet)));
    }

    @Transactional(readOnly = true)
    public List<HistoricoMedicoDto.Response> findByPet(Long petId) {
        petService.getEntity(petId);
        return historicoMedicoRepository.findByPetId(petId).stream().map(HistoricoMedicoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public HistoricoMedicoDto.Response findById(Long id) {
        return HistoricoMedicoMapper.toResponse(getEntity(id));
    }

    @Transactional
    public HistoricoMedicoDto.Response update(Long id, HistoricoMedicoDto.Request request) {
        HistoricoMedico historico = getEntity(id);
        HistoricoMedicoMapper.updateEntity(historico, request, historico.getPet());
        return HistoricoMedicoMapper.toResponse(historico);
    }

    @Transactional
    public void delete(Long id) {
        HistoricoMedico historico = getEntity(id);
        historicoMedicoRepository.delete(historico);
    }

    private HistoricoMedico getEntity(Long id) {
        return historicoMedicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico médico não encontrado."));
    }
}
