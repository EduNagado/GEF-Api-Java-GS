package com.api_gs.gef.dto;



public record DadosListandoPaciente(
    String nome,
    Integer idade,
    String endereco,
    DadosDetalhadoAbrigo abrigo,
    DadosDetalhandoPulseira pulseira
) {
    public DadosListandoPaciente(com.api_gs.gef.model.Paciente paciente) {
        this(
            paciente.getNome(),
            paciente.getIdade(),
            paciente.getEndereco(),
            new DadosDetalhadoAbrigo(paciente.getAbrigo()),
            new DadosDetalhandoPulseira(paciente.getPulseira())
        );
    }
}