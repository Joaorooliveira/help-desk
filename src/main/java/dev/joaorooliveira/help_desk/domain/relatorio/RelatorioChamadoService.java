package dev.joaorooliveira.help_desk.domain.relatorio;

import dev.joaorooliveira.help_desk.projection.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioChamadoService {
    private final RelatorioChamadoRepository relatorioChamadoRepository;

    public RelatorioChamadoService(RelatorioChamadoRepository relatorioChamadoRepository) {
        this.relatorioChamadoRepository = relatorioChamadoRepository;
    }

    public List<ChamadoPorPrioridadeProjection> buscarChamadosPorPrioridade(){
        return relatorioChamadoRepository.chamadoPorPrioridade();
    }

    public List<ChamadoPorStatusProjection> buscarChamadosPorStatus(){
        return relatorioChamadoRepository.chamadoPorStatus();
    }

    public List<ChamadoPorCategoriaProjection> buscarChamadosPorCategoria(){
        return relatorioChamadoRepository.chamadoPorCategoria();
    }

    public List<ChamadoPorTecnicoProjection> buscarChamadosPorTecnico(){
        return relatorioChamadoRepository.chamadoPorTecnico();
    }

    public List<ChamadoPorSetorProjection> buscarChamadosPorSetor(){
        return relatorioChamadoRepository.chamadoPorSetor();
    }
}