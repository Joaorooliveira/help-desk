package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.projection.ChamadoPorPrioridadeProjection;
import dev.joaorooliveira.help_desk.projection.ChamadoPorStatusProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RelatorioChamadoRepository extends Repository<Chamado, Long> {


    @Query(
            value = """
                    SELECT
                        prioridade,
                        COUNT(*) AS total
                    FROM chamado
                    GROUP BY prioridade
                    """,
            nativeQuery = true
    )
    List<ChamadoPorPrioridadeProjection> chamadoPorPrioridade();

    @Query(
            value = """
                     SELECT
                        status,
                        COUNT(*) AS total
                     FROM chamado
                     GROUP BY status;
                    """,
            nativeQuery = true
    )
    List<ChamadoPorStatusProjection> chamadoPorStatus();

}
