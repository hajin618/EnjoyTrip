package com.ssafy.enjoytrip.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.notice.model.NoticeDTO;
import com.ssafy.enjoytrip.notice.model.service.NoticeService;
import com.ssafy.enjoytrip.user.model.UserDTO;
import com.ssafy.enjoytrip.util.PageNavigation;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	@Qualifier("NoticeServiceImpl")
	private NoticeService service;

	public NoticeController(NoticeService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav = new ModelAndView();
//		PageNavigation pageNavigation = service.makePageNavigation(map);
		PageNavigation pageNavigation = service.makePageNavigation(map);
		
		List<NoticeDTO> list = service.listNotice(map);
		mav.addObject("notice", list);
		mav.addObject("navigation", pageNavigation);
		
		mav.addObject("pgno", 1);
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		
		mav.setViewName("notice/board");
		
		return mav;
	}
	
	@GetMapping("/write")
	public String write() {
		return "notice/write";
	}
	
	@PostMapping("/write")
	public String write(NoticeDTO noticeDto, HttpSession session, Model model) {
		UserDTO userDto = (UserDTO) session.getAttribute("userinfo");
		
		noticeDto.setUser_idx(userDto.getUser_idx());
		System.out.println(">>" + noticeDto.toString());
		try {
			service.createNotice(noticeDto);
			
			return "redirect:/notice/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/view")
	public String showNotice(@RequestParam("notice_idx") int noticeIdx, Model model) throws Exception {
		NoticeDTO noticeDto = service.getNotice(noticeIdx);
		int noticeHit = noticeDto.getNotice_hit();

		service.updateHit(noticeIdx);
	
		model.addAttribute("notice", noticeDto);
		return "notice/view";
	}
	
	@GetMapping("/delete")
	public String deleteNotice(@RequestParam Map<String, String> map, RedirectAttributes redirectAttributes) throws Exception {
		int notice_idx = Integer.parseInt(map.get("notice_idx"));
		
		System.out.println(">>>" + notice_idx);
		service.deleteNotice(notice_idx);
		
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("pgno", 1);
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
	
		return "redirect:/notice/list";
	}
//	@GetMapping("/notice")
//	@GetMapping("/notice")
//	public Map<String, Object> noticeList(@RequestBody Map<String, Object> param, Model model) {
//		List<NoticeDTO> list = null;
//		Map<String, Object> map = new HashMap<>();
////		PageNavigation pageNavigation = service.makePageNavigation(param);
//		try {
//			System.out.println("notice 보여라");
//			list = service.listNotice(param);
//			
//			map.put("isSuccess", "true");
//			map.put("notices", list);
//			
//			for(int i = 0; i <list.size(); i++) {
//				System.out.println(list.get(i));
//			}
//			model.addAttribute("articles", list);
////			map.put("navigation", pageNavigation);
////			map.put("pgno", param.get("pgno"));
////			map.put("key", param.get("key"));
////			map.put("word", param.get("word"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("isSuccess", "false");
//		}
//		return map;
//	}

	@GetMapping("/notice")
	public Map<String, Object> noticeOne(@RequestBody Map<String, Object> param,
			@PathVariable("notice_idx") String notice_idx) {
		NoticeDTO noticeDto = null;
		Map<String, Object> map = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			noticeDto = service.getNotice(Integer.parseInt(notice_idx));
			map.put("isSuccess", "true");
			map.put("notice", noticeDto);
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
//			map.put("key", param.get("key"));
//			map.put("word", param.get("word"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("isSuccess", "false");
		}

		return map;
	}

	@PostMapping(value = "/notice", headers = "content-type=application/json")
	public Map<String, Object> writeNotice(@RequestBody Map<String, Object> param, NoticeDTO noticeDto,
			HttpSession session) {
//		 UserDTO userDto = (UserDTO)session.getAttribute("userInfo");
//		 noticeDto.setUser_idx(userDto.getUser_idx());
		Map<String, Object> map = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			service.createNotice(noticeDto);
			map.put("isSuccess", "true");
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
//			map.put("key", param.get("key"));
//			map.put("word", param.get("word"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("isSuccess", "false");
		}

		return map;
	}

	@DeleteMapping("/notice/{notice_idx}")
	public Map<String, Object> deleteNotice(@RequestBody Map<String, Object> param,
			@PathVariable("notice_idx") String notice_idx) {
		Map<String, Object> map = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			service.deleteNotice(Integer.parseInt(notice_idx));
			map.put("isSuccess", "true");
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
//			map.put("key", param.get("key"));
//			map.put("word", param.get("word"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("isSuccess", "false");
		}

		return map;
	}

	@PutMapping("/notice/{notice_idx}")
	public Map<String, Object> modifyNotice(@RequestBody Map<String, Object> param, NoticeDTO noticeDto,
			@PathVariable("notice_idx") String notice_idx, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
//			PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			service.modifyNotice(noticeDto);
			map.put("isSuccess", "true");
//				map.put("navigation", pageNavigation);
//				map.put("pgno", param.get("pgno"));
//				map.put("key", param.get("key"));
//				map.put("word", param.get("word"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("isSuccess", "false");
		}

		return map;
	}

}
