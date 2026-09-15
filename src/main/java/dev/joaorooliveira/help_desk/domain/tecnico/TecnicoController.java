package dev.joaorooliveira.help_desk.domain.tecnico;

import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoAtualizarDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoResponseDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tecnicos")
@Tag(
        name = "Técnicos",
        description = "Operações relacionadas ao gerenciamento de técnicos de suporte"
)
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @Operation(
            summary = "Cadastrar técnico",
            description = "Cadastra um novo técnico no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Técnico cadastrado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = TecnicoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da requisição inválidos"
            )
    })
    @PostMapping
    public ResponseEntity<TecnicoResponseDTO> salvar(@RequestBody @Valid TecnicoRequestDTO tecnicoRequestDTO) {
        TecnicoResponseDTO tecnicoResponseDTO = tecnicoService.salvarTecnico(tecnicoRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnicoResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(tecnicoResponseDTO);
    }

    @Operation(
            summary = "Listar técnicos",
            description = "Retorna uma página de técnicos com filtros opcionais."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Técnicos encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<Page<TecnicoResponseDTO>> buscar(Pageable pageable, TecnicoFiltroRequestDTO filtro) {
        Page<TecnicoResponseDTO> tecnicos = tecnicoService.buscarTecnicos(pageable, filtro);
        return ResponseEntity.ok(tecnicos);
    }

    @Operation(
            summary = "Buscar técnico por ID",
            description = "Retorna os dados de um técnico específico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Técnico encontrado",
                    content = @Content(
                            schema = @Schema(implementation = TecnicoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Técnico não encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> buscarPorId(
            @Parameter(
                    description = "ID do técnico",
                    example = "1"
            )
            @PathVariable Long id) {

        TecnicoResponseDTO tecnicoResponseDTO = tecnicoService.buscarTecnicoPorId(id);
        return ResponseEntity.ok(tecnicoResponseDTO);
    }

    @Operation(
            summary = "Atualizar técnico",
            description = "Atualiza os dados de um técnico existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Técnico atualizado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = TecnicoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da requisição inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Técnico não encontrado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> atualizar(
            @Parameter(
                    description = "ID do técnico",
                    example = "1"
            )
            @PathVariable Long id,
            @RequestBody @Valid TecnicoAtualizarDTO tecnicoAtualizarDTO) {

        TecnicoResponseDTO tecnicoResponseDTO = tecnicoService.atualizarTecnico(id, tecnicoAtualizarDTO);
        return ResponseEntity.ok(tecnicoResponseDTO);
    }

    @Operation(
            summary = "Excluir técnico",
            description = "Remove um técnico do sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Técnico excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Técnico não encontrado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(
                    description = "ID do técnico",
                    example = "1"
            )
            @PathVariable Long id) {

        tecnicoService.deletarTecnico(id);
        return ResponseEntity.noContent().build();
    }
}
