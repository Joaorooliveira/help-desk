package dev.joaorooliveira.help_desk.domain.chamado;

import org.springframework.stereotype.Service;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }


}
