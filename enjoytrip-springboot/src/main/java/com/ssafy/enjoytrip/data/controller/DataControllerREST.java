package com.ssafy.enjoytrip.data.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;
import com.ssafy.enjoytrip.data.model.service.DataService;

@RestController
@RequestMapping("/api")
public class DataControllerREST {

	@Autowired
	@Qualifier("DataServiceImpl")
	private DataService service;
	
	public DataControllerREST(DataService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/childAttraction")
	public Map<String, Object> putChildAttraction(){
		Map<String, Object> resultMap = new HashMap<>();
		ChildAttractionDTO childAttractionDto = null;
		
//		
		
		return resultMap;
	}	
	
	
}
