package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LogRepository;
import com.example.demo.response.LogResponse;

@Service
public class LogFindService {

	@Autowired
	private LogRepository repository;

	public List<LogResponse> execute(Long id) {
		return repository.findAllById(id);
	}
}