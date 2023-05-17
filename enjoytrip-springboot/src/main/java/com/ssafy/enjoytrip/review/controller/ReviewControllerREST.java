package com.ssafy.enjoytrip.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.review.model.ReviewCommentDTO;
import com.ssafy.enjoytrip.review.model.ReviewDTO;
import com.ssafy.enjoytrip.review.model.ReviewSelectDTO;
import com.ssafy.enjoytrip.review.model.service.ReviewService;
import com.ssafy.enjoytrip.user.model.UserDTO;

@RestController
@RequestMapping("/api")
public class ReviewControllerREST {
	
	@Autowired
	@Qualifier("ReviewServiceImpl")
	private ReviewService service;
	
	public ReviewControllerREST(ReviewService service) {
		super();
		this.service = service;
	}

	// 한 개 조회할 때는 review, reviewComment, reviewImage 가져와야되는데
	// 목록 조회는 review랑 userName만 가져오면 됨
	
	// 1개 조회
	@GetMapping("/review/{review_idx}")
	public Map<String, Object> reviewOne(@RequestParam Map<String, String> param,
			@PathVariable("review_idx") String review_idx){
		ReviewDTO reviewDto = null;
		List<ReviewCommentDTO> reviewCommentDto = null;
		Map<String, Object> resultMap = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(map);	
		
		try {
			service.updateHit(Integer.parseInt(review_idx));
			reviewDto = service.getReview(Integer.parseInt(review_idx));
			reviewCommentDto = service.getReviewComment(Integer.parseInt(review_idx));
			resultMap.put("isSuccess", "true");
			resultMap.put("review", reviewDto);
			resultMap.put("reviewComment", reviewCommentDto);
//			resultMap.put("navigation", pageNavigation);
//			resultMap.put("pgno", param.get("pgno"));
//			resultMap.put("key", param.get("key"));
//			resultMap.put("word", param.get("word"));
			
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		
		return resultMap;
	}
	
	
	// 리스트 조회
//	@GetMapping("/review")
//	public Map<String, Object> reviewList() throws Exception{
//		List<ReviewDTO> list = null;
//		Map<String, Object> resultMap = new HashMap<>();
//		try {
//			list = service.listReview();
//			resultMap.put("isSuccess", "true");
//			resultMap.put("notices", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("isSuccess", "false");
//		}
//		return resultMap;
//	}
	
	@GetMapping("/review")
	public ResponseEntity<List<ReviewDTO>> reviewList() throws Exception{
		List<ReviewDTO> list = null;
		list = service.listReview();
		if(list == null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/review")
	public ResponseEntity<List<ReviewDTO>> reviewList(@RequestBody ReviewSelectDTO reviewSelectDTO) throws Exception{
		List<ReviewDTO> list = null;
		list = service.listReviewSort(reviewSelectDTO);
		if(list == null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	// 리뷰 작성
	//@PostMapping(value = "review", headers = "content-type=application/json")
	@PostMapping("/review")
	public Map<String, Object> writeReview(@RequestBody ReviewDTO reviewDto, @RequestParam Map<String, String> param, HttpSession session){
		
		Map<String, Object> resultMap = new HashMap<>();
//		UserDTO userDto = (UserDTO)session.getAttribute("userInfo");
//		reviewDto.setUser_idx(userDto.getUser_idx());
//		PageNavigation pageNavigation = service.makePageNavigation(param);

		try {
			service.createReview(reviewDto);
			resultMap.put("isSuccess", "true");
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
	
	// 리뷰 수정
	@PutMapping("/review/{review_idx}")
	public Map<String, Object> modifyReview(@RequestBody ReviewDTO reviewDto, @RequestParam Map<String, String> param,
			HttpSession session, @PathVariable("review_idx") String review_idx){
		Map<String, Object> resultMap = new HashMap<>();
		reviewDto.setReview_idx(Integer.parseInt(review_idx));
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			service.modifyReview(reviewDto);
			resultMap.put("isSuccess", "true");
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
	
	// 리뷰 삭제
	@DeleteMapping("/review/{review_idx}")
	public Map<String, Object> deleteReview(@RequestParam Map<String, String> param,
			HttpSession session, @PathVariable("review_idx") String review_idx){
		Map<String, Object> resultMap = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			service.deleteReview(Integer.parseInt(review_idx));
			resultMap.put("isSuccess", "true");
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
	
	// 리뷰 댓글 생성
	@PostMapping("/review/{review_idx}/comment")
	public Map<String, Object> createReviewComment(@PathVariable("review_idx") String review_idx, 
			@RequestBody ReviewCommentDTO reviewCommentDto, @RequestParam Map<String, String> param, HttpSession session){
		
		Map<String, Object> resultMap = new HashMap<>();
//		UserDTO userDto = (UserDTO)session.getAttribute("userInfo");
		reviewCommentDto.setComment_idx(Integer.parseInt(review_idx));
//		reviewCommentDto.setUser_idx(userDto.getUser_idx());
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		
		try {
			service.createReviewComment(reviewCommentDto);
			resultMap.put("isSuccess", "true");
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
	
	// 리뷰 댓글 삭제
	@DeleteMapping("/review/{review_idx}/comment/{review_comment_idx}")
	public Map<String, Object> deleteReviewComment(@PathVariable("review_idx") String review_idx,
			@PathVariable("review_comment_idx") String review_comment_idx, @RequestParam Map<String, String> param, HttpSession session){
		Map<String, Object> resultMap = new HashMap<>();
//		PageNavigation pageNavigation = service.makePageNavigation(param);
		try {
			service.deleteReviewComment(Integer.parseInt(review_idx), Integer.parseInt(review_comment_idx));
			resultMap.put("isSuccess", "true");
//			map.put("navigation", pageNavigation);
//			map.put("pgno", param.get("pgno"));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		return resultMap;
	}
}
 