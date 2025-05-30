package com.api_gs.gef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PulseiraDTO(



    @NotNull(message = "BPM não pode ser nulo")
    Integer bpm,

    @NotBlank
    String timestamp){
    
}
