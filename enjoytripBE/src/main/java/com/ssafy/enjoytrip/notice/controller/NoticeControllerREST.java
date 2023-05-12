package com.ssafy.enjoytrip.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.notice.model.NoticeDTO;
import com.ssafy.enjoytrip.notice.model.service.NoticeService;
import com.ssafy.enjoytrip.util.PageNavigation;

@RestController
@RequestMapping("/api")
public class NoticeControllerREST {

	@Autowired
	@Qualifier("NoticeServiceImpl")
	private NoticeService service;

	public NoticeControllerREST(NoticeService service) {
		super();
		this.service = service;
	}

	@GetMapping("/notice")
	public Map<String, Object> noticeList(@RequestParam Map<String, String> map) throws Exception {
		List<NoticeDTO> list = null;
		Map<String, Object> resultMap = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(map);
		
		try {
			list = service.listNotice(map);
			resultMap.put("isSuccess", "true");
			resultMap.put("notices", list);
//			resultMap.put("navigation", pageNavigation);
//			resultMap.put("pgno", map.get("pgno"));
//			resultMap.put("key", map.get("key"));
//			resultMap.put("word", map.get("word"));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}

	@GetMapping("/notice/{notice_idx}")
	public Map<String, Object> noticeOne(@RequestParam Map<String, Object> param,
			@PathVariable("notice_idx") String notice_idx) {
		NoticeDTO noticeDto = null;
		Map<String, Object> map = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			service.updateHit(Integer.parseInt(notice_idx));
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
//		PageNavigation pageNavigation = service.makePageNavigation(param);
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
