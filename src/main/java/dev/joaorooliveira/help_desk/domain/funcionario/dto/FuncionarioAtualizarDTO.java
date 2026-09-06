package dev.joaorooliveira.help_desk.domain.funcionario.dto;

import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.SetorTipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioAtualizarDTO(

        String nome,

        @Email(message = "O email do funcionário deve ser válido")
        String email,

        @Size(max = 10)
        String ramal,

        SetorTipo setor
) {

    public void preencher(Funcionario funcionario) {
        if(this.nome!=null){
            funcionario.setNome(this.nome);
        }
        if(this.email!=null){
            funcionario.setEmail(this.email);
        }
        if(this.ramal!=null){
            funcionario.setRamal(this.ramal);
        }
        if(this.setor!=null){
            funcionario.setSetor(this.setor);
        }
    }
}
