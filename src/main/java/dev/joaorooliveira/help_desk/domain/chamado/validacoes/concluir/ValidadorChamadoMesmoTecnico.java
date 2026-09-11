package dev.joaorooliveira.help_desk.domain.chamado.validacoes.concluir;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;
import dev.joaorooliveira.help_desk.infra.exception.RegraNegocioException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidadorChamadoMesmoTecnico implements ValidadorConcluirChamado{
    @Override
    public void validar(Chamado chamado, Tecnico tecnico) {
        if (!Objects.equals(chamado.getTecnico().getId(),tecnico.getId())){
            throw new RegraNegocioException("Somente o técnico responsável pode concluir o chamado");
        }
    }
}
