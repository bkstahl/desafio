package com.example.demo.recursos.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.recursos.usuarios.UsuarioRepository;
import com.example.demo.recursos.usuarios.entity.Endereco;
import com.example.demo.recursos.usuarios.entity.Usuario;

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