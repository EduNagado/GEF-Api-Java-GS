package com.api_gs.gef.dto;

import com.api_gs.gef.model.Abrigo;
import com.api_gs.gef.model.Pulseira;

public record  DadosListandoPaciente (String nome, Integer idade,String endereco, Abrigo abrigo, Pulseira PulseiraId ){
}
