package dev.joaorooliveira.help_desk.domain.relatorio;

import org.springframework.stereotype.Service;

@Service
public class RelatorioChamadoService {
    private final RelatorioChamadoRepository relatorioChamadoRepository;

    public RelatorioChamadoService(RelatorioChamadoRepository relatorioChamadoRepository) {
        this.relatorioChamadoRepository = relatorioChamadoRepository;
    }

}
