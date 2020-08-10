package com.udemy.smartponto.api.repositories;

import com.udemy.smartponto.api.entities.Empresa;
import com.udemy.smartponto.api.entities.Funcionario;
import com.udemy.smartponto.api.enums.PerfilEnum;
import com.udemy.smartponto.api.utils.PasswordUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private static final String EMAIL = "email@teste.com";
    private static final String CPF = "65165144887";

    @BeforeEach
    public void setUp() throws Exception{
        Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
        this.funcionarioRepository.save(obterDadosFuncionario(empresa));
    }

    @AfterEach
    public final void tearDown(){
        this.empresaRepository.deleteAll();
    }

    @Test
    public final void testBuscarFuncionarioPorEmail(){

        Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);
        Assertions.assertEquals(CPF, funcionario.getCpf());

    }

    @Test
    public final void testBuscarFuncionarioPorCpf(){

        Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);
        Assertions.assertEquals(CPF, funcionario.getCpf());

    }

    @Test
    public final void testBuscarFuncionarioPorEmailECpf(){

        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF,EMAIL);
        Assertions.assertNotNull(funcionario);

    }

    @Test
    public final void testBuscarFuncionarioPorEmailECpfInvalido(){

        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("112233445566",EMAIL);
        Assertions.assertNotNull(funcionario);

    }


    private Empresa obterDadosEmpresa(){
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("empresa de exmplo");
        empresa.setCnpj("13607317000132");
        return empresa;
    }

    private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException{

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Testenildo");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.gerarBCrypt("112233"));
        funcionario.setCpf(CPF);
        funcionario.setEmail(EMAIL);
        funcionario.setEmpresa(empresa);
        return funcionario;
    }


}
