package dev.joaorooliveira.help_desk.domain.funcionario;

import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioResponseDTO;
import dev.joaorooliveira.help_desk.infra.exception.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public FuncionarioResponseDTO salvarFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        Funcionario funcionario = funcionarioRepository.save(funcionarioRequestDTO.toEntity());
        return FuncionarioResponseDTO.fromEntity(funcionario);
    }

    public FuncionarioResponseDTO buscarFuncionarioPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado com o ID: " + id));
        return FuncionarioResponseDTO.fromEntity(funcionario);
    }



}
