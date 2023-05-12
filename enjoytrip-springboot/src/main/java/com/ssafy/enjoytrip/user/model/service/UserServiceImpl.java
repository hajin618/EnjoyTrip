package com.ssafy.enjoytrip.user.model.service;

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
	public UserDTO loginCheck(Map<String, String> map) throws Exception {
		return userMapper.loginCheck(map);
	}

	@Override
	public List<UserDTO> userList() {
		return userMapper.userList();
	}

	@Override
	public UserDTO userInfo(int userIdx) throws Exception {
		return userMapper.userInfo(userIdx);
	}
}
