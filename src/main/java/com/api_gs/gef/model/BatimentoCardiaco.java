package com.api_gs.gef.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatimentoCardiaco {
    private Long idBatimentoCardiaco;
    private int bpm;
    // private Date timestamp;
}
