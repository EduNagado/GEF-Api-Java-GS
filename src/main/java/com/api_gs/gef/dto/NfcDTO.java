package com.api_gs.gef.dto;

import com.api_gs.gef.model.NFC;

public record NfcDTO(Long idNFC) {
    public NfcDTO(NFC nfc) {
        this(nfc.getIdNFC());
    }
}
