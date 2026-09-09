package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoFuncionarioResponseDTO;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoRequestDTO;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoTecnicoResponseDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.FuncionarioRepository;
import dev.joaorooliveira.help_desk.infra.exception.EntidadeNaoEncontradaException;
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

    // Buscar para o Funcionario
    public ChamadoFuncionarioResponseDTO buscarChamadoFuncionarioPorId(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Chamado não encontrado com o ID: " + id));
        return ChamadoFuncionarioResponseDTO.fromEntity(chamado);
    }

    // Buscar para o Tecnico
    public ChamadoTecnicoResponseDTO buscarChamadoTecnicoPorId(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Chamado não encontrado com o ID: " + id));
        return ChamadoTecnicoResponseDTO.fromEntity(chamado);
    }

}
