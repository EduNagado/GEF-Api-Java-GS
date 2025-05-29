package com.api_gs.gef.model;


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
public class RFID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRFID;
    private double latitude;
    private double longitude;
}
