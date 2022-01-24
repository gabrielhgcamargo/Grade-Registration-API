package io.github.gabrielhgcamargo.repository;

import io.github.gabrielhgcamargo.rest.dto.model.NotasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NotasRepository extends JpaRepository<NotasModel, Integer> {


    @Query("select n from NotasModel n left join fetch n.aluno where n.id = :id ")
    Optional<NotasModel> findByIdFetchAluno(@Param("id") Integer id);


}

