package com.api_gs.gef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api_gs.gef.repository.FuncionarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return funcionarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
    
}
