package io.github.gabrielhgcamargo.controller;

import io.github.gabrielhgcamargo.model.NotasModel;
import io.github.gabrielhgcamargo.rest.dto.InfoNotasDTO;
import io.github.gabrielhgcamargo.rest.dto.NotaDTO;
import io.github.gabrielhgcamargo.service.NotaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/api/grades")
public class NotasController {

    private NotaService service;

    public NotasController(NotaService service) {
        this.service = service;
    }


    //SALVAR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Registering the grade from the student")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Student grade registred"),
            @ApiResponse(code = 400, message = "Validation Error")
    }
    )
    public Integer save(@RequestBody @Valid @ApiParam("{\n" +
            "  \"aluno\": 0,\n" +
            "  \"codigo\": 0,\n" +
            "  \"nota1\": 0,\n" +
            "  \"nota2\": 0\n" +
            "}") NotaDTO dto){
        NotasModel notas = service.salvar(dto);
        return notas.getCodigo();
    }

    //Exibir todas as informações por ID
    @GetMapping("{id}")
    @ApiOperation("Get the Grade and Student details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Student grade found"),
            @ApiResponse(code = 404, message = "Student grade not found with this ID"),
            @ApiResponse(code = 400, message = "Validation Error")
    }
    )
    public InfoNotasDTO getById(@PathVariable @ApiParam("Grade ID") Integer id){
    return service.obterNotaAlunoCompleto(id)
            .map(n -> converte(n))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id Grade not found!"));
    }

    private InfoNotasDTO converte(NotasModel notas){
        return InfoNotasDTO
                .builder()
                .codigo(notas.getCodigo())
                .id_aluno(notas.getAluno().getId())
                .nome(notas.getAluno().getNome())
                .dataNascimento(notas.getAluno().getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nota1(notas.getNota1())
                .nota2(notas.getNota2())
                .status(notas.getStatus().name())
                .build();
    }

}
