package com.example.demo.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.client.ViaCepClient;
import com.example.demo.config.exception.ApiException;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.response.ViaCepResponse;

@Service
public class UsuarioSaveService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ViaCepClient viaCepClient;
	
	public void execute(Usuario usuario){

		validaRegrasDeNegocio(usuario);
		realizaCriptografiaSenhaUsuario(usuario);
		usuarioRepository.save(usuario);
	}
	
	private void realizaCriptografiaSenhaUsuario(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
	}
	
	private void validaRegrasDeNegocio(Usuario usuario) {
		
		if(usuario.getNome() == null)
			throw new ApiException("Campo 'Nome' é obrigatório");
		
		if(usuario.getEmail() == null)
			throw new ApiException("Campo 'Email' é obrigatório");
		
		if(!usuario.getEmail().contains("@"))
			throw new ApiException("Campo 'Email' é inválido");
		
		if(usuario.getNome().split(" ").length <= 1)
			throw new ApiException("Campo 'Nome' deve possuir mais de uma palavra");
		
		if(usuario.getNascimento() != null && usuario.getNascimento().after(Calendar.getInstance().getTime()))
			throw new ApiException("Campo 'Nascimento' não pode ser maior que a data atual");
		
		if(usuario.getEndereco() == null || usuario.getEndereco().getCep() == null)
			throw new ApiException("Campo 'Endereço' é obrigatório");
		
		try {
			ViaCepResponse viaCepResponse = viaCepClient.getCep(usuario.getEndereco().getCep());
			if(viaCepResponse == null)
				throw new ApiException("O CEP informado não é válido");
		} catch (Exception e) {
			throw new ApiException("O CEP informado não é válido");
		}
	}
}