package dev.joaorooliveira.help_desk.domain.tecnico.dto;

import dev.joaorooliveira.help_desk.domain.tecnico.enums.EspecialidadeTipo;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TecnicoRequestDTO(
        @NotBlank(message = "Nome nao pode ser nulo ou vazio")
        @Size(max = 150,message = "Nome nao pode ter mais de 150 caracteres")
    String nome,

    @NotBlank(message = "Email nao pode ser nulo ou vazio")
    @Email(message = "Email invalido")
    String email,

    @NotBlank(message = "Ramal nao pode ser nulo ou vazio")
    @Size(max = 10,message = "Ramal nao pode ter mais de 10 caracteres")
    String ramal,

    @NotNull(message = "Especialidade nao pode ser nulo")
    EspecialidadeTipo especialidade
) {

    public Tecnico toEntity() {
        Tecnico tecnico = new Tecnico();
        preencher(tecnico);
        return tecnico;
    }

    private void preencher(Tecnico tecnico) {
        tecnico.setNome(nome);
        tecnico.setEmail(email);
        tecnico.setRamal(ramal);
        tecnico.setEspecialidade(especialidade);
    }
}
