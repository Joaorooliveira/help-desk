package dev.joaorooliveira.help_desk.domain.chamado.dto;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;

import java.time.LocalDateTime;

public record ChamadoTecnicoResponseDTO(

        Long id,
        String titulo,
        String descricao,
        PrioridadeTipo prioridade,
        CategoriaTipo categoria,
        StatusTipo status,
        String solucao,
        String nomeFuncionario,
        String ramalFuncionario,
        String nomeTecnico,
        LocalDateTime dataAbertura,
        LocalDateTime dataConclusao
) {

    public static ChamadoTecnicoResponseDTO fromEntity(Chamado chamado) {
        return new ChamadoTecnicoResponseDTO(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getPrioridade(),
                chamado.getCategoria(),
                chamado.getStatus(),
                chamado.getSolucao(),
                chamado.getFuncionario().getNome(),
                chamado.getFuncionario().getRamal(),
                chamado.getTecnico() != null ? chamado.getTecnico().getNome() : null,
                chamado.getDataAbertura(),
                chamado.getDataConclusao()
        );
    }
}
