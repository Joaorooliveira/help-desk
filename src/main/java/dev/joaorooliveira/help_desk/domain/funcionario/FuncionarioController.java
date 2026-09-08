package dev.joaorooliveira.help_desk.domain.funcionario;

import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
