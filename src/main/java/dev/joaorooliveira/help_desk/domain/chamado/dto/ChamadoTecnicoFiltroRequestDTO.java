package dev.joaorooliveira.help_desk.domain.chamado.dto;

import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;

import java.time.LocalDate;

public record ChamadoTecnicoFiltroRequestDTO(

        String titulo,
        String descricao,
        CategoriaTipo categoria,
        PrioridadeTipo prioridade,
        StatusTipo status,
        String nomeTecnico,
        String nomeFuncionario,
        LocalDate dataAbertura,
        LocalDate dataConclusao
) {
}
