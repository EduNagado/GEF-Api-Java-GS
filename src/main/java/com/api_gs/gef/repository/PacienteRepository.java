package com.api_gs.gef.repository;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api_gs.gef.model.Paciente;
import com.api_gs.gef.model.Pulseira;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Optional<Paciente> findByPulseira(Pulseira pulseira);
}