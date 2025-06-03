package com.api_gs.gef.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api_gs.gef.model.Paciente;

public interface UserRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}