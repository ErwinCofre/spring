package com.spring.spring.repositorios;

import com.spring.spring.models.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioLoginRepositoryJPA extends JpaRepository<UsuarioLogin, Integer> {

    Optional<UsuarioLogin> findByUsername(String username);
}
