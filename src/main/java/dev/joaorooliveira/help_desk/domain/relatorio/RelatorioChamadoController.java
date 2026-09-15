package dev.joaorooliveira.help_desk.domain.relatorio;

import dev.joaorooliveira.help_desk.projection.*;
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
        return ResponseEntity.ok(relatorioChamadoService.buscarChamadosPorPrioridade());
    }

    @GetMapping("/chamados/por-status")
    public ResponseEntity<List<ChamadoPorStatusProjection>> chamadoPorStatus(){
        return ResponseEntity.ok(relatorioChamadoService.buscarChamadosPorStatus());
    }

    @GetMapping("/chamados/por-categoria")
    public ResponseEntity<List<ChamadoPorCategoriaProjection>> chamadoPorCategoria(){
        return ResponseEntity.ok(relatorioChamadoService.buscarChamadosPorCategoria());
    }

    @GetMapping("/chamados/por-tecnico")
    public ResponseEntity<List<ChamadoPorTecnicoProjection>> chamadoPorTecnico(){
        return ResponseEntity.ok(relatorioChamadoService.buscarChamadosPorTecnico());
    }

    @GetMapping("/chamados/por-setor")
    public ResponseEntity<List<ChamadoPorSetorProjection>> chamadoPorSetor(){
        return ResponseEntity.ok(relatorioChamadoService.buscarChamadosPorSetor());
    }
}
