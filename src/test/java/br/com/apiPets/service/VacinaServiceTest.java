package br.com.apiPets.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import br.com.apiPets.dto.VacinaDto;
import br.com.apiPets.exception.BusinessException;
import br.com.apiPets.repository.VacinaRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VacinaServiceTest {

    @Mock
    private VacinaRepository vacinaRepository;

    @Mock
    private PetService petService;

    @Mock
    private VeterinarioService veterinarioService;

    @InjectMocks
    private VacinaService vacinaService;

    @Test
    void deveRejeitarProximaDoseAnteriorADataDeAplicacao() {
        VacinaDto.Request requisicao = new VacinaDto.Request(
                "Raiva",
                LocalDate.of(2026, 5, 10),
                LocalDate.of(2026, 5, 1),
                null,
                null
        );

        assertThatThrownBy(() -> vacinaService.create(1L, requisicao))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("dose");
    }
}
