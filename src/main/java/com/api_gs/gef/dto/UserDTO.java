package com.api_gs.gef.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(

    @NotBlank
    String nome,


    int idade,

    @NotBlank
    String endereco,


    Long abrigoId,


    Long PulseiraId){
    
}
