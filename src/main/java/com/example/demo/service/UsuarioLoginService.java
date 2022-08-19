package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioLoginService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Usuario usuario = repository.findByEmail(login);
		if(usuario == null)
			throw new UsernameNotFoundException("Usuario não encontrado");
		
		logSucesso(usuario);
		
		return usuario;
	}
	
	private void logSucesso(Usuario usuario) {
		log.info("Login do usuario "+usuario.getId()+"realizado com sucesso");
	}
}