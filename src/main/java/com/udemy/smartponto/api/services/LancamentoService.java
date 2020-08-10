package com.udemy.smartponto.api.services;

import com.udemy.smartponto.api.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface LancamentoService {

    /**
     * Buscar pelo id do  funcionario paginado
     * @param funcionarioId
     * @param pageRequest
     * @return Page<Lancamento>
     */
    Page<Lancamento> buscarPorFuncionarioID(Long funcionarioId, PageRequest pageRequest);

    /**
     * Buscar pelo id do  funcionario
     * @param id
     * @return Optional<Lancamento>
     */
    Optional<Lancamento> buscarPorId(Long id);

    /**
     *  Salvar lancamento
     * @param lancamento
     * @return Lancamento
     */
    Lancamento persistir(Lancamento lancamento);


    /**
     * Remove lancamento
     * @param id
     */
    void remover(Long id);


}
