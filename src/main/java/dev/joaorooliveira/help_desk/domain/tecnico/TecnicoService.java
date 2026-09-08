package dev.joaorooliveira.help_desk.domain.tecnico;

import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Transactional
    public TecnicoResponseDTO salvarTecnico(TecnicoRequestDTO tecnicoRequestDTO) {
        Tecnico tecnico = tecnicoRepository.save(tecnicoRequestDTO.toEntity());
        return TecnicoResponseDTO.fromEntity(tecnico);
    }
}
