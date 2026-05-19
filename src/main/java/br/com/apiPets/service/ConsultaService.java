package br.com.apiPets.service;

import br.com.apiPets.dto.ConsultaDto;
import br.com.apiPets.entity.Consulta;
import br.com.apiPets.entity.Pet;
import br.com.apiPets.entity.StatusConsulta;
import br.com.apiPets.entity.Veterinario;
import br.com.apiPets.exception.ResourceNotFoundException;
import br.com.apiPets.mapper.ConsultaMapper;
import br.com.apiPets.repository.ConsultaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PetService petService;
    private final VeterinarioService veterinarioService;

    @Transactional
    public ConsultaDto.Response create(ConsultaDto.Request request) {
        Pet pet = petService.getEntity(request.petId());
        Veterinario veterinario = veterinarioService.getEntity(request.veterinarioId());
        return ConsultaMapper.toResponse(consultaRepository.save(ConsultaMapper.toEntity(request, pet, veterinario)));
    }

    @Transactional(readOnly = true)
    public List<ConsultaDto.Response> findAll() {
        return consultaRepository.findAll().stream().map(ConsultaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ConsultaDto.Response findById(Long id) {
        return ConsultaMapper.toResponse(getEntity(id));
    }

    @Transactional
    public ConsultaDto.Response update(Long id, ConsultaDto.Request request) {
        Consulta consulta = getEntity(id);
        Pet pet = petService.getEntity(request.petId());
        Veterinario veterinario = veterinarioService.getEntity(request.veterinarioId());
        StatusConsulta status = consulta.getStatus();
        ConsultaMapper.updateEntity(consulta, request, pet, veterinario);
        consulta.setStatus(status);
        return ConsultaMapper.toResponse(consulta);
    }

    @Transactional
    public ConsultaDto.Response updateStatus(Long id, ConsultaDto.StatusRequest request) {
        Consulta consulta = getEntity(id);
        consulta.setStatus(request.status());
        return ConsultaMapper.toResponse(consulta);
    }

    @Transactional
    public void delete(Long id) {
        Consulta consulta = getEntity(id);
        consultaRepository.delete(consulta);
    }

    @Transactional(readOnly = true)
    public List<ConsultaDto.Response> findByPet(Long petId) {
        petService.getEntity(petId);
        return consultaRepository.findByPetId(petId).stream().map(ConsultaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ConsultaDto.Response> findByVeterinario(Long veterinarioId) {
        veterinarioService.getEntity(veterinarioId);
        return consultaRepository.findByVeterinarioId(veterinarioId).stream().map(ConsultaMapper::toResponse).toList();
    }

    private Consulta getEntity(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
    }
}
