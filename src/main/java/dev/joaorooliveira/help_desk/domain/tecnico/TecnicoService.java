package dev.joaorooliveira.help_desk.domain.tecnico;

import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }


}
