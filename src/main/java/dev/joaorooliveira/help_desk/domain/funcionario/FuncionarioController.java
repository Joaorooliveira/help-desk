package dev.joaorooliveira.help_desk.domain.funcionario;

import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioResponseDTO;
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
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> salvar(
            @RequestBody @Valid FuncionarioRequestDTO funcionarioRequestDTO) {

        FuncionarioResponseDTO funcionarioResponseDTO = funcionarioService.salvarFuncionario(funcionarioRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(funcionarioResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioResponseDTO>> buscar(@PageableDefault(size = 10) Pageable pageable,
                                                               FuncionarioFiltroRequestDTO filtro) {

        Page<FuncionarioResponseDTO> funcionarios = funcionarioService.buscarFuncionarios(pageable, filtro);
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        FuncionarioResponseDTO funcionarioResponseDTO = funcionarioService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(funcionarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

}
