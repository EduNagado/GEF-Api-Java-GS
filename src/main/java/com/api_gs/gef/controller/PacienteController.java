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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api_gs.gef.dto.PacienteDTO;
import com.api_gs.gef.model.Abrigo;
import com.api_gs.gef.model.Paciente;
import com.api_gs.gef.model.Pulseira;
import com.api_gs.gef.repository.AbrigoRepository;
import com.api_gs.gef.repository.UserRepository;
import com.api_gs.gef.service.PulseiraService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired  
    private UserRepository userRepository;

    @Autowired
    private PulseiraService pulseiraService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente create(@RequestBody @Valid PacienteDTO dto) {

        Abrigo abrigo = abrigoRepository.findByNome(dto.nomeAbrigo())
        .orElseThrow(() -> new IllegalArgumentException("Abrigo não encontrado"));

      
        Pulseira pulseira = pulseiraService.criarPulseira(80); 


        Paciente pacientes = new Paciente();
        pacientes.setNome(dto.nome());
        pacientes.setIdade(dto.idade());
        pacientes.setEndereco(dto.endereco());
        pacientes.setAbrigo(abrigo);
        pacientes.setPulseira(pulseira);

        // 4. Salvar e retornar
        return userRepository.save(pacientes);
    }

    @GetMapping
    @Operation(summary = "Lista os pacientes com paginação e filtros")
    public Page<Paciente> listar(
            @ParameterObject @PageableDefault(size = 10, sort = "nome") Pageable pageable,
            @RequestParam(required = false) String nome
    ) {
        log.info("Listando pacientes. Filtro nome = {}", nome);

        if (nome != null && !nome.isBlank()) {
            return userRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }

        return userRepository.findAll(pageable);
    }
}