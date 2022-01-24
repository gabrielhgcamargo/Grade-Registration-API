package io.github.gabrielhgcamargo.controller;

import io.github.gabrielhgcamargo.rest.dto.model.NotasModel;
import io.github.gabrielhgcamargo.rest.dto.InfoNotasDTO;
import io.github.gabrielhgcamargo.rest.dto.NotaDTO;
import io.github.gabrielhgcamargo.service.NotaService;
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
    public Integer save(@RequestBody @Valid NotaDTO dto){
        NotasModel notas = service.salvar(dto);
        return notas.getCodigo();
    }

    //Exibir todas as informações por ID
    @GetMapping("{id}")
    public InfoNotasDTO getById(@PathVariable Integer id){
    return service.obterNotaAlunoCompleto(id)
            .map(n -> converte(n))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Código de nota não encontrado!"));
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
