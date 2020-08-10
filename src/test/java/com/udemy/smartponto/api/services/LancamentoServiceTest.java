package com.udemy.smartponto.api.services;

import com.udemy.smartponto.api.entities.Lancamento;
import com.udemy.smartponto.api.repositories.LancamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

    @MockBean
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @BeforeEach
    public void setUp() throws Exception {
        BDDMockito.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class))).willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
        BDDMockito.given(this.lancamentoRepository.findById(Mockito.anyLong())).willReturn(java.util.Optional.of(new Lancamento()));
        BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
    }

    @Test
    public void testeBuscarLancamentoPorFuncionarioId(){
        Page<Lancamento> lancamentoPage = this.lancamentoService.buscarPorFuncionarioID(1L, PageRequest.of(0, 10));
        Assertions.assertNotNull(lancamentoPage);
    }

    @Test
    public void testBuscarLancamentoPorId(){
        Optional<Lancamento> lancamento = this.lancamentoService.buscarPorId(1L);
        Assertions.assertTrue(lancamento.isPresent());
    }

    @Test
    public void testPersistirLancamento(){
        Lancamento lancamento = this.lancamentoService.persistir(new Lancamento());
        Assertions.assertNotNull(lancamento);
    }

}
