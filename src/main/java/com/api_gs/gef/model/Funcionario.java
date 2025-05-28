package com.api_gs.gef.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    private Long id;
    private String nome;
    private String cargo;
    private Abrigo abrigo;
}
