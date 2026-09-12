package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.projection.ChamadoPorPrioridadeProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RelatorioChamadoRepository extends Repository<Chamado, Long> {


    @Query(
            value = """
                    SELECT prioridade, COUNT(*) AS total
                    FROM chamado
                    GROUP BY prioridade
                    """,
            nativeQuery = true
    )
    List<ChamadoPorPrioridadeProjection> chamadoPorPrioridade();

}
