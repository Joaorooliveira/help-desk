package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.domain.chamado.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/chamados")
@Tag(
        name = "Chamados",
        description = "Operações relacionadas ao gerenciamento de chamados de suporte técnico"
)
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @Operation(
            summary = "Abrir chamado",
            description = "Cria um novo chamado associado a um funcionário."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Chamado criado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = ChamadoFuncionarioResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da requisição inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Funcionário não encontrado"
            )
    })
    @PostMapping
    public ResponseEntity<ChamadoFuncionarioResponseDTO> salvar(
            @RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO) {

        ChamadoFuncionarioResponseDTO responseDTO =
                chamadoService.salvarChamado(chamadoRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @Operation(
            summary = "Listar chamados para funcionários",
            description = "Retorna uma página de chamados com filtros opcionais."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamados encontrados"
            )
    })
    @GetMapping("/funcionario")
    public ResponseEntity<Page<ChamadoFuncionarioResponseDTO>> buscarChamadosFuncionario(
            @PageableDefault(size = 10)
            Pageable pageable,
            ChamadoFuncionarioFiltroRequestDTO filtro) {

        Page<ChamadoFuncionarioResponseDTO> chamados =
                chamadoService.buscarChamadosFuncionario(pageable, filtro);

        return ResponseEntity.ok(chamados);
    }

    @Operation(
            summary = "Listar chamados para técnicos",
            description = "Retorna uma página de chamados com filtros opcionais."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamados encontrados"
            )
    })
    @GetMapping("/tecnico")
    public ResponseEntity<Page<ChamadoTecnicoResponseDTO>> buscarChamadosTecnico(
            @PageableDefault(size = 10)
            Pageable pageable,
            ChamadoTecnicoFiltroRequestDTO filtro) {

        Page<ChamadoTecnicoResponseDTO> chamados =
                chamadoService.buscarChamadosTecnico(pageable, filtro);

        return ResponseEntity.ok(chamados);
    }

    @Operation(
            summary = "Buscar chamado por ID para funcionário",
            description = "Retorna os dados de um chamado na visão do funcionário."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ChamadoFuncionarioResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado não encontrado"
            )
    })
    @GetMapping("/funcionario/{id}")
    public ResponseEntity<ChamadoFuncionarioResponseDTO> buscarChamadoFuncionarioPorId(
            @Parameter(
                    description = "ID do chamado",
                    example = "1"
            )
            @PathVariable Long id) {

        ChamadoFuncionarioResponseDTO chamado =
                chamadoService.buscarChamadoFuncionarioPorId(id);

        return ResponseEntity.ok(chamado);
    }

    @Operation(
            summary = "Buscar chamado por ID para técnico",
            description = "Retorna os dados de um chamado na visão do técnico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ChamadoTecnicoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado não encontrado"
            )
    })
    @GetMapping("/tecnico/{id}")
    public ResponseEntity<ChamadoTecnicoResponseDTO> buscarChamadoTecnicoPorId(
            @Parameter(
                    description = "ID do chamado",
                    example = "1"
            )
            @PathVariable Long id) {

        ChamadoTecnicoResponseDTO chamado =
                chamadoService.buscarChamadoTecnicoPorId(id);

        return ResponseEntity.ok(chamado);
    }

    @Operation(
            summary = "Assumir chamado",
            description = "Associa um técnico ao chamado e altera o status para EM_ANDAMENTO."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado assumido com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = ChamadoTecnicoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado ou técnico não encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Chamado não pode ser assumido devido a uma regra de negócio"
            )
    })
    @PostMapping("/{idChamado}/assumir")
    public ResponseEntity<ChamadoTecnicoResponseDTO> assumirChamado(
            @Parameter(
                    description = "ID do chamado",
                    example = "1"
            )
            @PathVariable Long idChamado,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "ID do técnico que irá assumir o chamado",
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    type = "integer",
                                    format = "int64",
                                    example = "2"
                            )
                    )
            )
            @RequestBody Long idTecnico) {

        ChamadoTecnicoResponseDTO responseDTO =
                chamadoService.assumirChamado(idTecnico, idChamado);

        return ResponseEntity.ok(responseDTO);
    }

    @Operation(
            summary = "Concluir chamado",
            description = "Conclui um chamado em andamento, registrando a solução e a data de conclusão."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado concluído com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = ChamadoTecnicoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado ou técnico não encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Chamado não pode ser concluído devido a uma regra de negócio"
            )
    })
    @PostMapping("/{idChamado}/concluir")
    public ResponseEntity<ChamadoTecnicoResponseDTO> concluirChamado(
            @Parameter(
                    description = "ID do chamado",
                    example = "1"
            )
            @PathVariable Long idChamado,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para concluir o chamado",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ConcluirChamadoDTO.class)
                    )
            )
            @RequestBody @Valid ConcluirChamadoDTO dto) {

        ChamadoTecnicoResponseDTO responseDTO =
                chamadoService.concluirChamado(idChamado, dto);

        return ResponseEntity.ok(responseDTO);
    }
}
