package com.api_gs.gef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_gs.gef.model.BatimentoCardiaco;
import com.api_gs.gef.model.NFC;
import com.api_gs.gef.model.Pulseira;
import com.api_gs.gef.repository.PulseiraRepository;

@Service
public class PulseiraService {

    @Autowired
    private PulseiraRepository pulseiraRepository;

    public Pulseira criarPulseira(Integer bpm) {
        Pulseira pulseira = new Pulseira();

        NFC nfc = new NFC(); 
        pulseira.setNfc(nfc);

        BatimentoCardiaco batimento = new BatimentoCardiaco();
        batimento.setBpm(bpm);

        pulseira.setNfc(nfc);
        pulseira.setBatimento(batimento);

        return pulseiraRepository.save(pulseira);
    }
}