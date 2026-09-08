package dev.joaorooliveira.help_desk.domain.tecnico;

import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoAtualizarDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoResponseDTO;
import dev.joaorooliveira.help_desk.infra.specification.TecnicoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    public ResponseEntity<TecnicoResponseDTO> salvar(TecnicoRequestDTO tecnicoRequestDTO) {
        TecnicoResponseDTO tecnicoResponseDTO = tecnicoService.salvarTecnico(tecnicoRequestDTO);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnicoResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(tecnicoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<TecnicoResponseDTO>> buscar(Pageable pageable, TecnicoFiltroRequestDTO filtro) {
        Page<TecnicoResponseDTO> tecnicos = tecnicoService.buscarTecnicos(pageable, filtro);
        return ResponseEntity.ok(tecnicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> buscarPorId(Long id) {
        TecnicoResponseDTO tecnicoResponseDTO = tecnicoService.buscarTecnicoPorId(id);
        return ResponseEntity.ok(tecnicoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> atualizar(@PathVariable Long id, TecnicoAtualizarDTO tecnicoAtualizarDTO) {
        TecnicoResponseDTO tecnicoResponseDTO = tecnicoService.atualizarTecnico(id, tecnicoAtualizarDTO);
        return ResponseEntity.ok(tecnicoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tecnicoService.deletarTecnico(id);
        return ResponseEntity.noContent().build();
    }
}
