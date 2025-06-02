package com.api_gs.gef.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api_gs.gef.dto.FuncionarioDTO;
import com.api_gs.gef.model.Abrigo;
import com.api_gs.gef.model.Funcionario;
import com.api_gs.gef.repository.AbrigoRepository;
import com.api_gs.gef.repository.FuncionarioRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario create(@RequestBody @Valid FuncionarioDTO dto) {

        // Buscar abrigo pelo nome
        Abrigo abrigo = abrigoRepository.findByNome(dto.nomeAbrigo())
        .orElseThrow(() -> new IllegalArgumentException("Abrigo não encontrado"));

        // Criar funcionário
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setPassword(dto.password()); // → depois, criptografe com BCrypt
        funcionario.setCargo(dto.cargo());
        funcionario.setAbrigo(abrigo);

        return funcionarioRepository.save(funcionario);
    }
}
