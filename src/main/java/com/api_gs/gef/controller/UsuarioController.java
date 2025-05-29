package com.api_gs.gef.controller;

import java.util.Optional;

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

import com.api_gs.gef.dto.UserDTO;
import com.api_gs.gef.model.Abrigo;
import com.api_gs.gef.model.Pulseira;
import com.api_gs.gef.model.User;
import com.api_gs.gef.repository.AbrigoRepository;
import com.api_gs.gef.repository.PulseiraRepository;
import com.api_gs.gef.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PulseiraRepository pulseiraRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário com pulseira vinculada",
            responses = {
                @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    public User create(@RequestBody @Valid UserDTO dto) {
        log.info("Criando usuário: {}", dto.nome());

        Optional<Abrigo> abrigo = abrigoRepository.findById(dto.abrigoId());
        Optional<Pulseira> pulseira = pulseiraRepository.findById(dto.PulseiraId());

        if (abrigo.isEmpty() || pulseira.isEmpty()) {
            throw new IllegalArgumentException("Abrigo ou Pulseira inválidos");
        }

        User usuario = new User();
        usuario.setNome(dto.nome());
        usuario.setEndereco(dto.endereco());
        usuario.setAbrigo(abrigo.get());
        usuario.setPulseira(pulseira.get());

        return userRepository.save(usuario);
    }

    @GetMapping
    @Operation(summary = "Lista os usuários com paginação e filtros")
    public Page<User> listar(
            @ParameterObject @PageableDefault(size = 10, sort = "nome") Pageable pageable,
            @RequestParam(required = false) String nome
    ) {
        log.info("Listando usuários. Filtro nome = {}", nome);

        if (nome != null && !nome.isBlank()) {
            return userRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }

        return userRepository.findAll(pageable);
    }
}