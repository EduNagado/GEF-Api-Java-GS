package com.api_gs.gef.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BatimentoCardiaco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBatimentoCardiaco;
    private int bpm;
    private LocalDateTime timestamp;
}
