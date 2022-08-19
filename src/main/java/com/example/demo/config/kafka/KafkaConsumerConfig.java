package com.example.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.entity.Usuario;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String address;

	@Value(value = "${kafka.usuarioGroupId}")
	private String groupId;

	@Bean
	ConsumerFactory<String, Usuario> usuarioConsumerFactory() {

		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(
				configProps,
				new StringDeserializer(), 
				new JsonDeserializer<>(Usuario.class));
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, Usuario> usuarioKafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, Usuario> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(usuarioConsumerFactory());
		return factory;
	}
}