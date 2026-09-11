package dev.joaorooliveira.help_desk.domain.chamado.validacoes.concluir;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;

public interface ValidadorConcluirChamado {

    void validar(Chamado chamado, Tecnico tecnico);

}
