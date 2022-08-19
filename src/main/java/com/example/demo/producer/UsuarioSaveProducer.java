package com.example.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.demo.entity.Usuario;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsuarioSaveProducer {

	@Value(value = "${kafka.usuarioSaveTopic}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, Usuario> kafkaTemplate;

	public void sendMessage(Usuario usuario) {
		
		log.info("Enviando mensagem para salvamento de usuario");
		
		ListenableFuture<SendResult<String, Usuario>> future = 
				kafkaTemplate.send(topicName, usuario);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Usuario>>() {
			@Override
			public void onSuccess(SendResult<String, Usuario> result) {
				log.info("Mensagem para salvamento de usuario enviada com sucesso");
			}
			@Override
			public void onFailure(Throwable ex) {
				log.error("Erro ao enviar mensagem para salvamento de usuario");
			}
		});
	}
}
