package com.example.demo.recursos.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.recursos.usuarios.UsuarioRepository;

@Service
public class UsuarioDeleteService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void execute(Long id) {
		usuarioRepository.deleteById(id);
	}
}