package com.dental.backend.service;

import com.dental.backend.model.Usuario;
import com.dental.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Usuario> listarId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public Usuario getId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
