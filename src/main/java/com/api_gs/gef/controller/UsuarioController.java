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

import com.api_gs.gef.dto.UserDTO;
import com.api_gs.gef.model.Abrigo;
import com.api_gs.gef.model.Pulseira;
import com.api_gs.gef.model.Usuario;
import com.api_gs.gef.repository.AbrigoRepository;
import com.api_gs.gef.repository.UserRepository;
import com.api_gs.gef.service.PulseiraService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired  
    private UserRepository userRepository;

    @Autowired
    private PulseiraService pulseiraService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody @Valid UserDTO dto) {

        Abrigo abrigo = abrigoRepository.findByNome(dto.nomeAbrigo())
        .orElseThrow(() -> new IllegalArgumentException("Abrigo não encontrado"));

      
        Pulseira pulseira = pulseiraService.criarPulseira(80); 


        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setIdade(dto.idade());
        usuario.setEndereco(dto.endereco());
        usuario.setAbrigo(abrigo);
        usuario.setPulseira(pulseira);

        // 4. Salvar e retornar
        return userRepository.save(usuario);
    }

    @GetMapping
    @Operation(summary = "Lista os usuários com paginação e filtros")
    public Page<Usuario> listar(
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