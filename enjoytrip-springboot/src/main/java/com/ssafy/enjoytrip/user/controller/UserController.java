package com.ssafy.enjoytrip.user.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.enjoytrip.user.model.UserDTO;
import com.ssafy.enjoytrip.user.model.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("UserServiceImpl")
	private UserService service;
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session, HttpServletResponse response) {
		try {
			String userId = map.get("login_id");
			String userPwd = map.get("login_pw");
			UserDTO userDto = null;
			System.out.println(userId + " " + userPwd);
			
			userDto = service.loginCheck(map);
			if(userDto != null) {				
				session.setAttribute("userinfo", userDto);
				session.setAttribute("logininfo", "loginok");
				session.setAttribute("loginid", userId);
				session.setAttribute("loginpw", userPwd);
				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/user");
				if("ok".equals(saveid)) {
					cookie.setMaxAge(60*60*24*365*40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "/";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
//			return "error/error";
			return "/";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("-------------logout-------------");
		session.invalidate();
		return "redirect:/";
	}
	
}
