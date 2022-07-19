package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.example.demo.recursos.logs.service.LogSaveService;

@Component
public class AuthenticationListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Autowired
	private LogSaveService logSaveService;
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event){
		logSaveService.execute("Login");
	}
}