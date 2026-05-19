package br.com.apiPets.repository;

import br.com.apiPets.entity.Consulta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPetId(Long petId);

    List<Consulta> findByVeterinarioId(Long veterinarioId);
}
