package com.app.weatherapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WheaterViewController {
	
	@GetMapping(value = "/index")
	public String index() {
		return "index";
	}

}
