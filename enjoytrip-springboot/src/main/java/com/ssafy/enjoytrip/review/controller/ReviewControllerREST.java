package com.ssafy.enjoytrip.review.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.review.model.ReviewCommentDTO;
import com.ssafy.enjoytrip.review.model.ReviewDTO;
import com.ssafy.enjoytrip.review.model.ReviewImageDTO;
import com.ssafy.enjoytrip.review.model.ReviewSelectDTO;
import com.ssafy.enjoytrip.review.model.service.ReviewService;
import com.ssafy.enjoytrip.user.controller.UserControllerREST;
import com.ssafy.enjoytrip.user.model.UserDTO;

@RestController
@RequestMapping("/api")
public class ReviewControllerREST {
	
	@Autowired
	ServletContext servletContext;
	
	public static final Logger logger = LoggerFactory.getLogger(UserControllerREST.class);
	
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
		
		try {
			service.updateHit(Integer.parseInt(review_idx));
			reviewDto = service.getReview(Integer.parseInt(review_idx));
			reviewCommentDto = service.getReviewComment(Integer.parseInt(review_idx));
			resultMap.put("isSuccess", "true");
			resultMap.put("review", reviewDto);
			resultMap.put("reviewComment", reviewCommentDto);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		
		return resultMap;
	}
	
	@GetMapping("/review")
	public ResponseEntity<List<ReviewDTO>> reviewList() throws Exception{
		List<ReviewDTO> list = null;
		list = service.listReview();
		if(list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/reviewsort")
	public ResponseEntity<List<ReviewDTO>> reviewListSort(@RequestParam("sido_code") int sido_code, @RequestParam("review_type") String review_type) throws Exception{
		
		logger.info("into sort logic");
		logger.info("sido_code" + sido_code);
		logger.info("review_type" + review_type);
		
		ReviewSelectDTO reviewSelectDTO = new ReviewSelectDTO();
		reviewSelectDTO.setSido_code(sido_code);
		if(review_type.equals("")) {
			reviewSelectDTO.setReview_type(null);
		}
		else {
			reviewSelectDTO.setReview_type(review_type);	
		}
		
		List<ReviewDTO> list = null;
		list = service.listReviewSort(reviewSelectDTO);
		if(list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	// 리뷰 작성
	//@PostMapping(value = "review", headers = "content-type=application/json")
//	@PostMapping("/review")
//	public Map<String, Object> writeReview(@RequestBody ReviewDTO reviewDto, @RequestParam Map<String, String> param, HttpSession session){
//		
//		Map<String, Object> resultMap = new HashMap<>();
////		UserDTO userDto = (UserDTO)session.getAttribute("userInfo");
////		reviewDto.setUser_idx(userDto.getUser_idx());
////		PageNavigation pageNavigation = service.makePageNavigation(param);
//
//		try {
//			service.createReview(reviewDto);
//			resultMap.put("isSuccess", "true");
////			map.put("navigation", pageNavigation);
////			map.put("pgno", param.get("pgno"));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("isSuccess", "false");
//		}
//		return resultMap;
//	}

	@PostMapping("/review")
	public ResponseEntity<Integer> postReview(@RequestBody ReviewDTO reviewDto){
		int result = -1;
		try {
			int rowCount = service.createReview(reviewDto);
			result = reviewDto.getReview_idx();
//			long rowCount = pushService.insertPushSend(pushSendDomain);
//			long push_send_seq = pushSendDomain.getPush_send_seq();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result != -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Integer>(result, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/fileUpload")
	public ResponseEntity<String> postImage(@RequestParam("upfile") MultipartFile[] files, @RequestParam("review_idx") int reviewIdx) throws Exception{
		
		String uploadDirectory = servletContext.getRealPath("/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadDirectory + File.separator + today;
		File folder = new File(saveFolder);
		
		if (!folder.exists())
			folder.mkdirs();
		
		for(MultipartFile mfile : files) {
			String originalFileName = mfile.getOriginalFilename();
			if(!originalFileName.isEmpty()) {
				// 디비에 저장
				ReviewImageDTO reviewImageDto = new ReviewImageDTO();
				reviewImageDto.setReview_idx(reviewIdx);
				reviewImageDto.setImage_name(originalFileName);
				reviewImageDto.setImage_url(folder.toString() + File.separator + originalFileName);
				service.createReviewImage(reviewImageDto);
				// 서버에 저장 
				mfile.transferTo(new File(folder, originalFileName));
			}
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
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
 