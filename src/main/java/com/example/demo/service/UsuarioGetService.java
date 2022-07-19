package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Endereco;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioGetService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario execute(Long id) {
		if(id!=null)
			return usuarioRepository.findUsuario(id);
		return Usuario
				.builder()
				.endereco(Endereco.builder().build())
				.build();
	}
}