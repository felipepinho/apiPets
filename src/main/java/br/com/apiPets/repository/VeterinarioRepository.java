package br.com.apiPets.repository;

import br.com.apiPets.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    boolean existsByCrmv(String crmv);

    boolean existsByCrmvAndIdNot(String crmv, Long id);
}
