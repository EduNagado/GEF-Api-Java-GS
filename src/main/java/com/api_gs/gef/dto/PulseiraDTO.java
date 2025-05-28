package com.api_gs.gef.dto;

public record PulseiraDTO(
    Long id,
    String idNFC,
    Double latitude,
    Double longitude,
    Integer bpm,
    String timestamp){
    
}
