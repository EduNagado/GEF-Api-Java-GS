package com.api_gs.gef.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_gs.gef.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
      Optional<Funcionario> findByEmail(String email);
      
}
