package dev.joaorooliveira.help_desk.domain.tecnico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TecnicoRepository extends JpaRepository<Tecnico,Integer>, JpaSpecificationExecutor<Tecnico> {
}
