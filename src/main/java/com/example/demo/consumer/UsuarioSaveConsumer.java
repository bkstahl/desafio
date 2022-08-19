package com.example.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioSaveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsuarioSaveConsumer {

	@Autowired
	private UsuarioSaveService usuarioSaveService;
	
	@KafkaListener(
			topics = "usuarioSaveTopic",
			groupId = "usuario",
			containerFactory = "usuarioKafkaListenerContainerFactory")
	public void usuarioListener(Usuario usuario) {
		
		log.info("Recebendo mensagem para salvamento de usuario");
	    usuarioSaveService.execute(usuario);
	}
}
