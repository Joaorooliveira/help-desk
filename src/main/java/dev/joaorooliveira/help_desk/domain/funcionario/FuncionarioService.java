package dev.joaorooliveira.help_desk.domain.funcionario;

import dev.joaorooliveira.help_desk.domain.chamado.ChamadoRepository;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioAtualizarDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioResponseDTO;
import dev.joaorooliveira.help_desk.infra.exception.EntidadeNaoEncontradaException;
import dev.joaorooliveira.help_desk.infra.exception.RegraNegocioException;
import dev.joaorooliveira.help_desk.infra.specification.FuncionarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final ChamadoRepository chamadoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, ChamadoRepository chamadoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.chamadoRepository = chamadoRepository;
    }

    @Transactional
    public FuncionarioResponseDTO salvarFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        Funcionario funcionario = funcionarioRepository.save(funcionarioRequestDTO.toEntity());
        return FuncionarioResponseDTO.fromEntity(funcionario);
    }


    public Page<FuncionarioResponseDTO> buscarFuncionarios(Pageable pageable, FuncionarioFiltroRequestDTO filtro) {
        return funcionarioRepository.findAll(FuncionarioSpecification.comFiltros(filtro), pageable)
                .map(FuncionarioResponseDTO::fromEntity);
    }

    public FuncionarioResponseDTO buscarFuncionarioPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado com o ID: " + id));
        return FuncionarioResponseDTO.fromEntity(funcionario);
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Funcionário não encontrado com o ID: " + id);
        }
        if(chamadoRepository.existsByFuncionarioId(id)){
            throw new RegraNegocioException("Não é possível excluir o funcionário, pois existem chamados associados a ele");
        }
        funcionarioRepository.deleteById(id);
    }


    @Transactional
    public FuncionarioResponseDTO atualizarFuncionario(Long id , FuncionarioAtualizarDTO funcionarioAtualizarDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado com o ID: " + id));
        funcionarioAtualizarDTO.preencher(funcionario);
        return FuncionarioResponseDTO.fromEntity(funcionario);
    }



}
