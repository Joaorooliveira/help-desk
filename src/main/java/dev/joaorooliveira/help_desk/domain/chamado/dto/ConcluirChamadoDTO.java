package dev.joaorooliveira.help_desk.domain.chamado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConcluirChamadoDTO(

        @NotBlank(message = "A solução do chamado é obrigatória")
        String solucao,
        @NotNull(message = "O id do tecnico é obrigatório")
        Long idTecnico
) {
}
