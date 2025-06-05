package com.api_gs.gef.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api_gs.gef.dto.DetalhesPulseiraDTO;
import com.api_gs.gef.dto.PulseiraDTO;
import com.api_gs.gef.model.Paciente;
import com.api_gs.gef.model.Pulseira;
import com.api_gs.gef.repository.PacienteRepository;
import com.api_gs.gef.repository.PulseiraRepository;
import com.api_gs.gef.service.PulseiraService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pulseiras")
@Slf4j
public class PulseiraController {

    @Autowired
    private PulseiraRepository pulseiraRepository;

    @Autowired
    private PulseiraService pulseiraService;
    
    @Autowired  
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Operation(summary = "Cadastra uma nova pulseira ")
    public ResponseEntity<Pulseira> create(@RequestBody @Valid PulseiraDTO dto) {
        log.info("Criando pulseira");

        Pulseira pulseira = pulseiraService.criarPulseira(dto.bpm());

        return ResponseEntity.status(HttpStatus.CREATED).body(pulseira);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista informações completas pela ID da pulseira")
    public ResponseEntity<DetalhesPulseiraDTO> listarPorPulseiraId(@PathVariable Long id) {
        log.info("Buscando informações da pulseira com ID: {}", id);

        Pulseira pulseira = pulseiraRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pulseira não encontrada"));

        Paciente paciente = pacienteRepository.findByPulseira(pulseira)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado para a pulseira"));

        DetalhesPulseiraDTO dto = new DetalhesPulseiraDTO(
            pulseira.getPulseiraId(),
            paciente.getNome(),
            paciente.getIdade(),
            paciente.getEndereco(),
            paciente.getAbrigo().getNome(),
            pulseira.getNfc().getIdNFC(),
            pulseira.getBatimento().getBpm(),
            pulseira.getBatimento().getTimestamp()
        );

        return ResponseEntity.ok(dto);
    }
}
