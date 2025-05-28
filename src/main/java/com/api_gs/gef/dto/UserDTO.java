package com.api_gs.gef.dto;

public record UserDTO(
    Long id,
    String nome,
    int idade,
    String endereco,
    Long abrigoId,
    Long PulseiraId){
    
}
