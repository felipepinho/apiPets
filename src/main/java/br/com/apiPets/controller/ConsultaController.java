package br.com.apiPets.controller;

import br.com.apiPets.dto.ConsultaDto;
import br.com.apiPets.service.ConsultaService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDto.Response> create(@Valid @RequestBody ConsultaDto.Request request) {
        ConsultaDto.Response response = consultaService.create(request);
        return ResponseEntity.created(URI.create("/consultas/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDto.Response>> findAll() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto.Response> update(@PathVariable Long id, @Valid @RequestBody ConsultaDto.Request request) {
        return ResponseEntity.ok(consultaService.update(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ConsultaDto.Response> updateStatus(@PathVariable Long id, @Valid @RequestBody ConsultaDto.StatusRequest request) {
        return ResponseEntity.ok(consultaService.updateStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ConsultaDto.Response>> findByPet(@PathVariable Long petId) {
        return ResponseEntity.ok(consultaService.findByPet(petId));
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<ConsultaDto.Response>> findByVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(consultaService.findByVeterinario(veterinarioId));
    }
}
