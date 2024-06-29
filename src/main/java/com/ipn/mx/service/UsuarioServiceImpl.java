package com.ipn.mx.service;

import com.ipn.mx.domain.entity.Usuario;
import com.ipn.mx.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
