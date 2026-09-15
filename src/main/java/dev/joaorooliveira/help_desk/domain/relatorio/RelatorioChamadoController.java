package dev.joaorooliveira.help_desk.domain.relatorio;

import dev.joaorooliveira.help_desk.projection.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@Tag(
        name = "Relatórios",
        description = "Consultas e estatísticas relacionadas aos chamados de suporte"
)
public class RelatorioChamadoController {

    private final RelatorioChamadoService relatorioChamadoService;

    public RelatorioChamadoController(RelatorioChamadoService relatorioChamadoService) {
        this.relatorioChamadoService = relatorioChamadoService;
    }

    @Operation(
            summary = "Consultar chamados por prioridade",
            description = "Retorna a quantidade de chamados agrupada por prioridade."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Relatório gerado com sucesso"
            )
    })
    @GetMapping("/chamados/por-prioridade")
    public ResponseEntity<List<ChamadoPorPrioridadeProjection>> chamadoPorPrioridade() {
        return ResponseEntity.ok(
                relatorioChamadoService.buscarChamadosPorPrioridade()
        );
    }

    @Operation(
            summary = "Consultar chamados por status",
            description = "Retorna a quantidade de chamados agrupada por status."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Relatório gerado com sucesso"
            )
    })
    @GetMapping("/chamados/por-status")
    public ResponseEntity<List<ChamadoPorStatusProjection>> chamadoPorStatus() {
        return ResponseEntity.ok(
                relatorioChamadoService.buscarChamadosPorStatus()
        );
    }

    @Operation(
            summary = "Consultar chamados por categoria",
            description = "Retorna a quantidade de chamados agrupada por categoria."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Relatório gerado com sucesso"
            )
    })
    @GetMapping("/chamados/por-categoria")
    public ResponseEntity<List<ChamadoPorCategoriaProjection>> chamadoPorCategoria() {
        return ResponseEntity.ok(
                relatorioChamadoService.buscarChamadosPorCategoria()
        );
    }

    @Operation(
            summary = "Consultar chamados por técnico",
            description = "Retorna a quantidade de chamados associada a cada técnico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Relatório gerado com sucesso"
            )
    })
    @GetMapping("/chamados/por-tecnico")
    public ResponseEntity<List<ChamadoPorTecnicoProjection>> chamadoPorTecnico() {
        return ResponseEntity.ok(
                relatorioChamadoService.buscarChamadosPorTecnico()
        );
    }

    @Operation(
            summary = "Consultar chamados por setor",
            description = "Retorna a quantidade de chamados agrupada pelo setor dos funcionários."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Relatório gerado com sucesso"
            )
    })
    @GetMapping("/chamados/por-setor")
    public ResponseEntity<List<ChamadoPorSetorProjection>> chamadoPorSetor() {
        return ResponseEntity.ok(
                relatorioChamadoService.buscarChamadosPorSetor()
        );
    }
}
