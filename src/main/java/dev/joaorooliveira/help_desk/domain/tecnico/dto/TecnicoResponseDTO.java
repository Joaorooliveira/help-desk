package dev.joaorooliveira.help_desk.domain.tecnico.dto;

import dev.joaorooliveira.help_desk.domain.tecnico.EspecialidadeTipo;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;

public record TecnicoResponseDTO(

        Long id,
        String nome,
        String email,
        String ramal,
        EspecialidadeTipo especialidade
) {

    public static TecnicoResponseDTO fromEntity(Tecnico tecnico) {
        return new TecnicoResponseDTO(
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getEmail(),
                tecnico.getRamal(),
                tecnico.getEspecialidade()
        );
    }
}
