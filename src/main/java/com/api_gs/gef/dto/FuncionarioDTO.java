package com.api_gs.gef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(

    @NotBlank
    String nome,

    @NotBlank
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
        message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula e um número"
    )
    String password,

    @NotBlank
    String cargo,

    @NotBlank
    Long abrigoId){
    
}
