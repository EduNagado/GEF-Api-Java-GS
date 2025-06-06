package com.api_gs.gef.dto;

import com.api_gs.gef.model.Cargo;
import com.api_gs.gef.model.Funcionario;

public record DadosDetalhadoFunc(String nome, String email, String password, Cargo cargo) {
    public DadosDetalhadoFunc(Funcionario funcionario) {
        this(funcionario.getNome(), funcionario.getEmail(), funcionario.getPassword(), funcionario.getCargo());
    }
}