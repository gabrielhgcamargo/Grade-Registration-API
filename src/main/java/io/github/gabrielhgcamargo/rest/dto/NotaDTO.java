package io.github.gabrielhgcamargo.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO {

    @NotNull(message = "As notas precisam pertencer a algum aluno, insira o respectivo ID")
    private Integer aluno;

    @NotNull(message = "Defina um código para acessar a nota e status do aluno posteriormente")
    private Integer codigo;

    @NotNull(message = "Para definir se foi aprovado ou não, a inserção das duas notas é obrigatória!")
    private Float nota1;

    @NotNull(message = "Para definir se foi aprovado ou não, a inserção das duas notas é obrigatória!")
    private Float nota2;
}
