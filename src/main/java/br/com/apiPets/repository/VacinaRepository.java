package br.com.apiPets.repository;

import br.com.apiPets.entity.Vacina;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
    List<Vacina> findByPetId(Long petId);
}
