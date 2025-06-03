package com.api_gs.gef.controller;


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api_gs.gef.dto.PulseiraDTO;
import com.api_gs.gef.model.Pulseira;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra uma nova pulseira com sensores")
    public Pulseira create(@RequestBody @Valid PulseiraDTO dto) {
        log.info("Criando pulseira");
        return pulseiraService.criarPulseira(dto.bpm());
    }

    @GetMapping
    @Operation(summary = "Lista pulseiras com paginação")
    public Page<Pulseira> listar(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        log.info("Listando pulseiras");
        return pulseiraRepository.findAll(pageable);
    }
}
