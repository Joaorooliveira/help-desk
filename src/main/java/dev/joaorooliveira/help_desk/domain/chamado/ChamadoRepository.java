package dev.joaorooliveira.help_desk.domain.chamado;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> , JpaSpecificationExecutor<Chamado> {
    boolean existsByFuncionarioId(Long funcionarioId);

    boolean existsByTecnicoId(Long idTecnico);

    boolean existsByEmail(String email);
}
