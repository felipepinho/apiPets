package br.com.apiPets.controller;

import br.com.apiPets.dto.VacinaDto;
import br.com.apiPets.service.VacinaService;
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
public class VacinaController {

    private final VacinaService vacinaService;

    @PostMapping("/pets/{petId}/vacinas")
    public ResponseEntity<VacinaDto.Response> create(@PathVariable Long petId, @Valid @RequestBody VacinaDto.Request request) {
        VacinaDto.Response response = vacinaService.create(petId, request);
        return ResponseEntity.created(URI.create("/vacinas/" + response.id())).body(response);
    }

    @GetMapping("/pets/{petId}/vacinas")
    public ResponseEntity<List<VacinaDto.Response>> findByPet(@PathVariable Long petId) {
        return ResponseEntity.ok(vacinaService.findByPet(petId));
    }

    @GetMapping("/vacinas/{id}")
    public ResponseEntity<VacinaDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vacinaService.findById(id));
    }

    @PutMapping("/vacinas/{id}")
    public ResponseEntity<VacinaDto.Response> update(@PathVariable Long id, @Valid @RequestBody VacinaDto.Request request) {
        return ResponseEntity.ok(vacinaService.update(id, request));
    }

    @DeleteMapping("/vacinas/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vacinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
