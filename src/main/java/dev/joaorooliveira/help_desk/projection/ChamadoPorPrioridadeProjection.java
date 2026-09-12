package dev.joaorooliveira.help_desk.projection;

import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;

import java.math.BigDecimal;

public interface ChamadoPorPrioridadeProjection {

    PrioridadeTipo getPrioridade();

    BigDecimal getTotal();
}
