package dev.joaorooliveira.help_desk.infra.specification;

import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;
import dev.joaorooliveira.help_desk.domain.tecnico.dto.TecnicoFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.tecnico.enums.EspecialidadeTipo;
import org.springframework.data.jpa.domain.Specification;

public class TecnicoSpecification {

    public static Specification<Tecnico> comFiltros(TecnicoFiltroRequestDTO filtro){
        return Specification
                .where(nomeContem(filtro.nome()))
                .and(emailContem(filtro.email()))
                .and(ramalContem(filtro.ramal()))
                .and(especialidadeIgual(filtro.especialidade()));
    }

    private static Specification<Tecnico> nomeContem(String nome) {
        return (root, query, cb) -> {
            if(nome == null || nome.isBlank()){
                return null;
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    private static Specification<Tecnico> emailContem(String email) {
        return (root, query, cb) -> {
            if(email == null || email.isBlank()){
                return null;
            }
            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }

    private static Specification<Tecnico> ramalContem(String ramal) {
        return (root, query, cb) -> {
            if(ramal == null || ramal.isBlank()){
                return null;
            }
            return cb.like(cb.lower(root.get("ramal")), "%" + ramal.toLowerCase() + "%");
        };
    }

    private static Specification<Tecnico> especialidadeIgual(EspecialidadeTipo especialidade) {
        return (root, query, cb) -> {
            if(especialidade == null){
                return null;
            }
            return cb.equal(root.get("especialidade"), especialidade);
        };
    }






}
