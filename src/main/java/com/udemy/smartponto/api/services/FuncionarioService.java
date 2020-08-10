package com.udemy.smartponto.api.services;

import com.udemy.smartponto.api.entities.Funcionario;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FuncionarioService {

    /**
     * Persiste um funcionário na base de dados
     * @param funcionario
     * @return Funcionario
     */
    Funcionario persistir(Funcionario funcionario);

    /**
     * Buscar funcionário por Cpf
     * @param cpf
     * @return Optional<Funcionario>
     */

    Optional<Funcionario> buscarPorCpf(String cpf);

    /**
     * Buscar funcionário por email
     * @param email
     * @return Optional<Funcionario>
     */

    Optional<Funcionario> buscarPorEmail(String email);

    /**
     * Buscar funcionário por id
     * @param id
     * @return Optional<Funcionario>
     */

    Optional<Funcionario> buscarPorId(Long id);

}
