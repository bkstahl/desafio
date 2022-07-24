package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Usuario;
import com.example.demo.response.UsuarioResponse;
import com.example.demo.service.UsuarioDeleteService;
import com.example.demo.service.UsuarioFindService;
import com.example.demo.service.UsuarioGetService;
import com.example.demo.service.UsuarioSaveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/usuarios")
@Api(value = "API Usuários")
public class UsuarioController {

	@Autowired
	private UsuarioGetService usuarioGetService;
	
	@Autowired
	private UsuarioFindService usuarioFindService;
	
	@Autowired
	private UsuarioDeleteService usuarioDeleteService;
	
	@Autowired
	private UsuarioSaveService usuarioSaveService;
	
	@GetMapping(value="/find")
	@ApiOperation(value = "Listagem de usuários")
	public ResponseEntity<List<UsuarioResponse>> get(
			@RequestParam(required=false) Long id,
			@RequestParam(required=false) String nome) {
		
		List<UsuarioResponse> lista = usuarioFindService.execute(id, nome);
		return ResponseEntity.ok(lista);
	}
	
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	@ApiOperation(value = "Alteração de usuários")
	public ResponseEntity<Usuario> save(
			@RequestBody Usuario usuario) {
		
		usuarioSaveService.execute(usuario);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@RequestMapping(value="/delete" , method=RequestMethod.DELETE)
	@ApiOperation(value = "Remoção de usuários")
	public ResponseEntity<?> delete(
			@RequestParam Long id) {
		
		usuarioDeleteService.execute(id);
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * Utilização via Thymeleaf
	 */
	@GetMapping
	@ApiOperation(value = "", hidden = true)
	public ModelAndView listagem(
			@RequestParam(required=false) Long id,
			@RequestParam(required=false) String nome) {
		
		List<UsuarioResponse> lista = usuarioFindService.execute(id, nome);
		ModelAndView modelAndView = new ModelAndView("usuario_pesquisa");		
		modelAndView.addObject("usuarios", lista);
		return modelAndView;
	}

	@GetMapping(value="/cadastro")
	@ApiOperation(value = "", hidden = true)
	public ModelAndView cadastro(
			@RequestParam(required=false) Long id) {
		
		ModelAndView modelAndView = new ModelAndView("usuario_cadastro");
		modelAndView.addObject("usuario", usuarioGetService.execute(id));
		return modelAndView;
	}
}