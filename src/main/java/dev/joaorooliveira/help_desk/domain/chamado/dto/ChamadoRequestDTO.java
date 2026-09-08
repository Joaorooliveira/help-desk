package dev.joaorooliveira.help_desk.domain.chamado.dto;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;
import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChamadoRequestDTO(

        @NotBlank(message = "O título do chamado é obrigatório")
        @Size(max = 200, message = "O título do chamado deve ter no máximo 200 caracteres")
        String titulo,

        @NotBlank(message = "A descrição do chamado é obrigatória")
        String descricao,

        @NotNull(message = "A categoria do chamado é obrigatória")
        CategoriaTipo categoria,

        @NotNull(message = "A prioridade do chamado é obrigatória")
        PrioridadeTipo prioridade,

        @NotNull(message = "O id do funcionário é obrigatório")
        Long funcionarioId
) {

    public Chamado toEntity(Funcionario funcionario) {
        Chamado chamado = new Chamado();
        chamado.setTitulo(this.titulo);
        chamado.setDescricao(this.descricao);
        chamado.setCategoria(this.categoria);
        chamado.setPrioridade(this.prioridade);
        chamado.setFuncionario(funcionario);
        return chamado;
    }
}
