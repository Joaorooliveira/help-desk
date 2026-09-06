package dev.joaorooliveira.help_desk.domain.funcionario.dto;

import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.SetorTipo;

public record FuncionarioAtualizarDTO(

    String nome,
    String email,
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
