package com.example.demo.recursos.logs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.recursos.usuarios.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
	
}