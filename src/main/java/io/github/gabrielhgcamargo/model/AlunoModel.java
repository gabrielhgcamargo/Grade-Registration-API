package io.github.gabrielhgcamargo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Table(name = "aluno")
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Inserir nome! Este campo não pode estar vazio.")
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno", fetch = FetchType.EAGER)
    private List<NotasModel> notas;

    public AlunoModel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
