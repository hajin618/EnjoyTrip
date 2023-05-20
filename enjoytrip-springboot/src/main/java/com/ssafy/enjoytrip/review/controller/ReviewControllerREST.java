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
import com.ssafy.enjoytrip.user.model.service.UserService;

@RestController
@RequestMapping("/api")
public class ReviewControllerREST {
	
	@Autowired
	ServletContext servletContext;
	
	public static final Logger logger = LoggerFactory.getLogger(UserControllerREST.class);
	
	@Autowired
	@Qualifier("ReviewServiceImpl")
	private ReviewService service;
	
	@Autowired
	private UserService uService;
	
	public ReviewControllerREST(ReviewService service, UserService uService) {
		super();
		this.service = service;
		this.uService = uService;
	}

	// 한 개 조회할 때는 review, reviewComment, reviewImage 가져와야되는데
	// 목록 조회는 review랑 userName만 가져오면 됨
	
	@GetMapping("/review/{review_idx}")
	public ResponseEntity<ReviewDTO> reviewOne(@PathVariable("review_idx") int review_idx){
		ReviewDTO reviewDto = null;
		
		try {
			service.updateHit(review_idx);
			reviewDto = service.getReview(review_idx);
			UserDTO reviewUser = uService.getUserInfoFromReview(reviewDto.getUser_idx());
			reviewDto.setUserDto(reviewUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(reviewDto != null) {
			return new ResponseEntity<>(reviewDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
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
	
	@GetMapping("/myreview/{user_idx}")
	public ResponseEntity<List<ReviewDTO>> myReviewList(@PathVariable("user_idx") int user_idx) throws Exception{
		List<ReviewDTO> list = null;
		list = service.myReviewList(user_idx);
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

	@PostMapping("/review")
	public ResponseEntity<Integer> postReview(@RequestBody ReviewDTO reviewDto){
		int result = -1;
		try {
			int rowCount = service.createReview(reviewDto);
			result = reviewDto.getReview_idx();
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
	
	@PostMapping("/fileDelete")
	public ResponseEntity<String> deleteImage(@RequestBody int[] deleteImageIdxList){
		
		for(int i = 0; i < deleteImageIdxList.length; i++) {
			int deleteImageIdx = deleteImageIdxList[i];
			try {
				service.deleteReviewImage(deleteImageIdx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@PutMapping("/review/{review_idx}")
	public ResponseEntity<String> updateReview(@RequestBody ReviewDTO reviewDto, @PathVariable("review_idx") int review_idx){
		logger.info(reviewDto.toString());
		reviewDto.setReview_idx(review_idx);
		try {
			service.modifyReview(reviewDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 리뷰 삭제
//	@DeleteMapping("/review/{review_idx}")
//	public Map<String, Object> deleteReview(@RequestParam Map<String, String> param,
//			HttpSession session, @PathVariable("review_idx") String review_idx){
//		Map<String, Object> resultMap = new HashMap<>();
////		PageNavigation pageNavigation = service.makePageNavigation(param);
//		try {
//			service.deleteReview(Integer.parseInt(review_idx));
//			resultMap.put("isSuccess", "true");
////			map.put("navigation", pageNavigation);
////			map.put("pgno", param.get("pgno"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("isSuccess", "false");
//		}
//		return resultMap;
//	}
	
	@DeleteMapping("/review/{review_idx}")
	public ResponseEntity<String> deleteReview(@PathVariable("review_idx") int review_idx){
		
		// 이미지들 다 삭제하고 그 다음에 리뷰 삭제해야됨
		try {
			service.deleteReview(review_idx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
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
 