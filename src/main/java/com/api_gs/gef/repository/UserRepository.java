package com.api_gs.gef.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api_gs.gef.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}