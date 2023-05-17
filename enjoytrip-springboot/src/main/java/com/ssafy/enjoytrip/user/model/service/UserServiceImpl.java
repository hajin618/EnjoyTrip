package com.ssafy.enjoytrip.user.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.model.UserDTO;
import com.ssafy.enjoytrip.user.model.mapper.UserMapper;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}
	
	@Override
	public UserDTO idCheck(String user_id) throws Exception {
		return userMapper.idCheck(user_id);
	}
	
	@Override
	public UserDTO emailCheck(String user_email) throws Exception {
		return userMapper.emailCheck(user_email);
	}
	
	@Override
	public String findId(String user_email) throws Exception {
		return userMapper.findId(user_email);
	}
	
	@Override
	public int changePw(UserDTO userDto) throws Exception {
		return userMapper.changePw(userDto);
	}
	
	@Override
	public void joinUser(UserDTO userDto) throws Exception {
		System.out.println(userDto.toString());
		userMapper.joinUser(userDto);
	}

	@Override
	public void updateUser(UserDTO userDto) throws Exception {
		userMapper.updateUser(userDto);
	}

	@Override
	public void deleteUser(int userIdx) throws Exception {
		userMapper.deleteUser(userIdx);
	}

	@Override
	public UserDTO login(UserDTO userDto) throws Exception {
		return userMapper.login(userDto);
	}

	@Override
	public List<UserDTO> userList() {
		return userMapper.userList();
	}

	@Override
	public UserDTO userInfo(String userId) throws Exception {
		return userMapper.userInfo(userId);
	}
	
	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return userMapper.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}
}
