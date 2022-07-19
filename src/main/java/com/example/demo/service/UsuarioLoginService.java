package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Repository
public class UsuarioLoginService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Usuario usuario = repository.findByEmail(login);

		if(usuario == null)
			throw new UsernameNotFoundException("Usuario não encontrado");
		
		return usuario;
	}
}