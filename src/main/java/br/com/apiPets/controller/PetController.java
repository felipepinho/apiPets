package br.com.apiPets.controller;

import br.com.apiPets.dto.PetDto;
import br.com.apiPets.service.PetService;
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
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetDto.Response> create(@Valid @RequestBody PetDto.Request request) {
        PetDto.Response response = petService.create(request);
        return ResponseEntity.created(URI.create("/pets/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PetDto.Response>> findAll() {
        return ResponseEntity.ok(petService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDto.Response> update(@PathVariable Long id, @Valid @RequestBody PetDto.Request request) {
        return ResponseEntity.ok(petService.update(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PetDto.Response> patch(@PathVariable Long id, @Valid @RequestBody PetDto.PatchRequest request) {
        return ResponseEntity.ok(petService.patch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<PetDto.Response>> findByTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(petService.findByTutor(tutorId));
    }
}
