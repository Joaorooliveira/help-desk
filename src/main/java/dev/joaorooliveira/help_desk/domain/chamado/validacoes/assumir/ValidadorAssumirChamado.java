package dev.joaorooliveira.help_desk.domain.chamado.validacoes.assumir;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;

public interface ValidadorAssumirChamado {

    void validar(Chamado chamado, Long idTecnico);
}
