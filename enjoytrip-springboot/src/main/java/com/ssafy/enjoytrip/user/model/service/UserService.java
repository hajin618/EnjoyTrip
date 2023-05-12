package com.ssafy.enjoytrip.user.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.user.model.UserDTO;

public interface UserService {
	// 회원 가입
	public void joinUser(UserDTO userDto) throws Exception;
	// 회원정보 수정
	public void updateUser(UserDTO userDto) throws Exception;
	// 회원정보 삭제
	public void deleteUser(int userIdx) throws Exception;
	
	// 회원정보 조회
	public UserDTO userInfo(int userIdx) throws Exception;
	
	// 로그인 확인
	public UserDTO loginCheck(Map<String, String> map) throws Exception;
	
	// 회원 리스트 조회
	public List<UserDTO> userList();
}
