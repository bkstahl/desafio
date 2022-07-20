package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.response.LogResponse;
import com.example.demo.service.LogFindService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/logs")
@Api(value = "API Logs")
public class LogController {
	
	@Autowired
	private LogFindService logService;

	@GetMapping
	@ApiOperation(value = "Logs de usuários")
	public ResponseEntity<List<LogResponse>> get(
			@RequestParam(required=true) Long id) {
		
		List<LogResponse> lista = logService.execute(id);
		return ResponseEntity.ok(lista);
	}
}