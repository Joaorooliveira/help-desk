package dev.joaorooliveira.help_desk.domain.chamado.validacoes.assumir;

public interface ValidadorAssumirChamado {

    void validar(Long idTecnico,Long idChamado);
}
