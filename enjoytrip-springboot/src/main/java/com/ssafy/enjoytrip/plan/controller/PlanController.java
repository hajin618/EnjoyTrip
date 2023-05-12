package com.ssafy.enjoytrip.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@GetMapping("/list")
	public String plan() {
		return "plan/planList";
	}
	
	@GetMapping("/makeplan")
	public String createPlan() {
		return "plan/makeplan";
	}
}
