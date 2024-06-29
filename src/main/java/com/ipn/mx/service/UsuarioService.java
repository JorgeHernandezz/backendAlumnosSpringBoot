package com.ipn.mx.service;

import com.ipn.mx.domain.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(int id);
    Usuario save(Usuario usuario);
    void deleteById(int id);
    Optional<Usuario> findByEmail(String email);
}
