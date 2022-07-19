package com.example.demo.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableFeignClients
@Slf4j
public class FeignClientConfiguration {

	@Bean
	Retryer retryer() {
		return new Retryer.Default(0, 0, 1);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return log.isDebugEnabled() ? Logger.Level.FULL : Logger.Level.NONE;
	}
}