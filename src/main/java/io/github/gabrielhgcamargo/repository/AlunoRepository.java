package io.github.gabrielhgcamargo.repository;

import io.github.gabrielhgcamargo.rest.dto.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {
}
