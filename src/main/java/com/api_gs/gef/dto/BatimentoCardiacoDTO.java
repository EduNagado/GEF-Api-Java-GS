package com.api_gs.gef.dto;

import com.api_gs.gef.model.BatimentoCardiaco;

public record BatimentoCardiacoDTO(Integer bpm, String timestamp) {
    public BatimentoCardiacoDTO(BatimentoCardiaco batimento) {
        this(
            batimento.getBpm(),
            batimento.getTimestamp() != null ? batimento.getTimestamp().toString() : null
        );
    }
}