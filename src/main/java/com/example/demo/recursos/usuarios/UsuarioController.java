package com.example.demo.recursos.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.recursos.usuarios.entity.Usuario;
import com.example.demo.recursos.usuarios.service.UsuarioDeleteService;
import com.example.demo.recursos.usuarios.service.UsuarioFindService;
import com.example.demo.recursos.usuarios.service.UsuarioGetService;
import com.example.demo.recursos.usuarios.service.UsuarioSaveService;

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
	
	@GetMapping
	public ModelAndView listagem(
			@RequestParam(required=false) Long id,
			@RequestParam(required=false) String nome) {
		
		List<Usuario> lista = usuarioFindService.execute(id, nome);
		ModelAndView modelAndView = new ModelAndView("usuario_pesquisa");		
		modelAndView.addObject("usuarios", lista);
		return modelAndView;
	}

	@GetMapping(value="/cadastro")
	public ModelAndView cadastro(
			@RequestParam(required=false) Long id) {
		
		ModelAndView modelAndView = new ModelAndView("usuario_cadastro");
		modelAndView.addObject("usuario", usuarioGetService.execute(id));
		return modelAndView;
	}

	@GetMapping(value="/get")
	@ApiOperation(value = "Listagem de usuários")
	public ResponseEntity<List<Usuario>> get(
			@RequestParam(required=false) Long id,
			@RequestParam(required=false) String nome) {
		
		List<Usuario> lista = usuarioFindService.execute(id, nome);
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
	
	@RequestMapping(value="/pass/{pass}", method=RequestMethod.GET)
	public ResponseEntity<String> pass(
			@PathVariable("pass") String pass) {
		
		return ResponseEntity.ok(new BCryptPasswordEncoder().encode(pass));
	}
}