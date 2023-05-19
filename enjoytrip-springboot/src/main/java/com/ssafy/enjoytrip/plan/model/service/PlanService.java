package com.ssafy.enjoytrip.plan.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.plan.model.PlanCommentDTO;
import com.ssafy.enjoytrip.plan.model.PlanDTO;
import com.ssafy.enjoytrip.plan.model.PlanDetailDTO;

public interface PlanService {
	// 여행 계획 생성
	public int createPlan(PlanDTO planDto) throws Exception;
	// 여행 계획 detail 생성
	public int createPlanDetail(PlanDetailDTO planDetailDto) throws Exception;
	// 여행 계획 수정
	public void updatePlan(PlanDTO planDto) throws Exception;
	// 여행 계획 리스트 조회
	public List<PlanDTO> listPlan(Map<String, String> param) throws Exception;
	// 여행 계획 1개 조회
	public PlanDTO getPlan(int planIdx) throws Exception;
	// 여행 계획 삭제
	public void deletePlan(int planIdx) throws Exception;
	
	// 여행 계획 권한 추가
	public void addPlanPermission(int planIdx, int userIdx) throws Exception;
	// 여행 계획 권한 삭제
	public void deletePlanPermission(int planIdx, int userIdx) throws Exception;
	
	// 여행 계획 댓글 생성
	public void createPlanComment(PlanCommentDTO planCommentDto) throws Exception;
	// 여행 계획 댓글 삭제
	public void deletePlanComment(int planCommentIdx) throws Exception;
	// 여행 계획 댓글 수정
	public void updatePlanComment(PlanCommentDTO planCommentDto) throws Exception;
	// 여행 계획 댓글 리스트 조회
	public List<PlanCommentDTO> listPlanComment(int plan_idx) throws Exception;
	// 여행 계획 조회수 증가
	public void updateHit(int planIdx) throws Exception;
	// 여행 계획 상세 불러오기
	public List<PlanDetailDTO> getPlanDetail(int planIdx)throws Exception;
	// 여행 계획 댓글 보기
	public List<PlanCommentDTO> getPlanComment(int planIdx) throws Exception;
	// 여행 계획 상세 삭제
	public void deletePlanDetail(int plan_idx)throws Exception;
	// 여행 계획 좋아요
	public void addLikePlan(int planIdx, int userIdx) throws Exception;
	// 여행 계획 좋아요 취소
	public void deleteLikePlan(int planIdx, int userIdx) throws Exception;
}
