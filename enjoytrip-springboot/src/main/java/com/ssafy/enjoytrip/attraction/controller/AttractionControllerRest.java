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

import com.ssafy.enjoytrip.attraction.model.AttractionDescriptionDTO;
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
	
	// 어른 여행지 검색 : 여행지 저장 할 때 사용 : attraction_info 테이블에서
	@GetMapping("/attraction/{content_id}")
	public ResponseEntity<AttractionInfoDTO> savedAttractionList(@PathVariable("content_id") String content_id){
		
		AttractionInfoDTO attractionInfoDto = null;
		
		try {
			attractionInfoDto = service.searchAttractionById(Integer.parseInt(content_id));
			//System.out.println("att=========================");
			//System.out.println(attractionInfoDto.toString());
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
			
			return new ResponseEntity<AttractionInfoDTO>(attractionInfoDto, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<AttractionInfoDTO>(attractionInfoDto, HttpStatus.OK);
	}
	
	// 어른 여행지 검색 : 모달 띄울 때 사용 : attraction_description 테이블에서
	@GetMapping("/attractionDesc/{content_id}")
	public ResponseEntity<AttractionDescriptionDTO> getAttractionDesc(@PathVariable("content_id") String content_id){
		AttractionDescriptionDTO attractionDescriptionDto = null;
		
		try {
			attractionDescriptionDto = service.searchAttractionDesc(Integer.parseInt(content_id));
		}catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<AttractionDescriptionDTO>(attractionDescriptionDto, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<AttractionDescriptionDTO>(attractionDescriptionDto, HttpStatus.OK);
	}
	
	// 어린이 여행지 검색 : 여행지 저장 할 때 사용 (어린이용 관광지)
	@GetMapping("/childAttraction/{attraction_idx}")
	public ResponseEntity<ChildAttractionDTO> savedChildAttractionList(@PathVariable("attraction_idx") String attraction_idx){
		
		ChildAttractionDTO childAttractionDto = null;
		
		try {
			childAttractionDto = service.searchChildAttractionById(Integer.parseInt(attraction_idx));
			//System.out.println("child att=========================");
			//System.out.println(childAttractionDto.toString());
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
			
			return new ResponseEntity<ChildAttractionDTO>(childAttractionDto, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<ChildAttractionDTO>(childAttractionDto, HttpStatus.OK);
	}
	
	
}
