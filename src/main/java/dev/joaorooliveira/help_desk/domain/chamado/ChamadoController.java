package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoFuncionarioResponseDTO;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<ChamadoFuncionarioResponseDTO> salvarChamado(@RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO) {
        ChamadoFuncionarioResponseDTO responseDTO = chamadoService.salvarChamado(chamadoRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

}
