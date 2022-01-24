package io.github.gabrielhgcamargo.service.impl;

import io.github.gabrielhgcamargo.enums.StatusNotas;
import io.github.gabrielhgcamargo.exception.RegraNegocioException;
import io.github.gabrielhgcamargo.model.AlunoModel;
import io.github.gabrielhgcamargo.model.NotasModel;
import io.github.gabrielhgcamargo.repository.AlunoRepository;
import io.github.gabrielhgcamargo.repository.NotasRepository;
import io.github.gabrielhgcamargo.rest.dto.NotaDTO;
import io.github.gabrielhgcamargo.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final AlunoRepository repository;
    private final NotasRepository notasRepository;

    @Override
    @Transactional
    public NotasModel salvar(NotaDTO dto) {

        Integer idAluno = dto.getAluno();
        AlunoModel aluno = repository
                .findById(idAluno)
                .orElseThrow(() -> new RegraNegocioException("Código de aluno inválido"));

        NotasModel notas = new NotasModel();
        notas.setCodigo(dto.getCodigo());
        notas.setNota1(dto.getNota1());
        notas.setNota2(dto.getNota2());
        notas.setAluno(aluno);
        if ((dto.getNota1() + dto.getNota2()/2) >= 7)
        {
            notas.setStatus(StatusNotas.APROVADO);
        }
        else{
            notas.setStatus(StatusNotas.REPROVADO);
        }

        notasRepository.save(notas);

        return notas;
    }

    @Override
    public Optional<NotasModel> obterNotaAlunoCompleto(Integer id) {
        return notasRepository.findByIdFetchAluno(id);
    }


}



