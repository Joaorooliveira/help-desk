package dev.joaorooliveira.help_desk.domain.tecnico;

import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoAtualizarDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoResponseDTO;
import dev.joaorooliveira.help_desk.infra.exception.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Transactional
    public TecnicoResponseDTO salvarTecnico(TecnicoRequestDTO tecnicoRequestDTO) {
        Tecnico tecnico = tecnicoRepository.save(tecnicoRequestDTO.toEntity());
        return TecnicoResponseDTO.fromEntity(tecnico);
    }

    public TecnicoResponseDTO buscarTecnicoPorId(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Técnico não encontrado com o ID: " + id));
        return TecnicoResponseDTO.fromEntity(tecnico);
    }

    // Lembrar de validar se o tecnico nao tem chamados associados antes de deletar
    @Transactional
    public void deletarTecnico(Long id) {
        if (!tecnicoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Técnico não encontrado com o ID: " + id);
        }
        tecnicoRepository.deleteById(id);
    }

    @Transactional
    public TecnicoResponseDTO atualizarTecnico(Long id, TecnicoAtualizarDTO tecnicoAtualizarDTO) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Técnico não encontrado com o ID: " + id));
        tecnicoAtualizarDTO.preencher(tecnico);
        return TecnicoResponseDTO.fromEntity(tecnico);
    }
}
