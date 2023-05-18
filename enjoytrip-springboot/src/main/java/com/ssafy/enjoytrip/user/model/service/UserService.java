package com.ssafy.enjoytrip.user.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.user.model.UserDTO;

public interface UserService {
	
	public UserDTO idCheck(String user_id) throws Exception;
	
	public UserDTO emailCheck(String user_email) throws Exception;
	
	public String findId(String user_email) throws Exception;
	
	public int changePw(UserDTO userDto) throws Exception;
	
	// 회원 가입
	public void joinUser(UserDTO userDto) throws Exception;
	// 회원정보 수정
	public void updateUser(UserDTO userDto) throws Exception;
	// 회원정보 삭제
	public void deleteUser(String user_id) throws Exception;
	
	// 회원정보 조회
	public UserDTO userInfo(String userId) throws Exception;
	
	// 회원 리스트 조회
	public List<UserDTO> userList();
	
	// 로그인 확인
	public UserDTO login(UserDTO userDto) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
}
