package com.ssafy.enjoytrip.plan.model;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.enjoytrip.user.model.UserDTO;

public class PlanDTO {
	private int plan_idx;
	private int user_idx;
	private String plan_title;
	private String plan_create;
	private String plan_update;
	private String plan_start;
	private String plan_end;
	private int plan_like;
	private int plan_hit;
	private UserDTO userDto;
	private List<PlanDetailDTO> planDetail;
	private List<PlanCommentDTO> planCommentDto;
	
	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}

	public List<PlanDetailDTO> getPlanDetail() {
		return planDetail;
	}
	
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getPlan_title() {
		return plan_title;
	}
	public void setPlan_title(String plan_title) {
		this.plan_title = plan_title;
	}
	public String getPlan_create() {
		return plan_create;
	}
	public void setPlan_create(String plan_create) {
		this.plan_create = plan_create;
	}
	public String getPlan_update() {
		return plan_update;
	}
	public void setPlan_update(String plan_update) {
		this.plan_update = plan_update;
	}
	public String getPlan_start() {
		return plan_start;
	}
	public void setPlan_start(String plan_start) {
		this.plan_start = plan_start;
	}
	public String getPlan_end() {
		return plan_end;
	}
	public void setPlan_end(String plan_end) {
		this.plan_end = plan_end;
	}
	public int getPlan_like() {
		return plan_like;
	}
	public void setPlan_like(int plan_like) {
		this.plan_like = plan_like;
	}
	public int getPlan_hit() {
		return plan_hit;
	}
	public void setPlan_hit(int plan_hit) {
		this.plan_hit = plan_hit;
	}
	
	public void setPlanDetail(List<PlanDetailDTO> planDetail) {
		this.planDetail = planDetail;
	}
	
	public List<PlanCommentDTO> getPlanCommentDto() {
		return planCommentDto;
	}

	public void setPlanCommentDto(List<PlanCommentDTO> planCommentDto) {
		this.planCommentDto = planCommentDto;
	}
	
	@Override
	public String toString() {
		return "PlanDTO [plan_idx=" + plan_idx + ", user_idx=" + user_idx + ", plan_title=" + plan_title
				+ ", plan_create=" + plan_create + ", plan_update=" + plan_update + ", plan_start=" + plan_start
				+ ", plan_end=" + plan_end + ", plan_like=" + plan_like + ", plan_hit=" + plan_hit + "]";
	}
}
