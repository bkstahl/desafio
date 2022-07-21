package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.response.UsuarioResponse;

/**
 * Referências:
 * http://www.logicbig.com/tutorials/spring-framework/spring-data/query-example-matchers.html
 * http://modelmapper.org/getting-started/
 */
@Service
public class UsuarioFindService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioResponse> execute(Long id, String nome) {

		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withIgnoreCase("nome");

		Example<Usuario> example = Example.of(
				Usuario.builder()
				.id(id)
				.nome(nome)
				.build(), matcher);

		return usuarioRepository.findAll(example)
				.stream()
				.map(usuario -> modelMapper.map(usuario, UsuarioResponse.class))
				.collect(Collectors.toList());
	}
}