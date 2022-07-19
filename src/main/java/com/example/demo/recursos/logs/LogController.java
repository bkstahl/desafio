package com.example.demo.recursos.logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.recursos.logs.service.LogFindService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/logs")
@Api(value = "API Logs")
public class LogController {
	
	@Autowired
	private LogFindService logService;

	@GetMapping
	public ModelAndView logs() {
		ModelAndView modelAndView = new ModelAndView("logs");
		
		modelAndView.addObject("temperatura", "23,8ºC");
		modelAndView.addObject("logs", logService.execute());
		return modelAndView;
	}
}