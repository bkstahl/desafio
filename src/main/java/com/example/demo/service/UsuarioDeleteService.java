package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioDeleteService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void execute(Long id) {
		usuarioRepository.deleteById(id);
		logSucesso(id);
	}
	
	private void logSucesso(Long id) {
		log.info("Remoção do usuário "+id+ " realizada com sucesso");
	}
}