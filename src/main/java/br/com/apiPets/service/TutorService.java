package br.com.apiPets.service;

import br.com.apiPets.dto.PetDto;
import br.com.apiPets.dto.TutorDto;
import br.com.apiPets.entity.Tutor;
import br.com.apiPets.exception.ConflictException;
import br.com.apiPets.exception.ResourceNotFoundException;
import br.com.apiPets.mapper.PetMapper;
import br.com.apiPets.mapper.TutorMapper;
import br.com.apiPets.repository.PetRepository;
import br.com.apiPets.repository.TutorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final PetRepository petRepository;

    @Transactional
    public TutorDto.Response create(TutorDto.Request request) {
        validateCpf(request.cpf(), null);
        return TutorMapper.toResponse(tutorRepository.save(TutorMapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    public List<TutorDto.Response> findAll() {
        return tutorRepository.findAll().stream().map(TutorMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TutorDto.Response findById(Long id) {
        return TutorMapper.toResponse(getEntity(id));
    }

    @Transactional
    public TutorDto.Response update(Long id, TutorDto.Request request) {
        Tutor tutor = getEntity(id);
        validateCpf(request.cpf(), id);
        TutorMapper.updateEntity(tutor, request);
        return TutorMapper.toResponse(tutor);
    }

    @Transactional
    public void delete(Long id) {
        Tutor tutor = getEntity(id);
        tutorRepository.delete(tutor);
    }

    @Transactional(readOnly = true)
    public List<PetDto.Response> findPets(Long id) {
        if (!tutorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tutor não encontrado.");
        }
        return petRepository.findByTutorId(id).stream().map(PetMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Tutor getEntity(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado."));
    }

    private void validateCpf(String cpf, Long id) {
        boolean exists = id == null ? tutorRepository.existsByCpf(cpf) : tutorRepository.existsByCpfAndIdNot(cpf, id);
        if (exists) {
            throw new ConflictException("Já existe um tutor cadastrado com este CPF.");
        }
    }
}
