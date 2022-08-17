package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.client.ViaCepClient;
import com.example.demo.config.exception.ApiException;
import com.example.demo.entity.Endereco;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.response.ViaCepResponse;

@ExtendWith(MockitoExtension.class)
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

	@Test
	public void deveOcorrerErroQuandoSalvarUsuarioSemNome(){

		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {

			Usuario usuario = getUsuarioSucesso();
			usuario.setNome(null);

			service.execute(usuario);
		});

		verify(repository, never()).save(any(Usuario.class));
		assertTrue(exception.getMessage().equals(UsuarioSaveService.CAMPO_NOME_OBRIGATORIO));
	}

	@Test
	public void deveOcorrerErroQuandoCampoNomeTiverSomenteUmaPalavra() {

		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			Usuario usuario = getUsuarioSucesso();
			usuario.setNome("Nome");

			service.execute(usuario);
		});
		
		verify(repository, never()).save(any(Usuario.class));
		assertTrue(exception.getMessage().contains(UsuarioSaveService.CAMPO_NOME_INVALIDO));
	}

	@Test
	public void deveOcorrerErroQuandoEmailForInvalido() {

		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {

			Usuario usuario = getUsuarioSucesso();
			usuario.setEmail("pessoa.com");

			service.execute(usuario);
		});
		
		verify(repository, never()).save(any(Usuario.class));
		assertTrue(exception.getMessage().contains(UsuarioSaveService.CAMPO_EMAIL_INVALIDO));
	}

	@Test
	public void deveOcorrerErroQuandoDataNascimentoForMaiorQueAgora() {

		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			Usuario usuario = getUsuarioSucesso();
			usuario.setNascimento(LocalDate.now().plusMonths(2));

			service.execute(usuario);
		});
		
		verify(repository, never()).save(any(Usuario.class));
		assertTrue(exception.getMessage().contains(UsuarioSaveService.CAMPO_NASCIMENTO_INVALIDO));
	}

	@Test
	public void deveOcorrerErroQuandoCepForInvalido(){

		ApiException exception = Assertions.assertThrows(ApiException.class, () -> {
			when(viaCepClient.getCep(any(Integer.class)))
			.thenThrow(new RuntimeException());

			Usuario usuario = getUsuarioSucesso();

			service.execute(usuario);
		});

		verify(repository, never()).save(any(Usuario.class));
		assertTrue(exception.getMessage().contains(UsuarioSaveService.CAMPO_CEP_INVALIDO));
	}

	private static Usuario getUsuarioSucesso() {
		return Usuario
				.builder()
				.id(new Random().nextLong())
				.nome("Nome Pessoa")
				.email("pessoa@email.com")
				.senha(getRandonString())
				.nascimento(LocalDate.now())
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
