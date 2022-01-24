package io.github.gabrielhgcamargo.controller;

import io.github.gabrielhgcamargo.model.AlunoModel;
import io.github.gabrielhgcamargo.repository.AlunoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/student")
public class AlunoController {

    private AlunoRepository repository;

    public AlunoController(AlunoRepository repository) { this.repository = repository; }

    @GetMapping("{id}")
    public AlunoModel getAlunoById(@PathVariable Integer id) {
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));
    }


    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoModel save(@RequestBody @Valid AlunoModel aluno){
        return repository.save(aluno);

    }

    //DELETAR
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        repository.findById(id)
                .map( aluno -> {
                    repository.delete(aluno);
                    return aluno;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));

    }

    //ATUALIZAR
    @PutMapping("{id}")
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid AlunoModel aluno){
        repository
                .findById(id)
                .map(alunoExistente -> {
                    aluno.setId(alunoExistente.getId());
                    repository.save(aluno);
                    return alunoExistente;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));
    }

    //BUSCA POR PROPRIEDADES (ID, E Nome)
    @GetMapping
    public List<AlunoModel> find( AlunoModel filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);

        return repository.findAll(example);
    }

}


