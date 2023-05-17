package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserDTO;
import com.ssafy.enjoytrip.user.model.service.UserService;
import com.ssafy.enjoytrip.util.jwt.JwtServiceImpl;

@RestController
@RequestMapping("/api")
public class UserControllerREST {
	@Autowired
	@Qualifier("UserServiceImpl")
	private UserService service;
	
	public static final Logger logger = LoggerFactory.getLogger(UserControllerREST.class);
	
	@Autowired
	private JwtServiceImpl jwtService;

	public UserControllerREST(UserService service) {
		super();
		this.service = service;
	}
	
	// 성공시 202 실패시 500
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDTO userDto){
		logger.info("into login logic");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info(userDto.toString());
		try {
			UserDTO loginUser = service.login(userDto);
			// 로그인 성공했을 경우
			if(loginUser != null) {
				logger.info("login success");
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUser_id());
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUser_id());
				service.saveRefreshToken(userDto.getUser_id(), refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}
			// 로그인 실패했을 경우
			else {
				resultMap.put("message", "FAILED");
				status = HttpStatus.ACCEPTED;
			}
		}
		catch(Exception e) {
			resultMap.put("message", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	// 카카오 로그인 -> 그냥 무조건 인증 되면 토큰 생성 -> id가 아닌 email을 userid로 설정, 인증되기만 하고 나머지 로직은 일반 사용자 로그인과 동일하도록
	@PostMapping("/kakaoLogin")
	public ResponseEntity<Map<String, Object>> kakaoLogin(@RequestBody UserDTO userDto) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		// 카카오 로그인 했다면 DB에 실제 데이터 삽입 회원가입 개념
		UserDTO joinDto = new UserDTO();
		joinDto.setUser_id(userDto.getUser_id());
		joinDto.setUser_email(userDto.getUser_id());
		service.joinUser(joinDto);
		
		String accessToken = jwtService.createAccessToken("userid", userDto.getUser_id());
		String refreshToken = jwtService.createRefreshToken("userid", userDto.getUser_id());
		service.saveRefreshToken(userDto.getUser_email(), refreshToken);
		resultMap.put("access-token", accessToken);
		resultMap.put("refresh-token", refreshToken);
		resultMap.put("message", "SUCCESS");
		status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 로그아웃
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			service.deleRefreshToken(userid);
			resultMap.put("message", "SUCCESS");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userid, HttpServletRequest request){
		logger.info("into info logic");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		if(jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("토큰 검증 완료");
			try {
				UserDTO userDto = service.userInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
				logger.info(userDto.toString());
				logger.info("유저 정보 담아서 프론트로 전송");
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		else {
			resultMap.put("message", "FAILED");
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<Map<String, Object>> (resultMap, status);
	}
	
	// Access token 재발급
	// 재발급 성공 시 202, 실패시 401
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDTO userDto, HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		
		if(jwtService.checkToken(token)) {
			if(token.equals(service.getRefreshToken(userDto.getUser_id()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getUser_id());
				resultMap.put("access-token", accessToken);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}
		}
		else {
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@GetMapping("/idCheck/{user_id}")
	 public ResponseEntity<String> checkId(@PathVariable("user_id") String user_id) throws Exception{
		UserDTO userDto = null;
        userDto = service.idCheck(user_id);
        if(userDto == null) {
        	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);	
        }
        else {
        	return new ResponseEntity<>("FAILED", HttpStatus.NO_CONTENT);
        }
    }
	
	@GetMapping("/emailCheck/{user_email}")
	 public ResponseEntity<String> checkEmail(@PathVariable("user_email") String user_email) throws Exception{
		UserDTO userDto = null;
       userDto = service.emailCheck(user_email);
       if(userDto == null) {
       	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);	
       }
       else {
       	return new ResponseEntity<>("FAILED", HttpStatus.NO_CONTENT);
       }
   }
	
	@GetMapping("/findId/{user_email}")
	 public ResponseEntity<String> findId(@PathVariable("user_email") String user_email) throws Exception{
		String userId = null;
       userId = service.findId(user_email);
       
       if(userId != null) {
       	return new ResponseEntity<>(userId, HttpStatus.OK);	
       }
       else {
       	return new ResponseEntity<>("FAILED", HttpStatus.NO_CONTENT);
       }
   }
	
	@PostMapping("/changePwd")
	public ResponseEntity<String> changePwd(@RequestBody UserDTO userDto) throws Exception{
		int result = 0;
		result = service.changePw(userDto);
		
		if(result == 1) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("FAILED", HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<String> join(@RequestBody UserDTO userDto){
		try {
			service.joinUser(userDto);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);	
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("FAILED", HttpStatus.OK);	
		}
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
	
//	@GetMapping("/user/{user_idx}")
//	public Map<String, Object> userInfo(@PathVariable("user_idx") int userIdx) {
//		Map<String, Object> map = new HashMap();
//		
//		try {
//			UserDTO userDto = service.userInfo(userIdx);
//			
//			map.put("resmsg", "조회성공");
//			map.put("resdata", userDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("resmsg", "조회실패");
//		}
//		return map;
//	}
	
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
}
