package dev.joaorooliveira.help_desk.domain.tecnico.dto;

import dev.joaorooliveira.help_desk.domain.tecnico.enums.EspecialidadeTipo;

public record TecnicoFiltroRequestDTO(
        String nome,
        String email,
        String ramal,
        EspecialidadeTipo especialidade
) {
}
