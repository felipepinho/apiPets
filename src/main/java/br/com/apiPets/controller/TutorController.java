package br.com.apiPets.controller;

import br.com.apiPets.dto.PetDto;
import br.com.apiPets.dto.TutorDto;
import br.com.apiPets.service.TutorService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorDto.Response> create(@Valid @RequestBody TutorDto.Request request) {
        TutorDto.Response response = tutorService.create(request);
        return ResponseEntity.created(URI.create("/tutores/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TutorDto.Response>> findAll() {
        return ResponseEntity.ok(tutorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorDto.Response> update(@PathVariable Long id, @Valid @RequestBody TutorDto.Request request) {
        return ResponseEntity.ok(tutorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tutorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetDto.Response>> findPets(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.findPets(id));
    }
}
