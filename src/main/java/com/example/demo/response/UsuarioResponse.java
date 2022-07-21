package com.example.demo.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
	
	private Long id;
	private String nome;
	private String email;
	private LocalDate nascimento;
	private Boolean status;
}
