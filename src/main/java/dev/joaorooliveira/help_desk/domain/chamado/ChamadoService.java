package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.domain.chamado.dto.*;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;
import dev.joaorooliveira.help_desk.domain.chamado.validacoes.assumir.ValidadorAssumirChamado;
import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.FuncionarioRepository;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;
import dev.joaorooliveira.help_desk.domain.tecnico.TecnicoRepository;
import dev.joaorooliveira.help_desk.infra.exception.EntidadeNaoEncontradaException;
import dev.joaorooliveira.help_desk.infra.specification.ChamadoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final TecnicoRepository tecnicoRepository;
    private final List<ValidadorAssumirChamado> validadores;

    public ChamadoService(ChamadoRepository chamadoRepository, FuncionarioRepository funcionarioRepository, TecnicoRepository tecnicoRepository, List<ValidadorAssumirChamado> validadores){
        this.chamadoRepository = chamadoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.validadores = validadores;
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
    public Page<ChamadoFuncionarioResponseDTO> buscarChamadosFuncionario(Pageable pageable,
                                                                         ChamadoFuncionarioFiltroRequestDTO filtro) {
        return chamadoRepository.findAll(ChamadoSpecification.comFiltrosFuncionario(filtro), pageable)
                .map(ChamadoFuncionarioResponseDTO::fromEntity);
    }

    // Listar chamados para Tecnico com filtros
    public Page<ChamadoTecnicoResponseDTO> buscarChamadosTecnico(Pageable pageable,
                                                                 ChamadoTecnicoFiltroRequestDTO filtro) {
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

    @Transactional
    public ChamadoTecnicoResponseDTO assumirChamado(Long idTecnico,Long idChamado) {
        Tecnico tecnico = buscarTecnicoPorId(idTecnico);
        Chamado chamado = buscarChamadoPorId(idChamado);
        validadores.forEach(v ->v.validar(chamado,idTecnico));
        chamado.setTecnico(tecnico);
        chamado.setStatus(StatusTipo.EM_ANDAMENTO);
        return ChamadoTecnicoResponseDTO.fromEntity(chamado);
    }

    //Criar Validacao do chamado
    @Transactional
    public ChamadoTecnicoResponseDTO concluirChamado(Long idChamado,ConcluirChamadoDTO dto){
        Tecnico tecnico = buscarTecnicoPorId(dto.idTecnico());
        //Falta Validar o Tecnico
        Chamado chamado = buscarChamadoPorId(idChamado);
        chamado.setStatus(StatusTipo.CONCLUIDO);
        chamado.setSolucao(dto.solucao());
        chamado.setDataConclusao(LocalDateTime.now());
        return ChamadoTecnicoResponseDTO.fromEntity(chamado);
    }


    private Chamado buscarChamadoPorId(Long id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Chamado não encontrado com o ID: " + id));
    }

    private Tecnico buscarTecnicoPorId(Long id){
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tecnico não encontrado com o ID: " + id));
    }
}
