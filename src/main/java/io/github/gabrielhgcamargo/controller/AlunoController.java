package io.github.gabrielhgcamargo.controller;

import io.github.gabrielhgcamargo.model.AlunoModel;
import io.github.gabrielhgcamargo.repository.AlunoRepository;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/student")
@Api("Students API")
public class AlunoController {

    private AlunoRepository repository;

    public AlunoController(AlunoRepository repository) { this.repository = repository; }

    @GetMapping("{id}")
    @ApiOperation("Get student details")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Student found"),
        @ApiResponse(code = 404, message = "Student not found with this ID"),
        @ApiResponse(code = 400, message = "Validation Error")
    }
    )
    public AlunoModel getAlunoById(@PathVariable @ApiParam("Student ID") Integer id) {
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Invalid Student ID"));
    }


    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Registering a new student")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Student created"),
            @ApiResponse(code = 400, message = "Validation Error")
    }
    )
    public AlunoModel save(@RequestBody @Valid @ApiParam("All student fields completed") AlunoModel aluno){
        return repository.save(aluno);

    }

    //DELETAR
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete some student")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Student deleted"),
            @ApiResponse(code = 404, message = "Student not found with this ID")
    }
    )
    public void delete( @PathVariable @ApiParam("Id from the student to delete") Integer id ){
        repository.findById(id)
                .map( aluno -> {
                    repository.delete(aluno);
                    return aluno;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Invalid Student ID"));

    }

    //ATUALIZAR
    @PutMapping("{id}")
    @ApiOperation("Update some student")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Student updated"),
            @ApiResponse(code = 404, message = "Student not found with this ID")
    }
    )
    public void update(@PathVariable @ApiParam("Id from the student to update") Integer id,
                       @RequestBody @Valid @ApiParam("All student fields completed") AlunoModel aluno){
        repository
                .findById(id)
                .map(alunoExistente -> {
                    aluno.setId(alunoExistente.getId());
                    repository.save(aluno);
                    return alunoExistente;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Invalid Student ID"));
    }

    //BUSCA POR PROPRIEDADES (ID, E Nome)
    @GetMapping
    @ApiOperation("Find student with Params")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Student found with your params"),
            @ApiResponse(code = 400, message = "Bad Request, check again your params!")
    }
    )
    public List<AlunoModel> find( AlunoModel filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);

        return repository.findAll(example);
    }

}


