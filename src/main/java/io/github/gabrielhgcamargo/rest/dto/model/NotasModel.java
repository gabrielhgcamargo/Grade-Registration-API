package io.github.gabrielhgcamargo.rest.dto.model;

import io.github.gabrielhgcamargo.enums.StatusNotas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotasModel {

    @Id
    @Column
    private Integer codigo;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private AlunoModel aluno;

    @Column
    private Float nota1;

    @Column
    private Float nota2;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusNotas status;


}
