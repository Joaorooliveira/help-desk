package dev.joaorooliveira.help_desk.domain.funcionario.dto;

import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.SetorTipo;

public record FuncionarioResponseDTO(
        Long id,
        String nome,
        String email,
        String ramal,
        SetorTipo setor
) {

    public static FuncionarioResponseDTO fromEntity(Funcionario funcionario){
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getRamal(),
                funcionario.getSetor()
        );
    }
}
