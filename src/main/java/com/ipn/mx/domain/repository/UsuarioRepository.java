package com.ipn.mx.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ipn.mx.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
	Optional<Usuario> findByEmail(String email);
}
