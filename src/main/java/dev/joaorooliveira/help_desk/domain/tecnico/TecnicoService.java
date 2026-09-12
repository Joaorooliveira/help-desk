package dev.joaorooliveira.help_desk.domain.tecnico;

import dev.joaorooliveira.help_desk.domain.chamado.ChamadoRepository;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoAtualizarDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoResponseDTO;
import dev.joaorooliveira.help_desk.infra.exception.EntidadeNaoEncontradaException;
import dev.joaorooliveira.help_desk.infra.exception.RegraNegocioException;
import dev.joaorooliveira.help_desk.infra.specification.TecnicoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final ChamadoRepository chamadoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository, ChamadoRepository chamadoRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.chamadoRepository = chamadoRepository;
    }

    @Transactional
    public TecnicoResponseDTO salvarTecnico(TecnicoRequestDTO tecnicoRequestDTO) {
        Tecnico tecnico = tecnicoRepository.save(tecnicoRequestDTO.toEntity());
        return TecnicoResponseDTO.fromEntity(tecnico);
    }

    public Page<TecnicoResponseDTO> buscarTecnicos(Pageable pageable, TecnicoFiltroRequestDTO filtro) {
        return tecnicoRepository.findAll(TecnicoSpecification.comFiltros(filtro), pageable)
                .map(TecnicoResponseDTO::fromEntity);
    }

    public TecnicoResponseDTO buscarTecnicoPorId(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Técnico não encontrado com o ID: " + id));
        return TecnicoResponseDTO.fromEntity(tecnico);
    }

    @Transactional
    public void deletarTecnico(Long id) {
        if (!tecnicoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Técnico não encontrado com o ID: " + id);
        }
        if(chamadoRepository.existsByTecnicoId(id)){
            throw new RegraNegocioException("Não é possível excluir o funcionário, pois existem chamados associados a ele");
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
