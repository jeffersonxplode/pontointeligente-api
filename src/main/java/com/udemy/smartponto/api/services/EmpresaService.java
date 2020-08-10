package com.udemy.smartponto.api.services;

import com.udemy.smartponto.api.entities.Empresa;

import java.util.Optional;

public interface EmpresaService {

    /**
     * Retorna uma empresa dado um CNPJ
     * @param cnpj
     * @return Optional<Empresa>
     */
    Optional<Empresa> buscarPorCnpj (String cnpj);


    /**
     * Cadastra uma nova empresa no BD
     * @param empresa
     * @return empresa
     */
    Empresa persistir(Empresa empresa);


}
