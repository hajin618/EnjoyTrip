package com.ssafy.enjoytrip.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.AttractionInfoDTO;
import com.ssafy.enjoytrip.attraction.model.AttractionSelectDTO;
import com.ssafy.enjoytrip.attraction.model.GugunDTO;
import com.ssafy.enjoytrip.attraction.model.SidoDTO;
import com.ssafy.enjoytrip.attraction.model.service.AttractionService;
import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;
import com.ssafy.enjoytrip.user.controller.UserControllerREST;

@RestController
@RequestMapping("/api")
public class AttractionControllerRest {

	@Autowired
	@Qualifier("AttractionServiceImpl")
	private AttractionService service;
	
	public static final Logger logger = LoggerFactory.getLogger(UserControllerREST.class);
	
	public AttractionControllerRest(AttractionService service) {
		super();
		this.service = service;
	}
	
	// 시도 검색
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
	
	// 구군 검색
	@GetMapping("/{sido_code}/gugun")
	public Map<String, Object> gugunList(@RequestParam Map<String, String> param,
			@PathVariable("sido_code") String sido_code) throws Exception {
		List<GugunDTO> list = null;
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			list = service.searchGugun(Integer.parseInt(sido_code));
			resultMap.put("isSuccess", "true");
			resultMap.put("gugunList", list);
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
	
	// 시도코드, 구군코드, 관광지 유형, 검색어 들고 전체 관광지 검색
//	@GetMapping("/attraction")
//	public Map<String, Object> attractionList(@RequestBody AttractionInfoDTO attractionInfoDto) throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		List<AttractionInfoDTO> list = null;
//		
//		try {
//			System.out.println("==========================================");
//			System.out.println(param.toString());
//			System.out.println("sido : "+param.get("sido_code"));
//			System.out.println("gugun : "+param.get("gugun_code"));
//			list = service.searchAttraction(param);
//			resultMap.put("isSuccess", "true");
//			resultMap.put("attractionList", list);
//		} catch(Exception e) {
//			e.printStackTrace();
//			resultMap.put("isSuccess", "false");
//		}
//		return resultMap;
//	}
	
	// 어른 여행지 검색 
	@PostMapping("/attraction")
	public ResponseEntity<List<AttractionInfoDTO>> attractionList(@RequestBody AttractionSelectDTO attractionSelectDTO){
		
		List<AttractionInfoDTO> list = null;
		
		logger.info(attractionSelectDTO.toString());
		try {
			list = service.searchAttraction(attractionSelectDTO);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		
		if(list != null) {
			return new ResponseEntity<List<AttractionInfoDTO>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AttractionInfoDTO>>(list, HttpStatus.NO_CONTENT);
		}
	}
	
	// 아이 여행지 검색
	@PostMapping("/childAttraction")
	public ResponseEntity<List<ChildAttractionDTO>> childAttractionList(@RequestBody AttractionSelectDTO attractionSelectDTO){
		
		List<ChildAttractionDTO> list = null;
		
		try {
			list = service.searchChildAttraction(attractionSelectDTO);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(list != null) {
			return new ResponseEntity<List<ChildAttractionDTO>>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<ChildAttractionDTO>>(list, HttpStatus.NO_CONTENT);
		}
	}
	
	// 여행지 저장 할 때 사용
	@GetMapping("/attraction/{content_id}")
	public ResponseEntity<List<AttractionInfoDTO>> savedAttractionList(@PathVariable("content_id") String content_id){
		
		List<AttractionInfoDTO> list = null;
		
		try {
			list = service.searchAttractionById(Integer.parseInt(content_id));
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
			
			return new ResponseEntity<List<AttractionInfoDTO>>(list, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<AttractionInfoDTO>>(list, HttpStatus.OK);
	}
	
	
}
