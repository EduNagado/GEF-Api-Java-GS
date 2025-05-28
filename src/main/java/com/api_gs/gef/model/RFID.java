package com.api_gs.gef.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RFID {
    private Long idRFID;
    private double latitude;
    private double longitude;
}
