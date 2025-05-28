package com.api_gs.gef.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pulseira {
    private Long PulseiraId;
    private NFC nfc;
    private RFID rfid;
    private BatimentoCardiaco batimento;
}
