package com.udemy.smartponto.api.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {

    private static final String SENHA = "123456";
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testSenhaNula(){
        Assertions.assertNull(PasswordUtils.gerarBCrypt(null));
    }

    @Test
    public  void testarGerarHashSenha() throws Exception{

        String hash = PasswordUtils.gerarBCrypt(SENHA);
        Assertions.assertTrue(bCryptPasswordEncoder.matches(SENHA,hash));

    }
}
