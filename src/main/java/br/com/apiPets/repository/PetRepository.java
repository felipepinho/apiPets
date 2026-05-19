package br.com.apiPets.repository;

import br.com.apiPets.entity.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByTutorId(Long tutorId);
}
