package dev.joaorooliveira.help_desk.domain.relatorio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioChamadoController {

    private final RelatorioChamadoService relatorioChamadoService;

    public RelatorioChamadoController(RelatorioChamadoService relatorioChamadoService) {
        this.relatorioChamadoService = relatorioChamadoService;
    }

}
