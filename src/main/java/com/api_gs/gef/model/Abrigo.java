package com.api_gs.gef.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abrigo {
    private long abrigoId;
    private String nome;
    private List<User> usuarios= new ArrayList<>();
}
