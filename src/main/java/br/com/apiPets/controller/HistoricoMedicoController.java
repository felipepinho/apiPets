package br.com.apiPets.controller;

import br.com.apiPets.dto.HistoricoMedicoDto;
import br.com.apiPets.service.HistoricoMedicoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HistoricoMedicoController {

    private final HistoricoMedicoService historicoMedicoService;

    @PostMapping("/pets/{petId}/historico")
    public ResponseEntity<HistoricoMedicoDto.Response> create(@PathVariable Long petId, @Valid @RequestBody HistoricoMedicoDto.Request request) {
        HistoricoMedicoDto.Response response = historicoMedicoService.create(petId, request);
        return ResponseEntity.created(URI.create("/historico/" + response.id())).body(response);
    }

    @GetMapping("/pets/{petId}/historico")
    public ResponseEntity<List<HistoricoMedicoDto.Response>> findByPet(@PathVariable Long petId) {
        return ResponseEntity.ok(historicoMedicoService.findByPet(petId));
    }

    @GetMapping("/historico/{id}")
    public ResponseEntity<HistoricoMedicoDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(historicoMedicoService.findById(id));
    }

    @PutMapping("/historico/{id}")
    public ResponseEntity<HistoricoMedicoDto.Response> update(@PathVariable Long id, @Valid @RequestBody HistoricoMedicoDto.Request request) {
        return ResponseEntity.ok(historicoMedicoService.update(id, request));
    }

    @DeleteMapping("/historico/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        historicoMedicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
