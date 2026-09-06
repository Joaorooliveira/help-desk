package dev.joaorooliveira.help_desk.domain.tecnico;

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
@Table(name = "tecnico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 150)
    private String nome;

    @Column(nullable = false,length = 150,unique = true)
    private String email;

    @Column(nullable = false,length = 10)
    private String ramal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 30)
    private EspecialidadeTipo especialidade;

    @Column(name = "criado_em",nullable = false,updatable = false)
    @CreatedDate
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em",nullable = false)
    @LastModifiedDate
    private LocalDateTime atualizadoEm;
}

