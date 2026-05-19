package br.com.apiPets.service;

import br.com.apiPets.dto.VeterinarioDto;
import br.com.apiPets.entity.Veterinario;
import br.com.apiPets.exception.ConflictException;
import br.com.apiPets.exception.ResourceNotFoundException;
import br.com.apiPets.mapper.VeterinarioMapper;
import br.com.apiPets.repository.VeterinarioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    @Transactional
    public VeterinarioDto.Response create(VeterinarioDto.Request request) {
        validateCrmv(request.crmv(), null);
        return VeterinarioMapper.toResponse(veterinarioRepository.save(VeterinarioMapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    public List<VeterinarioDto.Response> findAll() {
        return veterinarioRepository.findAll().stream().map(VeterinarioMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public VeterinarioDto.Response findById(Long id) {
        return VeterinarioMapper.toResponse(getEntity(id));
    }

    @Transactional
    public VeterinarioDto.Response update(Long id, VeterinarioDto.Request request) {
        Veterinario veterinario = getEntity(id);
        validateCrmv(request.crmv(), id);
        VeterinarioMapper.updateEntity(veterinario, request);
        return VeterinarioMapper.toResponse(veterinario);
    }

    @Transactional
    public void delete(Long id) {
        Veterinario veterinario = getEntity(id);
        veterinarioRepository.delete(veterinario);
    }

    @Transactional(readOnly = true)
    public Veterinario getEntity(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado."));
    }

    private void validateCrmv(String crmv, Long id) {
        boolean exists = id == null ? veterinarioRepository.existsByCrmv(crmv) : veterinarioRepository.existsByCrmvAndIdNot(crmv, id);
        if (exists) {
            throw new ConflictException("Já existe um veterinário cadastrado com este CRMV.");
        }
    }
}
