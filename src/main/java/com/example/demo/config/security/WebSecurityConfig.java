package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.recursos.usuarios.service.UsuarioLoginService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioLoginService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/usuarios/pass/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.defaultSuccessUrl("/usuarios/", true)
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		http.headers()
			.frameOptions()
			.disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/spring.png")
			.and().ignoring().antMatchers(HttpMethod.GET, "/swagger-ui/**")
			.and().ignoring().antMatchers("/h2-console/**")
			.and().ignoring().antMatchers("/h2/**");
	}
}