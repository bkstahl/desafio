package com.example.demo.recursos.logs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.recursos.logs.LogRepository;
import com.example.demo.recursos.usuarios.entity.Log;

@Service
public class LogFindService {

	@Autowired
	private LogRepository repository;

	public List<Log> execute() {
		return repository.findAll();
	}
}