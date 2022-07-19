package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.response.ViaCepResponse;

@FeignClient(url = "${client.url.cep}", name="viacep")
public interface ViaCepClient {

	@GetMapping("/{cep}/json/")
	ViaCepResponse getCep(@PathVariable int cep);
}