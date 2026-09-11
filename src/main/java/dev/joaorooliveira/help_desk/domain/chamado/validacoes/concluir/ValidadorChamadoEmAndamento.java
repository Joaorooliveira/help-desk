package dev.joaorooliveira.help_desk.domain.chamado.validacoes.concluir;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;
import dev.joaorooliveira.help_desk.infra.exception.RegraNegocioException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChamadoEmAndamento implements ValidadorConcluirChamado{
    @Override
    public void validar(Chamado chamado, Tecnico tecnico) {
        if(chamado.getStatus()!= StatusTipo.EM_ANDAMENTO){
            throw new RegraNegocioException("Chamado precisa estar em andamento para concluir");
        }
    }
}
