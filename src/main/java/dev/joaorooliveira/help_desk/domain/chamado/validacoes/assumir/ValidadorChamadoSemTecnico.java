package dev.joaorooliveira.help_desk.domain.chamado.validacoes.assumir;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.infra.exception.RegraNegocioException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorChamadoSemTecnico implements ValidadorAssumirChamado{

    @Override
    public void validar(Chamado chamado, Long idTecnico) {
        if(chamado.getTecnico()!=null) {
            throw new RegraNegocioException("Esse chamado ja foi assumido");
        }
    }
}
