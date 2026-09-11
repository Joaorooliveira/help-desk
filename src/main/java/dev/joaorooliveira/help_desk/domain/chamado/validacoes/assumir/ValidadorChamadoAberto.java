package dev.joaorooliveira.help_desk.domain.chamado.validacoes.assumir;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;
import dev.joaorooliveira.help_desk.infra.exception.RegraNegocioException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChamadoAberto implements ValidadorAssumirChamado{
    @Override
    public void validar(Chamado chamado, Long idTecnico) {
        if(chamado.getStatus() != StatusTipo.ABERTO){
            throw new RegraNegocioException("Chamado precisa estar com status aberto");
        }
    }
}
