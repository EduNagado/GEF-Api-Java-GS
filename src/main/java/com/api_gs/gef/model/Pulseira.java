package com.api_gs.gef.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pulseira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PulseiraId;

    @OneToOne
    private NFC nfc;
    @OneToOne
    private RFID rfid;
    
    @OneToOne
    private BatimentoCardiaco batimento;
}
