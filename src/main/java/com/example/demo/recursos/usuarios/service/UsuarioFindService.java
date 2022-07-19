package com.example.demo.recursos.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.recursos.usuarios.UsuarioRepository;
import com.example.demo.recursos.usuarios.entity.Usuario;

/**
 * Referência:
 * www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html
 */
@Service
public class UsuarioFindService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> execute(Long id, String nome) {

		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withIgnoreCase("nome");

		Example<Usuario> example = Example.of(
				Usuario.builder()
				.id(id)
				.nome(nome)
				.build(), matcher);

		return usuarioRepository.findAll(example);
	}
}