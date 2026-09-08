package dev.joaorooliveira.help_desk.domain.funcionario.dto;

import dev.joaorooliveira.help_desk.domain.funcionario.enums.SetorTipo;

public record FuncionarioFiltroRequestDTO(
    String nome,
    String email,
    String ramal,
    SetorTipo setor
) {
}
