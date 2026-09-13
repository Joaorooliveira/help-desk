package dev.joaorooliveira.help_desk.domain.relatorio;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.projection.*;
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
                     GROUP BY status
                    """,
            nativeQuery = true
    )
    List<ChamadoPorStatusProjection> chamadoPorStatus();

    @Query(
            value = """
                    SELECT
                        categoria,
                        COUNT(*) AS total
                    FROM chamado
                    GROUP BY categoria
                    """,
            nativeQuery = true
    )
    List<ChamadoPorCategoriaProjection> chamadoPorCategoria();


    @Query(
            value = """
                    SELECT
                        t.nome AS nome,
                        count(c.id) AS total
                    FROM chamado c
                    LEFT JOIN tecnico t
                    ON c.tecnico_id = t.id
                    GROUP BY t.nome
                    """,
            nativeQuery = true
    )
    List<ChamadoPorTecnicoProjection> chamadoPorTecnico();


    @Query(
            value = """
                    SELECT
                        f.setor,
                        COUNT(c.id) AS total
                    FROM funcionario f
                    LEFT JOIN chamado c
                        ON c.funcionario_id = f.id
                    GROUP BY f.setor
                    """,
            nativeQuery = true
    )
    List<ChamadoPorSetorProjection> chamadoPorSetor();
}
