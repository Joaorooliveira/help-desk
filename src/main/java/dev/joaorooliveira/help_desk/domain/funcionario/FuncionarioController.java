package dev.joaorooliveira.help_desk.domain.funcionario;

import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioAtualizarDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioResponseDTO;
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
@RequestMapping("/funcionarios")
@Tag(
        name = "Funcionários",
        description = "Operações relacionadas ao gerenciamento de funcionários"
)
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Operation(
            summary = "Cadastrar funcionário",
            description = "Cadastra um novo funcionário no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Funcionário cadastrado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = FuncionarioResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da requisição inválidos"
            )
    })
    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> salvar(
            @RequestBody @Valid FuncionarioRequestDTO funcionarioRequestDTO) {

        FuncionarioResponseDTO funcionarioResponseDTO =
                funcionarioService.salvarFuncionario(funcionarioRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioResponseDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(funcionarioResponseDTO);
    }

    @Operation(
            summary = "Listar funcionários",
            description = "Retorna uma página de funcionários com filtros opcionais."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Funcionários encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<Page<FuncionarioResponseDTO>> buscar(
            @PageableDefault(size = 10) Pageable pageable,
            FuncionarioFiltroRequestDTO filtro) {

        Page<FuncionarioResponseDTO> funcionarios =
                funcionarioService.buscarFuncionarios(pageable, filtro);

        return ResponseEntity.ok(funcionarios);
    }

    @Operation(
            summary = "Buscar funcionário por ID",
            description = "Retorna os dados de um funcionário específico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Funcionário encontrado",
                    content = @Content(
                            schema = @Schema(implementation = FuncionarioResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Funcionário não encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(
            @Parameter(
                    description = "ID do funcionário",
                    example = "1"
            )
            @PathVariable Long id) {

        FuncionarioResponseDTO funcionarioResponseDTO =
                funcionarioService.buscarFuncionarioPorId(id);

        return ResponseEntity.ok(funcionarioResponseDTO);
    }

    @Operation(
            summary = "Atualizar funcionário",
            description = "Atualiza os dados de um funcionário existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Funcionário atualizado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = FuncionarioResponseDTO.class)
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
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(
            @Parameter(
                    description = "ID do funcionário",
                    example = "1"
            )
            @PathVariable Long id,
            @RequestBody @Valid FuncionarioAtualizarDTO funcionarioAtualizarDTO) {

        FuncionarioResponseDTO funcionarioResponseDTO =
                funcionarioService.atualizarFuncionario(
                        id,
                        funcionarioAtualizarDTO
                );

        return ResponseEntity.ok(funcionarioResponseDTO);
    }

    @Operation(
            summary = "Excluir funcionário",
            description = "Remove um funcionário do sistema. Funcionários que possuem chamados associados não podem ser excluídos."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Funcionário excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Funcionário não encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Funcionário não pode ser excluído devido a uma regra de negócio"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(
                    description = "ID do funcionário",
                    example = "1"
            )
            @PathVariable Long id) {

        funcionarioService.deletarFuncionario(id);

        return ResponseEntity.noContent().build();
    }
}