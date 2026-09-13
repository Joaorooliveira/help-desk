package dev.joaorooliveira.help_desk.domain.relatorio;

import dev.joaorooliveira.help_desk.projection.ChamadoPorPrioridadeProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioChamadoController {

    private final RelatorioChamadoService relatorioChamadoService;

    public RelatorioChamadoController(RelatorioChamadoService relatorioChamadoService) {
        this.relatorioChamadoService = relatorioChamadoService;
    }

    @GetMapping("/chamados/por-prioridade")
    public ResponseEntity<List<ChamadoPorPrioridadeProjection>> chamadoPorPrioridade(){
        return ResponseEntity.ok(relatorioChamadoService.chamadoPorPrioridade());
    }
}
