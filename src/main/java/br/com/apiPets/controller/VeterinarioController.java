package br.com.apiPets.controller;

import br.com.apiPets.dto.VeterinarioDto;
import br.com.apiPets.service.VeterinarioService;
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
@RequestMapping("/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    public ResponseEntity<VeterinarioDto.Response> create(@Valid @RequestBody VeterinarioDto.Request request) {
        VeterinarioDto.Response response = veterinarioService.create(request);
        return ResponseEntity.created(URI.create("/veterinarios/" + response.id())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioDto.Response>> findAll() {
        return ResponseEntity.ok(veterinarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioDto.Response> update(@PathVariable Long id, @Valid @RequestBody VeterinarioDto.Request request) {
        return ResponseEntity.ok(veterinarioService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        veterinarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
