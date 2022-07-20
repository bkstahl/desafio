package com.example.demo.response;

import java.time.LocalDate;

public interface UsuarioResponse {
	
	Long getId();
	String getNome();
	String getEmail();
	LocalDate getNascimento();
	Boolean getStatus();
}
