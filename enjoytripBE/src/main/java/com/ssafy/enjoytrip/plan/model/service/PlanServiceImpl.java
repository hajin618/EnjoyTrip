package com.ssafy.enjoytrip.plan.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.plan.model.PlanCommentDTO;
import com.ssafy.enjoytrip.plan.model.PlanDTO;
import com.ssafy.enjoytrip.plan.model.PlanDetailDTO;
import com.ssafy.enjoytrip.plan.model.mapper.PlanMapper;

@Service("PlanServiceImpl")
public class PlanServiceImpl implements PlanService {
	
	private PlanMapper planMapper;

	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	public void createPlan(PlanDTO planDto) throws Exception {
		System.out.println("service: " + planDto.toString());
		planMapper.createPlan(planDto);
		
		System.out.println(planDto.getPlan_idx());
		List<PlanDetailDTO> planDetailList = planDto.getPlanDetail();
		if(planDetailList != null && !planDetailList.isEmpty()) {
			planMapper.createPlanDetail(planDto);
		}
	}

	@Override
	public void updatePlan(PlanDTO planDto) throws Exception {
		planMapper.updatePlan(planDto);
	}

	@Override
	public List<PlanDTO> listPlan(Map<String, String> param) throws Exception {
		return planMapper.listPlan(param);
	}

	@Override
	public PlanDTO getPlan(int planIdx) throws Exception {
		return planMapper.getPlan(planIdx);
	}

	@Override
	public void deletePlan(int planIdx) throws Exception {
		System.out.println("mapper" + planIdx);
		planMapper.deletePlan(planIdx);
	}

	@Override
	public void addPlanPermission(@Param("plan_idx")int plan_idx, @Param("user_idx")int user_idx) throws Exception {
		planMapper.addPlanPermission(plan_idx, user_idx);
	}

	@Override
	public void deletePlanPermission(@Param("plan_idx")int plan_idx, @Param("user_idx") int user_idx) throws Exception {
		planMapper.deletePlanPermission(plan_idx, user_idx);
	}

	@Override
	public void createPlanComment(PlanCommentDTO planCommentDto) throws Exception {
		planMapper.createPlanComment(planCommentDto);		
	}

	@Override
	public void deletePlanComment(int planCommentIdx) throws Exception {
		planMapper.deletePlanComment(planCommentIdx);
	}

	@Override
	public List<PlanCommentDTO> listPlanComment(int plan_idx) throws Exception {
		return planMapper.listPlanComment(plan_idx);
	}

	@Override
	public void updateHit(int planIdx) throws Exception {
		planMapper.updateHit(planIdx);
	}

	@Override
	public List<PlanDetailDTO> getPlanDetail(int planIdx) throws Exception {
		return planMapper.getPlanDetail(planIdx);
	}

	@Override
	public List<PlanCommentDTO> getPlanComment(int planIdx) throws Exception {
		return planMapper.getPlanComment(planIdx);
	}

	@Override
	public void deletePlanDetail(int planIdx) throws Exception {
		planMapper.deletePlanDetail(planIdx);
	}

	@Override
	public void updatePlanComment(PlanCommentDTO planCommentDto) throws Exception {
		planMapper.updatePlanComment(planCommentDto);
	}

	@Override
	public void addLikePlan(@Param("plan_idx")int planIdx, @Param("user_idx")int userIdx) throws Exception {
		System.out.println(planIdx + " " + userIdx);
		planMapper.addLikePlan(planIdx, userIdx);
	}

	@Override
	public void deleteLikePlan(@Param("plan_idx")int planIdx, @Param("user_idx")int userIdx) throws Exception {
		planMapper.deleteLikePlan(planIdx, userIdx);
	}
}
