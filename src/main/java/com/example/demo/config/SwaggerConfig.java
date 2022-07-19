package com.example.demo.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Value("${swagger.title}")
	private String title;

	@Value("${swagger.description}")
	private String description;

	@Value("${swagger.version}")
	private String version;

	@Value("${swagger.terms}")
	private String terms;

	@Value("${swagger.develloper-name}")
	private String develloperName;

	@Value("${swagger.develloper-email}")
	private String develloperEmail;

	@Value("${swagger.licence}")
	private String licence;

	@Value("${swagger.licence-url}")
	private String licenceURL;

	@Value("${swagger.base-package}")
	private String basePackage;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage(basePackage))
			.build()
			.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {

		return new ApiInfo(
				title,
				description,
				version,
				terms,
				new Contact(develloperName, "", develloperEmail),
				licence,
				licenceURL, Collections.emptyList()
		);
	}
}
