package com.udemy.smartponto.api.repositories;


import com.udemy.smartponto.api.entities.Empresa;
import com.udemy.smartponto.api.entities.Funcionario;
import com.udemy.smartponto.api.entities.Lancamento;
import com.udemy.smartponto.api.enums.PerfilEnum;
import com.udemy.smartponto.api.enums.TipoEnum;
import com.udemy.smartponto.api.utils.PasswordUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {


    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private long funcionarioId;

    @BeforeEach
    public void setUp() throws  Exception{
        Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());

        Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
        this.funcionarioId = funcionario.getId();

        this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
        this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
    }

    @AfterEach
    public void tearDown(){

        this.empresaRepository.deleteAll();

    }

    @Test
    public void testBuscarLancamentosPorfuncionarioId(){

        List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
        Assertions.assertEquals(2, lancamentos.size());

    }

    @Test
    public void testeBuscarLancamentosPorFuncionarioIdPaginado(){
        PageRequest page = PageRequest.of(0, 10);
        Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);

        Assertions.assertEquals(2,lancamentos.getTotalElements());

    }

    private Empresa obterDadosEmpresa(){
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("empresa de exemplo");
        empresa.setCnpj("13607317000132");
        return empresa;
    }

    private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Testenildo");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.gerarBCrypt("112233"));
        funcionario.setCpf("123123456");
        funcionario.setEmail("teste@gmail.com");
        funcionario.setEmpresa(empresa);
        return funcionario;
    }

    private Lancamento obterDadosLancamentos(Funcionario funcionario){
        Lancamento lancamento = new Lancamento();
        lancamento.setData(new Date());
        lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
        lancamento.setFuncionario(funcionario);
        return lancamento;
    }

}
