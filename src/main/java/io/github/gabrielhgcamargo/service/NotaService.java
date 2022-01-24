package io.github.gabrielhgcamargo.service;

import io.github.gabrielhgcamargo.model.NotasModel;
import io.github.gabrielhgcamargo.rest.dto.NotaDTO;

import java.util.Optional;

public interface NotaService {

    NotasModel salvar(NotaDTO dto);

    Optional<NotasModel> obterNotaAlunoCompleto(Integer id);
}
