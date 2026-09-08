package dev.joaorooliveira.help_desk.domain.chamado.dto;

import jakarta.validation.constraints.NotBlank;

public record ConcluirChamadoDTO(

        @NotBlank(message = "A solução do chamado é obrigatória")
        String solucao
) {
}
