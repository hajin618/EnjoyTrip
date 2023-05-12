package com.ssafy.enjoytrip.attraction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attraction")
public class AttractionControler {
	
	@GetMapping("view")
	public String attraction() {
		return "attraction/search";
	}
}
