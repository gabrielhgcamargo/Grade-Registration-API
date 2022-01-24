package io.github.gabrielhgcamargo.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDTO {

    private Integer aluno;
    private Integer codigo;
    private Float nota1;
    private Float nota2;
}
