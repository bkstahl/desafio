package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioGetService;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioGetServiceTest {

	@InjectMocks
	private UsuarioGetService service;

	@Mock
	private UsuarioRepository repository;

	@Test
	public void deveCarregarUsuario(){

		long codigo = new Random().nextLong();
		
		Usuario usuario = Usuario
				.builder()
				.id(codigo)
				.build();
		
		when(repository.findUsuario(
				any(Long.class))).thenReturn(usuario);

		Usuario user = service.execute(codigo);

		verify(repository).findUsuario(codigo);

		assert(user.getId() == codigo);
	}
}
