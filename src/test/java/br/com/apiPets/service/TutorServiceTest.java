package br.com.apiPets.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.apiPets.dto.TutorDto;
import br.com.apiPets.entity.Tutor;
import br.com.apiPets.exception.ConflictException;
import br.com.apiPets.exception.ResourceNotFoundException;
import br.com.apiPets.repository.PetRepository;
import br.com.apiPets.repository.TutorRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private TutorService tutorService;

    @Test
    void deveCadastrarTutorQuandoCpfForUnico() {
        TutorDto.Request requisicao = new TutorDto.Request("Ana Souza", "ana@email.com", "11999999999", "12345678900");
        when(tutorRepository.existsByCpf(requisicao.cpf())).thenReturn(false);
        when(tutorRepository.save(any(Tutor.class))).thenAnswer(invocacao -> {
            Tutor tutor = invocacao.getArgument(0);
            tutor.setId(1L);
            return tutor;
        });

        TutorDto.Response resposta = tutorService.create(requisicao);

        assertThat(resposta.id()).isEqualTo(1L);
        assertThat(resposta.nome()).isEqualTo("Ana Souza");
        verify(tutorRepository).save(any(Tutor.class));
    }

    @Test
    void deveLancarConflitoQuandoCpfJaExistir() {
        TutorDto.Request requisicao = new TutorDto.Request("Ana Souza", "ana@email.com", "11999999999", "12345678900");
        when(tutorRepository.existsByCpf(requisicao.cpf())).thenReturn(true);

        assertThatThrownBy(() -> tutorService.create(requisicao))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("CPF");
    }

    @Test
    void deveLancarNaoEncontradoQuandoTutorNaoExistir() {
        when(tutorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tutorService.findById(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
