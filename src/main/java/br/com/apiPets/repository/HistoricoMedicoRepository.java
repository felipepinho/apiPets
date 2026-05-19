package br.com.apiPets.repository;

import br.com.apiPets.entity.HistoricoMedico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedico, Long> {
    List<HistoricoMedico> findByPetId(Long petId);
}
