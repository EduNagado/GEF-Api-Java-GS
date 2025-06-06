package com.api_gs.gef.dto;


import com.api_gs.gef.model.Pulseira;


public record DadosDetalhandoPulseira(NfcDTO nfc, BatimentoCardiacoDTO batimento) {
    public DadosDetalhandoPulseira(Pulseira pulseira) {
        this(
            new NfcDTO(pulseira.getNfc()),
            new BatimentoCardiacoDTO(pulseira.getBatimento())
        );
    }
}
