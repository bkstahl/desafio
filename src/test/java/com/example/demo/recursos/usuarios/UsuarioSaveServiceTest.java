package com.example.demo.recursos.usuarios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.core.exception.ApiException;
import com.example.demo.recursos.cep.ViaCepClient;
import com.example.demo.recursos.cep.ViaCepResponse;
import com.example.demo.recursos.usuarios.entity.Endereco;
import com.example.demo.recursos.usuarios.entity.Usuario;
import com.example.demo.recursos.usuarios.service.UsuarioSaveService;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioSaveServiceTest {

	@InjectMocks
	private UsuarioSaveService service;

	@Mock
	private UsuarioRepository repository;

	@Mock
	private ViaCepClient viaCepClient;

	@Test
	public void deveSalvarUsuario(){

		when(viaCepClient.getCep(any(Integer.class)))
			.thenReturn(createViaCepResponseMock());
		
		Usuario usuario = getUsuarioSucesso();

		service.execute(usuario);

		verify(repository).save(any(Usuario.class));
	}

	@Test(expected = ApiException.class)
	public void deveOcorrerErroQuandoSalvarUsuarioSemNome(){

		when(viaCepClient.getCep(any(Integer.class)))
			.thenReturn(createViaCepResponseMock());
		
		Usuario usuario = getUsuarioSucesso();
		usuario.setNome(null);

		service.execute(usuario);

		verify(repository, never()).save(any(Usuario.class));
	}

	@Test(expected = ApiException.class)
	public void deveOcorrerErroQuandoCampoNomeTiverSomenteUmaPalavra() {

		Usuario usuario = getUsuarioSucesso();
		usuario.setNome("Nome");

		service.execute(usuario);

		verify(repository, never()).save(any(Usuario.class));
	}
	
	@Test(expected = ApiException.class)
	public void deveOcorrerErroQuandoEmailForInvalido() {

		when(viaCepClient.getCep(any(Integer.class)))
			.thenReturn(createViaCepResponseMock());
		
		Usuario usuario = getUsuarioSucesso();
		usuario.setEmail("pessoa.com");
		
		service.execute(usuario);

		verify(repository, never()).save(any(Usuario.class));
	}
	
	@Test(expected = ApiException.class)
	public void deveOcorrerErroQuandoDataNascimentoForMaiorQueAgora() {

		when(viaCepClient.getCep(any(Integer.class)))
			.thenReturn(createViaCepResponseMock());
		
		Calendar nascimentoMaiorQueDataAtual = Calendar.getInstance();
		nascimentoMaiorQueDataAtual.add(Calendar.DAY_OF_MONTH , 2);
		
		Usuario usuario = getUsuarioSucesso();
		usuario.setNascimento(nascimentoMaiorQueDataAtual.getTime());
		
		service.execute(usuario);

		verify(repository, never()).save(any(Usuario.class));
	}

	@Test(expected = ApiException.class)
	public void deveOcorrerErroQuandoCepForInvalido(){

		when(viaCepClient.getCep(any(Integer.class)))
			.thenThrow(new RuntimeException());
		
		Usuario usuario = getUsuarioSucesso();
		
		service.execute(usuario);

		verify(repository, never()).save(any(Usuario.class));
	}
	
	private static Usuario getUsuarioSucesso() {
		return Usuario
				.builder()
				.id(new Random().nextLong())
				.nome("Nome Pessoa")
				.email("pessoa@email.com")
				.senha(getRandonString())
				.nascimento(Calendar.getInstance().getTime())
				.endereco(Endereco.builder().cep(93320400).build())
				.status(true)
				.build();
	}
	
	private static ViaCepResponse createViaCepResponseMock() {
		return  ViaCepResponse
					.builder()
					.logradouro(getRandonString())
					.complemento(getRandonString())
					.bairro(getRandonString())
					.localidade(getRandonString())
					.uf(getRandonString())
					.ibge(123456)
					.cep(getRandonString())
					.build();
	}
	
	private static String getRandonString() {
		byte[] array = new byte[10];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}
}
