package com.udemy.smartponto.api;

import com.udemy.smartponto.api.entities.Empresa;
import com.udemy.smartponto.api.repositories.EmpresaRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
@ActiveProfiles("test")
class SmartpontoApplicationTests {

    @Autowired
    EmpresaRepository empresaRepository;

    private static  final String CNPJ = "13607317000132";

    @BeforeEach
    public void setUp() throws Exception{
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("empresa de exmplo");
        empresa.setCnpj(CNPJ);
        this.empresaRepository.save(empresa);
    }

    @AfterEach
    public final void tearDown(){
        this.empresaRepository.deleteAll();
    }


    @Test
    public void testBuscarPorCnpj(){

        Empresa empresa = this.empresaRepository.findByCnpj(CNPJ);
        Assertions.assertEquals(empresa.getCnpj(), CNPJ);

    }


}
