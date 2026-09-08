package dev.joaorooliveira.help_desk.domain.chamado.dto;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;

public record ChamadoFuncionarioAtualizarDTO(

        String titulo,
        String descricao,
        CategoriaTipo categoria,
        PrioridadeTipo prioridade
) {

    public void preencher(Chamado chamado) {
        if(titulo != null) {
            chamado.setTitulo(titulo);
        }
        if(descricao != null) {
            chamado.setDescricao(descricao);
        }
        if(categoria != null) {
            chamado.setCategoria(categoria);
        }
        if(prioridade != null) {
            chamado.setPrioridade(prioridade);
        }
    }
}
