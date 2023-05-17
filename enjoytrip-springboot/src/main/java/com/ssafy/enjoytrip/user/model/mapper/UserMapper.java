package com.ssafy.enjoytrip.user.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserDTO;

@Mapper
public interface UserMapper {
//	int idCheck(String memberId) throws Exception;
//	int joinMember(UserDTO memberDto) throws Exception;
//	int modifyMember(UserDTO memberDto, String loginId) throws Exception;
//	int deleteMember(String loginId) throws Exception;
//	UserDTO loginMember(String memberId, String memberPwd) throws Exception;
	
	public UserDTO idCheck(String user_id) throws Exception;
	
	public UserDTO emailCheck(String user_email) throws Exception;
	
	public String findId(String user_email) throws Exception;
	
	public int changePw(UserDTO userDto) throws Exception;
	
	// 회원 가입
	public void joinUser(UserDTO userDto) throws Exception;
	// 회원정보 수정
	public void updateUser(UserDTO userDto) throws Exception;
	// 회원정보 삭제
	public void deleteUser(int userIdx) throws Exception;
	// 회원정보 조회
	public UserDTO userInfo(String userId) throws Exception;
	//회원 목록 조회
	public List<UserDTO> userList();
	
	// 로그인 확인
	public UserDTO login(UserDTO userDto) throws Exception;
	
	// 토큰 관련
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
