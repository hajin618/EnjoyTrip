package com.ssafy.enjoytrip.plan.model;

public class PlanCommentDTO {
	private int plan_comment_idx;
	private int plan_idx;
	private int user_idx;
	private String plan_comment_create;
	private String plan_comment_update;
	private String plan_comment_content;
	public int getPlan_comment_idx() {
		return plan_comment_idx;
	}
	public void setPlan_comment_idx(int plan_comment_idx) {
		this.plan_comment_idx = plan_comment_idx;
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
	public String getPlan_comment_create() {
		return plan_comment_create;
	}
	public void setPlan_comment_create(String plan_comment_create) {
		this.plan_comment_create = plan_comment_create;
	}
	public String getPlan_comment_update() {
		return plan_comment_update;
	}
	public void setPlan_comment_update(String plan_comment_update) {
		this.plan_comment_update = plan_comment_update;
	}
	public String getPlan_comment_content() {
		return plan_comment_content;
	}
	public void setPlan_comment_content(String plan_comment_content) {
		this.plan_comment_content = plan_comment_content;
	}
	@Override
	public String toString() {
		return "PlanCommentDTO [plan_comment_idx=" + plan_comment_idx + ", plan_idx=" + plan_idx + ", user_idx="
				+ user_idx + ", plan_comment_create=" + plan_comment_create + ", plan_comment_update="
				+ plan_comment_update + ", plan_comment_content=" + plan_comment_content + "]";
	}
}
