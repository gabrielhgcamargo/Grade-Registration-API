package io.github.gabrielhgcamargo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoNotasDTO {

    private Integer codigo;
    private Integer id_aluno;
    private String nome;
    private String dataNascimento;
    private Float nota1;
    private Float nota2;
    private String status;
}
