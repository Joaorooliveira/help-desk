package dev.joaorooliveira.help_desk.projection;

import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;

import java.math.BigDecimal;

public interface ChamadoPorStatusProjection {

    StatusTipo getStatus();
    Long getTotal();

}
