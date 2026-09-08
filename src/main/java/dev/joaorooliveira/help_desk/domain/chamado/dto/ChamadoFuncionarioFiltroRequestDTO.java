package dev.joaorooliveira.help_desk.domain.chamado.dto;

import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;

import java.time.LocalDate;

public record ChamadoFuncionarioFiltroRequestDTO(

        String titulo,
        String descricao,
        PrioridadeTipo prioridade,
        CategoriaTipo categoria,
        StatusTipo status,
        LocalDate dataAbertura,
        LocalDate dataConclusao
) {
}
