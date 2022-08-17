package com.example.demo.service;

import java.time.LocalDate;

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

	public static String CAMPO_NOME_OBRIGATORIO = "Campo 'Nome' é obrigatório";
	public static String CAMPO_EMAIL_OBRIGATORIO = "Campo 'Email' é obrigatório";
	public static String CAMPO_EMAIL_INVALIDO = "Campo 'Email' é inválido";
	public static String CAMPO_NOME_INVALIDO = "Campo 'Nome' deve possuir mais de uma palavra";
	public static String CAMPO_NASCIMENTO_INVALIDO = "Campo 'Nascimento' não pode ser maior que a data atual";
	public static String CAMPO_ENDERECO_OBRIGATORIO = "Campo 'Endereço' é obrigatório";
	public static String CAMPO_CEP_INVALIDO = "O CEP informado não é válido";

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
			throw new ApiException(CAMPO_NOME_OBRIGATORIO);

		if(usuario.getEmail() == null)
			throw new ApiException(CAMPO_EMAIL_OBRIGATORIO);

		if(!usuario.getEmail().contains("@"))
			throw new ApiException(CAMPO_EMAIL_INVALIDO);

		if(usuario.getNome().split(" ").length <= 1)
			throw new ApiException(CAMPO_NOME_INVALIDO);

		if(usuario.getNascimento() != null && usuario.getNascimento().isAfter(LocalDate.now()))
			throw new ApiException(CAMPO_NASCIMENTO_INVALIDO);

		if(usuario.getEndereco() == null || usuario.getEndereco().getCep() == null)
			throw new ApiException(CAMPO_ENDERECO_OBRIGATORIO);

		try {
			ViaCepResponse viaCepResponse = viaCepClient.getCep(usuario.getEndereco().getCep());
			if(viaCepResponse == null)
				throw new ApiException(CAMPO_CEP_INVALIDO);
		} catch (Exception e) {
			throw new ApiException(CAMPO_CEP_INVALIDO);
		}
	}
}