package com.example.demo.recursos.logs.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.recursos.logs.LogRepository;
import com.example.demo.recursos.usuarios.entity.Log;
import com.example.demo.recursos.usuarios.entity.Usuario;

@Service
public class LogSaveService {

	@Autowired
	private LogRepository repository;

	public void execute(String tipo) {
		
		Usuario usuarioLogado = getUsuarioLogado();
		
		Log log = Log.builder()
				.log(tipo)
				.usuario(usuarioLogado)
				.data(Calendar.getInstance().getTime())
				.build();
		
		repository.save(log);
	}
	
	public Usuario getUsuarioLogado() {
		return (Usuario) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
	}
}