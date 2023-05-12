package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserDTO;
import com.ssafy.enjoytrip.user.model.service.UserService;

@RestController
@RequestMapping("/api")
public class UserControllerREST {
	@Autowired
	@Qualifier("UserServiceImpl")
	private UserService service;

	public UserControllerREST(UserService service) {
		super();
		this.service = service;
	}

	@GetMapping("/user") // userList 조회
	public Map<String, Object> userList() {
		Map<String, Object> map = new HashMap();
		List<UserDTO> list = null;
		
		try {
			list = service.userList();
			
			map.put("resmsg", "조회성공");
			map.put("resdata", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "조회실패");
		}
		return map;
	}
	
	@GetMapping("/user/{user_idx}")
	public Map<String, Object> userInfo(@PathVariable("user_idx") int userIdx) {
		Map<String, Object> map = new HashMap();
		
		try {
			UserDTO userDto = service.userInfo(userIdx);
			
			map.put("resmsg", "조회성공");
			map.put("resdata", userDto);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "조회실패");
		}
		return map;
	}
	
	@PostMapping("/user") // 회원가입
	public Map<String, Object> join(@RequestBody UserDTO userDto) {
		System.out.println("controller: " + userDto.toString());
		Map<String, Object> map = new HashMap();
		
		try {
			service.joinUser(userDto);
			map.put("resmsg", "회원가입 성공");
			map.put("resdata", userDto);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "회원가입 실패");
		}
		
		return map;
	}
	
	@PutMapping("/user")
	public Map<String, Object> updateUser(@RequestBody UserDTO userDto) {
		Map<String, Object> map = new HashMap();
		
		try {
			service.updateUser(userDto);
			map.put("resmsg", "회원정보 수정 성공");
			map.put("resdata", userDto);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "회원정보 수정 실패");
		}
		return map;
	}
	
	@DeleteMapping("/user/{user_idx}")
	public Map<String, Object> deleteUser(@PathVariable("user_idx") int userIdx) {
		System.out.println("controller>> " + userIdx);
		Map<String, Object> map = new HashMap();
		
		try {
			service.deleteUser(userIdx);
			map.put("resmsg", "회원 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "회원정보 삭제 실패");
		}
		return map;
	}
	
//	@PostMapping("/user/{user_idx}/{plan_idx}")
	
}
