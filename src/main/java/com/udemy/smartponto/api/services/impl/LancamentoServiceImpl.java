package com.udemy.smartponto.api.services.impl;

import com.udemy.smartponto.api.entities.Lancamento;
import com.udemy.smartponto.api.repositories.LancamentoRepository;
import com.udemy.smartponto.api.services.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    public Page<Lancamento> buscarPorFuncionarioID(Long funcionarioId, PageRequest pageRequest) {

        log.info("Buscando lançamentos para o funcionario de id {}", funcionarioId);
        return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
    }

    @Override
    public Optional<Lancamento> buscarPorId(Long id) {
        log.info("Buscando lançamentos para o funcionario de id {}", id);
        return this.lancamentoRepository.findById(id);
    }

    @Override
    public Lancamento persistir(Lancamento lancamento) {
        log.info("Persistindo o lançamento {}", lancamento);
        return this.lancamentoRepository.save(lancamento);
    }

    @Override
    public void remover(Long id) {
        log.info("removando o lancamento pelo id {}", id);
        this.lancamentoRepository.deleteById(id);
    }
}
