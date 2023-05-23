package com.ssafy.enjoytrip.plan.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

import com.ssafy.enjoytrip.attraction.model.AttractionInfoDTO;
import com.ssafy.enjoytrip.plan.model.PlanCommentDTO;
import com.ssafy.enjoytrip.plan.model.PlanDTO;
import com.ssafy.enjoytrip.plan.model.PlanDetailDTO;
import com.ssafy.enjoytrip.plan.model.service.PlanService;

@RestController
@RequestMapping("/api")
public class PlanControllerREST {
	@Autowired
	@Qualifier("PlanServiceImpl")
	private PlanService service;
	
	@Autowired
	ServletContext servletContext;

	public PlanControllerREST(PlanService service) {
		super();
		this.service = service;
	}

	// 페이지네이션 정보 가지고 가는거 - service에서 처리하는거 해야 됨(이해가 잘,,, ㅠ-ㅠ)
	@GetMapping("/plan") // 여행 계획 리스트 조회
	public Map<String, Object> planList(@RequestParam Map<String, String> map, Model model) throws Exception {
		Map<String, Object> resultMap = new HashMap();

		try {
			List<PlanDTO> list = service.listPlan(map);

			resultMap.put("resmsg", "여행 계획 리스트 조회 성공");
			resultMap.put("resdata", list);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "여행 계획 리스트 조회 실패");
		}
		return resultMap;
	}
	
	@PostMapping("/plan/fileUpload")
	public ResponseEntity<String> postImage(@RequestParam("upfile") MultipartFile[] files, @RequestParam("plan_idx") int plan_idx) throws Exception {
		String uploadDirectory = servletContext.getRealPath("/upload/plan");
		File folder = new File(uploadDirectory);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		for(MultipartFile mfile : files) {
			String originalFileName = plan_idx+".png";
			if(!originalFileName.isEmpty()) {
				mfile.transferTo(new File(folder, originalFileName));
			}
		}
		return new ResponseEntity<String> ("SUCCESS", HttpStatus.OK);
	}
	
//	@GetMapping("/plan/{plan_idx}")  // 여행 계획 1개 조회, '여행 계획 + 여행 계획 상세 + 댓글' 
//	public Map<String, Object> planInfo(@PathVariable("plan_idx") int planIdx, @RequestParam Map<String, String> map, Model model) {
//		Map<String, Object> resultMap = new HashMap();
//
//		try {
//			PlanDTO planDto = service.getPlan(planIdx);
//			List<PlanDetailDTO > planDetailList = service.getPlanDetail(planIdx);
//			List<PlanCommentDTO> planCommetList = service.getPlanComment(planIdx);
//			
//			service.updateHit(planIdx);
//			
//			resultMap.put("resmsg", "여행 계획 조회 성공");
//			resultMap.put("plan", planDto);
//			resultMap.put("planDetail", planDetailList);
//			resultMap.put("planComment", planCommetList);
//			resultMap.put("pgno", map.get("pgno"));
//			resultMap.put("key", map.get("key"));
//			resultMap.put("word", map.get("word"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("resmsg", "여행 계획 생성 실패");
//		}
//		return resultMap;
//	}
	
	// plan_idx로 계획 1개 얻어오기
	@GetMapping("/plan/{plan_idx}")
	public ResponseEntity<PlanDTO> getPlan(@PathVariable("plan_idx") String plan_idx){
		
		PlanDTO planDto = null;
		
		try{
			planDto = service.getPlan(Integer.parseInt(plan_idx));
		}catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<PlanDTO>(planDto, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<PlanDTO>(planDto, HttpStatus.OK);
	}
	
	// plan_idx로 여행 계획 상세 얻기(PlanDetailDTO list)
	@GetMapping("/planDetail/{plan_idx}")
	public ResponseEntity<List<PlanDetailDTO>> getPlanDetail(@PathVariable("plan_idx") String plan_idx){
		
		List<PlanDetailDTO> list = null;
		
		try {
			list = service.getPlanDetail(Integer.parseInt(plan_idx));
		}catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<List<PlanDetailDTO>>(list, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PlanDetailDTO>>(list, HttpStatus.OK);
	}
	
	
	
	/*
	@PostMapping("/plan") // 여행 계획 생성, 처음 여행 계획 생성한 사람 permission 추가
	public Map<String, Object> createPlan(@RequestBody PlanDTO planDto, @RequestParam Map<String, String> map, Model model) {
		Map<String, Object> resultMap = new HashMap();

		try {
			
			service.createPlan(planDto);
			
			int planIdx = planDto.getPlan_idx();
			int userIdx = planDto.getUser_idx();

			service.addPlanPermission(planIdx, userIdx);

			resultMap.put("resmsg", "여행 계획 생성 성공");
			resultMap.put("resdata", planDto);
			resultMap.put("pgno", map.get("pgno"));
			resultMap.put("key", map.get("key"));
			resultMap.put("word", map.get("word"));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "여행 계획 생성 실패");
		}
		return resultMap;
	}
	*/
	@PostMapping("/plan")
	public ResponseEntity<Integer> createPlan(@RequestBody PlanDTO planDto){
		int result = -1;
//		planDto.setPlan_type("어린이");
		try {
			int rowCount = service.createPlan(planDto);
			result = planDto.getPlan_idx();
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
	
	@PostMapping("/planDetail")
	public ResponseEntity<Integer> createPlanDetail(@RequestBody PlanDetailDTO planDetailDto){
		int result = -1;
		try {
			result = service.createPlanDetail(planDetailDto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result != -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Integer>(result, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/myPlan/{user_idx}")
	public ResponseEntity<List<PlanDTO>> getMyPlanList(@PathVariable("user_idx") String user_idx){
		
		List<PlanDTO> list = null;
		try {
			list = service.myPlanList(Integer.parseInt(user_idx));
		} catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<List<PlanDTO>>(list, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<PlanDTO>>(list, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{plan_idx}")
	public Map<String, Object> deletePlan(@PathVariable("plan_idx") int planIdx, @RequestParam Map<String, String> map) throws Exception {
		Map<String, Object> resultMap = new HashMap();
		
		try {
			service.deletePlan(planIdx);

			resultMap.put("resmsg", "여행 계획 삭제 성공");
			resultMap.put("pgno", map.get("pgno"));
			resultMap.put("key", map.get("key"));
			resultMap.put("word", map.get("word"));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "여행 계획 삭제 실패");
		}
		return resultMap;
	}
	
//	@PutMapping("/plan") // planDto, planDtoDetailDto 수정
//	// 매개 변수를 이렇게 전달해주는게 맞는지,,,
//	public Map<String, Object> updatePlan(@RequestBody PlanDTO planDto, @RequestBody List<PlanDetailDTO> planDetilList, @RequestParam Map<String, String> map, @RequestParam List<PlanDetailDTO> details) {
//		Map<String, Object> resultMap = new HashMap();
//		
//		try {
//			service.updatePlan(planDto);
//			
//			service.deletePlanDetail(planDto.getPlan_idx());
////			service.createPlanDetail(planDetilList);
//			resultMap.put("resmsg", "여행 계획 수정 성공");
//			resultMap.put("pgno", map.get("pgno"));
//			resultMap.put("key", map.get("key"));
//			resultMap.put("word", map.get("word"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("resmsg", "여행 계획 수정 실패");
//		}
//		return resultMap;
//	}
	
	// 계획 수정하기 : 등록 버튼 눌렀을 때도 이거 사용함 (계획 생성하러 가기 버튼 눌렀을 때 이미 생성되었기 때문)
	@PutMapping("/plan/{plan_idx}")
	public ResponseEntity<Integer> updatePlan(@RequestBody PlanDTO planDto){
		int result = -1;
		
		try {
			result = service.updatePlan(planDto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result != -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Integer>(result, HttpStatus.NO_CONTENT);
		}
	}
	
	// plan_detail 삭제하기
	@DeleteMapping("/planDetail/{plan_idx}")
	public ResponseEntity<Integer> deletePlanDetail(@PathVariable("plan_idx") int plan_idx){
		int result = -1;
		
		try {
			result = service.deletePlanDetail(plan_idx);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result != -1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Integer>(result, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/plan/{plan_idx}/{user_idx}")
	public Map<String,Object> addPlanPermission(@PathVariable("plan_idx") int planIdx, @PathVariable("user_idx") int userIdx) {
		Map<String, Object> resultMap = new HashMap();
		
		try {
			service.addPlanPermission(planIdx, userIdx);
			resultMap.put("resmsg", "계획 수정 권한 추가 성공");
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "계획 수정 권한 추가 실패");
		}
		return resultMap;
	}
	
	@DeleteMapping("/plan/{plan_idx}/{user_idx}")
	public Map<String, Object> deletePlanPermission(@PathVariable("plan_idx") int planIdx, @PathVariable("user_idx") int userIdx) {
		Map<String, Object> resultMap = new HashMap();
		
		try {
			// 여기서부터 다시 하기!
			service.deletePlanPermission(planIdx, userIdx);
			resultMap.put("resmsg", "계획 권한 추가 성공");			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "계획 권한 삭제 실패");
		}
		return resultMap;
	}
	
	@PostMapping("/plan/{plan_idx}/comment") // 계획 댓글 생성
	public Map<String, Object> createPlanComment(@RequestBody PlanCommentDTO planCommentDto, @PathVariable("plan_idx") int planIdx) {
		Map<String, Object> resultMap = new HashMap();
		
		try {
			planCommentDto.setPlan_idx(planIdx);
			
			service.createPlanComment(planCommentDto);
			resultMap.put("resmsg", "계획 댓글 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "계획 댓글 생성 실패");
		}
		
		return resultMap;
	}
	
	@PutMapping("/plan/{plan_idx}/comment")
	public Map<String, Object> updatePlanComment(@RequestBody PlanCommentDTO planCommentDto, @PathVariable("plan_idx") int planIdx) {
		Map<String, Object> resultMap = new HashMap();
		
		try {
			service.updatePlanComment(planCommentDto);
			resultMap.put("resmsg", "계획 댓글 수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "계획 댓글 수정 실패");
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/plan/{plan_idx}/comment/{comment_id}")
	public Map<String, Object> deletePlanComment(@PathVariable("comment_id") int planCommentIdx) {
		Map<String, Object> resultMap = new HashMap();
		
		try { 
			service.deletePlanComment(planCommentIdx);
			resultMap.put("resmsg", "댓글 삭제 성공");
		} catch (Exception e) {
			resultMap.put("resmsg", "댓글 삭제 실패");
		}
		
		return resultMap;
	}
	
	@PostMapping("/plan/like/{plan_idx}/{user_idx}")
	public Map<String, Object> planLikeAdd(@PathVariable("plan_idx")int planIdx, @PathVariable("user_idx") int userIdx) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			service.addLikePlan(planIdx, userIdx);
			resultMap.put("resmsg", "게시물 좋아요 성공");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "게시물 좋아요 실패");
		}
		return resultMap;
	}
	
	@DeleteMapping("/plan/like/{plan_idx}/{user_idx}")
	public Map<String, Object> planLikeDelete(@PathVariable("plan_idx")int planIdx,  @PathVariable("user_idx") int userIdx) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			service.deleteLikePlan(planIdx, userIdx);
			resultMap.put("resmsg", "게시물 좋아요 취소 성공");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resmsg", "게시물 좋아요 취소 실패");
		}
		return resultMap;
	}	
}
