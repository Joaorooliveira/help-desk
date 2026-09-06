package dev.joaorooliveira.help_desk.domain.funcionario.dto;

import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.SetorTipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioRequestDTO(

        @NotBlank(message = "O nome do funcionário é obrigatório")
        String nome,

        @NotBlank(message = "O email do funcionário é obrigatório")
        @Email(message = "O email do funcionário deve ser válido")
        String email,

        @NotBlank(message = "O ramal do funcionário é obrigatório")
        @Size(max = 10)
        String ramal,

        @NotNull(message = "O setor do funcionário é obrigatório")
        SetorTipo setor

) {

    public Funcionario toEntity(){
        Funcionario funcionario = new Funcionario();
        preencher(funcionario);
        return funcionario;
    }

    private void preencher(Funcionario funcionario) {
        funcionario.setNome(this.nome);
        funcionario.setEmail(this.email);
        funcionario.setRamal(this.ramal);
        funcionario.setSetor(this.setor);
    }
}
