package com.api_gs.gef.dto;

import jakarta.validation.constraints.NotBlank;

public record AbrigoDTO(

    @NotBlank
    String nome,
    
    @NotBlank
    String endereco) {
    
}
