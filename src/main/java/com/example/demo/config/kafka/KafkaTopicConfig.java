package com.example.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String address;
	
	@Value(value = "${kafka.usuarioSaveTopic}")
	private String topic;

	@Bean
	KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, address);
		return new KafkaAdmin(configs);
	}

	@Bean
	NewTopic topic1() {
		return new NewTopic(topic, 1, (short) 1);
	}
}