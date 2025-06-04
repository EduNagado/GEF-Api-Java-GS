package com.api_gs.gef.dto;

import jakarta.validation.constraints.NotBlank;

public record PacienteDTO(

    @NotBlank(message = "campo obrigatório")
    String nome,

    int idade,

    @NotBlank(message = "campo obrigatório")
    String endereco,

    @NotBlank(message = "campo obrigatório")
    String nomeAbrigo
){
}
