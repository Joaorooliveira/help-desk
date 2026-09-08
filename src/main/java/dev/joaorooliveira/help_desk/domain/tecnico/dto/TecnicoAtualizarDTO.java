package dev.joaorooliveira.help_desk.domain.tecnico.dto;

import dev.joaorooliveira.help_desk.domain.tecnico.enums.EspecialidadeTipo;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record TecnicoAtualizarDTO(

        @Size(max = 150,message = "Nome nao pode ter mais de 150 caracteres")
        String nome,

        @Email(message = "Email invalido")
        String email,

        @Size(max = 10,message = "Ramal nao pode ter mais de 10 caracteres")
        String ramal,

        EspecialidadeTipo especialidade
) {

    public void preencher(Tecnico tecnico) {
        if (nome != null) {
            tecnico.setNome(nome);
        }
        if (email != null) {
            tecnico.setEmail(email);
        }
        if (ramal != null) {
            tecnico.setRamal(ramal);
        }
        if (especialidade != null) {
            tecnico.setEspecialidade(especialidade);
        }
    }
}
