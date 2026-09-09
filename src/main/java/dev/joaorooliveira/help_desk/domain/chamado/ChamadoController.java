package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoFuncionarioFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoFuncionarioResponseDTO;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoRequestDTO;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoTecnicoResponseDTO;
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
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<ChamadoFuncionarioResponseDTO> salvar(@RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO) {
        ChamadoFuncionarioResponseDTO responseDTO = chamadoService.salvarChamado(chamadoRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ChamadoFuncionarioResponseDTO>> buscarChamadosFuncionario(@PageableDefault(size = 10) Pageable pageable,
                                                                                         ChamadoFuncionarioFiltroRequestDTO filtro) {
        Page<ChamadoFuncionarioResponseDTO> chamados = chamadoService.buscarChamadosFuncionario(pageable, filtro);

        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoFuncionarioResponseDTO> buscarChamadoFuncionarioPorId(@PathVariable Long id) {
        ChamadoFuncionarioResponseDTO chamado = chamadoService.buscarChamadoFuncionarioPorId(id);
        return ResponseEntity.ok(chamado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoTecnicoResponseDTO> buscarChamadoTecnicoPorId(@PathVariable Long id) {
        ChamadoTecnicoResponseDTO chamado = chamadoService.buscarChamadoTecnicoPorId(id);
        return ResponseEntity.ok(chamado);
    }


}
