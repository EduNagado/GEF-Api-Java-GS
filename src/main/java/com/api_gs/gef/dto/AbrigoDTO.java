package com.api_gs.gef.dto;

import jakarta.validation.constraints.NotBlank;

public record AbrigoDTO(

    @NotBlank(message = "campo obrigatório")
    String nome,
    
    @NotBlank(message = "campo obrigatório")
    String endereco) {
    
}
