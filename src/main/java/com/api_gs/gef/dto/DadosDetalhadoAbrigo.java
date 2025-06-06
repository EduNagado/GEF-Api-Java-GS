package com.api_gs.gef.dto;

import com.api_gs.gef.model.Abrigo;

public record  DadosDetalhadoAbrigo(String nome) {
    public DadosDetalhadoAbrigo(Abrigo abrigo) {
        this(abrigo.getNome());
    }
}
