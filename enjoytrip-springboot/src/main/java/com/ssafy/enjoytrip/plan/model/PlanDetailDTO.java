package com.ssafy.enjoytrip.plan.model;

public class PlanDetailDTO {
	private int plan_idx;
	private int content_id;
	private int detail_order;
	
	@Override
	public String toString() {
		return "PlanDetailDTO [plan_idx=" + plan_idx + ", content_id=" + content_id + ", detail_order=" + detail_order
				+ "]";
	}

	public int getPlan_idx() {
		return plan_idx;
	}

	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public int getDetail_order() {
		return detail_order;
	}

	public void setDetail_order(int detail_order) {
		this.detail_order = detail_order;
	}
	
	
	

}
