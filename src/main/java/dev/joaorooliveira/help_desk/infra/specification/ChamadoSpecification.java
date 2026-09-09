package dev.joaorooliveira.help_desk.infra.specification;

import dev.joaorooliveira.help_desk.domain.chamado.Chamado;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoFuncionarioFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.chamado.dto.ChamadoTecnicoFiltroRequestDTO;
import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChamadoSpecification {

    public static Specification<Chamado> comFiltrosFuncionario(
            ChamadoFuncionarioFiltroRequestDTO filtro) {

        return Specification
                .where(tituloContem(filtro.titulo()))
                .and(descricaoContem(filtro.descricao()))
                .and(prioridadeIgual(filtro.prioridade()))
                .and(categoriaIgual(filtro.categoria()))
                .and(statusIgual(filtro.status()))
                .and(dataAberturaIgual(filtro.dataAbertura()))
                .and(dataConclusaoIgual(filtro.dataConclusao()));
    }


    public static Specification<Chamado> comFiltrosTecnico(
            ChamadoTecnicoFiltroRequestDTO filtro) {

        return Specification
                .where(tituloContem(filtro.titulo()))
                .and(descricaoContem(filtro.descricao()))
                .and(prioridadeIgual(filtro.prioridade()))
                .and(categoriaIgual(filtro.categoria()))
                .and(statusIgual(filtro.status()))
                .and(dataAberturaIgual(filtro.dataAbertura()))
                .and(dataConclusaoIgual(filtro.dataConclusao()))
                .and(nomeTecnicoContem(filtro.nomeTecnico()))
                .and(nomeFuncionarioContem(filtro.nomeFuncionario()));
    }


    private static Specification<Chamado> nomeTecnicoContem(String nomeTecnico) {

        return (root, query, cb) -> {

            if (nomeTecnico == null || nomeTecnico.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(
                            root.join("tecnico", JoinType.LEFT)
                                    .get("nome")
                    ),
                    "%" + nomeTecnico.toLowerCase() + "%"
            );
        };
    }


    private static Specification<Chamado> nomeFuncionarioContem(String nomeFuncionario) {

        return (root, query, cb) -> {

            if (nomeFuncionario == null || nomeFuncionario.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(
                            root.join("funcionario", JoinType.LEFT)
                                    .get("nome")
                    ),
                    "%" + nomeFuncionario.toLowerCase() + "%"
            );
        };
    }


    public static Specification<Chamado> tituloContem(String titulo) {

        return (root, query, cb) -> {

            if (titulo == null || titulo.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("titulo")),
                    "%" + titulo.toLowerCase() + "%"
            );
        };
    }


    public static Specification<Chamado> descricaoContem(String descricao) {

        return (root, query, cb) -> {

            if (descricao == null || descricao.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("descricao")),
                    "%" + descricao.toLowerCase() + "%"
            );
        };
    }


    public static Specification<Chamado> prioridadeIgual(
            PrioridadeTipo prioridade) {

        return (root, query, cb) -> {

            if (prioridade == null) {
                return null;
            }

            return cb.equal(
                    root.get("prioridade"),
                    prioridade
            );
        };
    }


    public static Specification<Chamado> categoriaIgual(
            CategoriaTipo categoria) {

        return (root, query, cb) -> {

            if (categoria == null) {
                return null;
            }

            return cb.equal(
                    root.get("categoria"),
                    categoria
            );
        };
    }


    public static Specification<Chamado> statusIgual(
            StatusTipo status) {

        return (root, query, cb) -> {

            if (status == null) {
                return null;
            }

            return cb.equal(
                    root.get("status"),
                    status
            );
        };
    }


    public static Specification<Chamado> dataAberturaIgual(
            LocalDate data) {

        return (root, query, cb) -> {

            if (data == null) {
                return null;
            }

            LocalDateTime inicio = data.atStartOfDay();

            LocalDateTime fim = data
                    .plusDays(1)
                    .atStartOfDay();

            return cb.and(
                    cb.greaterThanOrEqualTo(
                            root.get("dataAbertura"),
                            inicio
                    ),
                    cb.lessThan(
                            root.get("dataAbertura"),
                            fim
                    )
            );
        };
    }


    public static Specification<Chamado> dataConclusaoIgual(
            LocalDate data) {

        return (root, query, cb) -> {

            if (data == null) {
                return null;
            }

            LocalDateTime inicio = data.atStartOfDay();

            LocalDateTime fim = data
                    .plusDays(1)
                    .atStartOfDay();

            return cb.and(
                    cb.greaterThanOrEqualTo(
                            root.get("dataConclusao"),
                            inicio
                    ),
                    cb.lessThan(
                            root.get("dataConclusao"),
                            fim
                    )
            );
        };
    }
}