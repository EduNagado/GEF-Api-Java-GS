package com.api_gs.gef.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_gs.gef.model.Abrigo;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    Optional<Abrigo> findByNome(String nome);
}