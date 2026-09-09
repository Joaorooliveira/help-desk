package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.domain.chamado.dto.*;
import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.FuncionarioRepository;
import dev.joaorooliveira.help_desk.infra.exception.EntidadeNaoEncontradaException;
import dev.joaorooliveira.help_desk.infra.specification.ChamadoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, FuncionarioRepository funcionarioRepository){
        this.chamadoRepository = chamadoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public ChamadoFuncionarioResponseDTO salvarChamado(ChamadoRequestDTO chamadoRequestDTO) {
        Funcionario funcionario = funcionarioRepository.findById(chamadoRequestDTO.funcionarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado com o ID: "
                        + chamadoRequestDTO.funcionarioId()));
        Chamado chamado = chamadoRequestDTO.toEntity(funcionario);
        chamadoRepository.save(chamado);
        return ChamadoFuncionarioResponseDTO.fromEntity(chamado);
    }

    // Listar chamados para Funcionario com filtros
    public Page<ChamadoFuncionarioResponseDTO> buscarChamadosFuncionario(Pageable pageable, ChamadoFuncionarioFiltroRequestDTO filtro) {
        return chamadoRepository.findAll(ChamadoSpecification.comFiltrosFuncionario(filtro), pageable)
                .map(ChamadoFuncionarioResponseDTO::fromEntity);
    }

    // Listar chamados para Tecnico com filtros
    public Page<ChamadoTecnicoResponseDTO> buscarChamadosTecnico(Pageable pageable, ChamadoTecnicoFiltroRequestDTO filtro) {
        return chamadoRepository.findAll(ChamadoSpecification.comFiltrosTecnico(filtro), pageable)
                .map(ChamadoTecnicoResponseDTO::fromEntity);
    }

    // Buscar para o Funcionario
    public ChamadoFuncionarioResponseDTO buscarChamadoFuncionarioPorId(Long id) {
        Chamado chamado = buscarChamadoPorId(id);
        return ChamadoFuncionarioResponseDTO.fromEntity(chamado);
    }

    // Buscar para o Tecnico
    public ChamadoTecnicoResponseDTO buscarChamadoTecnicoPorId(Long id) {
        Chamado chamado = buscarChamadoPorId(id);
        return ChamadoTecnicoResponseDTO.fromEntity(chamado);
    }



    private Chamado buscarChamadoPorId(Long id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Chamado não encontrado com o ID: " + id));
    }
}
