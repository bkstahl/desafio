package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Log;
import com.example.demo.response.LogResponse;

public interface LogRepository extends JpaRepository<Log, Long> {
	
	List<LogResponse> findAllById(Long id);
}