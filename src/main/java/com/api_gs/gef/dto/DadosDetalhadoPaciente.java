package com.api_gs.gef.dto;

import com.api_gs.gef.model.Paciente;

public record DadosDetalhadoPaciente(String nome, Integer idade, String endereco) {
    public DadosDetalhadoPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getIdade(), paciente.getEndereco());
    }
}
