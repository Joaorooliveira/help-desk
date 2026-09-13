package dev.joaorooliveira.help_desk.domain.relatorio;

import dev.joaorooliveira.help_desk.projection.ChamadoPorPrioridadeProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioChamadoService {
    private final RelatorioChamadoRepository relatorioChamadoRepository;

    public RelatorioChamadoService(RelatorioChamadoRepository relatorioChamadoRepository) {
        this.relatorioChamadoRepository = relatorioChamadoRepository;
    }

    public List<ChamadoPorPrioridadeProjection> buscarChamadoPorPrioridade(){
        return relatorioChamadoRepository.chamadoPorPrioridade();
    }
}
git