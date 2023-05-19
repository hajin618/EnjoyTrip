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
	private int plan_like;
	private int plan_hit;
	private String plan_content;
	private String plan_type;
	
	@Override
	public String toString() {
		return "PlanDTO [plan_idx=" + plan_idx + ", user_idx=" + user_idx + ", plan_title=" + plan_title
				+ ", plan_create=" + plan_create + ", plan_update=" + plan_update + ", plan_like=" + plan_like
				+ ", plan_hit=" + plan_hit + ", plan_content=" + plan_content + ", plan_type=" + plan_type + "]";
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

	public String getPlan_content() {
		return plan_content;
	}

	public void setPlan_content(String plan_content) {
		this.plan_content = plan_content;
	}

	public String getPlan_type() {
		return plan_type;
	}

	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}
	
	
}
