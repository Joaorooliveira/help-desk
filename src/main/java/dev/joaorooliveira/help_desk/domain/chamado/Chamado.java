package dev.joaorooliveira.help_desk.domain.chamado;

import dev.joaorooliveira.help_desk.domain.chamado.enums.CategoriaTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.PrioridadeTipo;
import dev.joaorooliveira.help_desk.domain.chamado.enums.StatusTipo;
import dev.joaorooliveira.help_desk.domain.funcionario.Funcionario;
import dev.joaorooliveira.help_desk.domain.tecnico.Tecnico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "chamado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
    private String titulo;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private PrioridadeTipo prioridade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 30)
    private CategoriaTipo categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private StatusTipo status = StatusTipo.ABERTO;

    @Column(columnDefinition = "TEXT")
    private String solucao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @Column(nullable = false,name = "data_abertura",updatable = false)
    @CreatedDate
    private LocalDateTime dataAbertura;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "criado_em",nullable = false,updatable = false)
    @CreatedDate
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em",nullable = false)
    @LastModifiedDate
    private LocalDateTime atualizadoEm;
}
