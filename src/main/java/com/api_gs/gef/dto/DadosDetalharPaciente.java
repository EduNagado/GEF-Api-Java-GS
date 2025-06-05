package com.api_gs.gef.dto;

import com.api_gs.gef.model.Paciente;

public record DadosDetalharPaciente(String nome, Integer idade, String endereco) {
    public DadosDetalharPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getIdade(), paciente.getEndereco());
    }
}
