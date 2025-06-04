package com.api_gs.gef.dto;

import com.api_gs.gef.model.Cargo;

public record DadosAtualizarFunc(
    String nome,
    String email,
    String password,
    Cargo cargo) {

}