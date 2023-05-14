package com.ssafy.enjoytrip.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.GugunDTO;
import com.ssafy.enjoytrip.attraction.model.SidoDTO;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;

@RestController
@RequestMapping("/api")
public class AttractionControllerRest {

	@Autowired
	@Qualifier("AttractionServiceImpl")
	private AttractionService service;
	
	public AttractionControllerRest(AttractionService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/sido")
	public Map<String, Object> sidoList(@RequestParam Map<String, String> param) throws Exception {
		List<SidoDTO> list = null;
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = service.searchSido(param);
			resultMap.put("isSuccess", "true");
			resultMap.put("sidoList", list);
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
	
	@GetMapping("/{sido_code}/gugun")
	public Map<String, Object> gugunList(@RequestParam Map<String, String> param,
			@PathVariable("sido_code") String sido_code) throws Exception {
		List<GugunDTO> list = null;
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = service.searchGugun(Integer.parseInt(sido_code));
			resultMap.put("isSuccess", "true");
			resultMap.put("sidoList", list);
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
}
