package dev.joaorooliveira.help_desk.domain.chamado.dto;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;

import java.time.LocalDateTime;

public record ChamadoFuncionarioResponseDTO(
        Long id,
        String titulo,
        String descricao,
        CategoriaTipo categoria,
        PrioridadeTipo prioridade,
        StatusTipo status,
        LocalDateTime dataAbertura,
        LocalDateTime dataConclusao,
        String nomeTecnico,
        String ramalTecnico
) {

    public static ChamadoFuncionarioResponseDTO fromEntity(Chamado chamado) {
        return new ChamadoFuncionarioResponseDTO(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getCategoria(),
                chamado.getPrioridade(),
                chamado.getStatus(),
                chamado.getDataAbertura(),
                chamado.getDataConclusao(),
                chamado.getTecnico() != null ? chamado.getTecnico().getNome() : null,
                chamado.getTecnico() != null ? chamado.getTecnico().getRamal() : null
        );
    }
}
