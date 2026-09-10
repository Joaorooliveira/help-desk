package dev.joaorooliveira.help_desk.domain.chamado.validacoes.criacao;

import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoRequestDTO;

public interface ValidadorCriacaoChamado {

    void validar(ChamadoRequestDTO dados);
}
