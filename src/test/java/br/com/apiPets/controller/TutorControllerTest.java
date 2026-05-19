package br.com.apiPets.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.apiPets.dto.TutorDto;
import br.com.apiPets.service.TutorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TutorController.class)
@ActiveProfiles("test")
class TutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TutorService tutorService;

    @Test
    void deveRetornarCriadoAoCadastrarTutor() throws Exception {
        TutorDto.Request requisicao = new TutorDto.Request("Ana Souza", "ana@email.com", "11999999999", "12345678900");
        TutorDto.Response resposta = new TutorDto.Response(1L, requisicao.nome(), requisicao.email(), requisicao.telefone(), requisicao.cpf(), LocalDateTime.now());
        when(tutorService.create(any(TutorDto.Request.class))).thenReturn(resposta);

        mockMvc.perform(post("/tutores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requisicao)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/tutores/1"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    void deveRetornarRequisicaoInvalidaQuandoEmailForInvalido() throws Exception {
        String conteudo = """
                {
                  "nome": "Ana Souza",
                  "email": "email-invalido",
                  "telefone": "11999999999",
                  "cpf": "12345678900"
                }
                """;

        mockMvc.perform(post("/tutores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(conteudo))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }
}
