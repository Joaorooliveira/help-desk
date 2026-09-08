package dev.joaorooliveira.help_desk.infra.specification;

import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.funcionario.dto.FuncionarioFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.funcionario.enums.SetorTipo;
import org.springframework.data.jpa.domain.Specification;

public class FuncionarioSpecification {

    public Specification<Funcionario> comFiltros(FuncionarioFiltroRequestDTO filtro) {
        return Specification
                .where(nomeContem(filtro.nome()))
                .and(emailContem(filtro.email()))
                .and(setorIgual(filtro.setor()))
                .and(ramalContem(filtro.ramal()));
    }

    private Specification<Funcionario> ramalContem(String ramal) {
        return (root, query, cb) -> {
            if (ramal == null || ramal.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(root.get("ramal")), "%" + ramal.toLowerCase() + "%");
        };
    }

    private Specification<Funcionario> setorIgual(SetorTipo setor) {
        return (root, query, cb) -> {
            if (setor == null) {
                return null;
            }
            return cb.equal(root.get("setor"), setor);
        };
    }

    private Specification<Funcionario> emailContem(String email) {
        return (root, query, cb) -> {
            if (email == null || email.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }

    private Specification<Funcionario> nomeContem(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }


}
