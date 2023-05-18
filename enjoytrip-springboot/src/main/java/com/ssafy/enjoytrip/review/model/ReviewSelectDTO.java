package com.ssafy.enjoytrip.review.model;

public class ReviewSelectDTO {
	int sido_code;
	String review_type;
	
	@Override
	public String toString() {
		return "ReviewSelectDTO [sido_code=" + sido_code + ", review_type=" + review_type + "]";
	}
	public int getSido_code() {
		return sido_code;
	}
	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}
	public String getReview_type() {
		return review_type;
	}
	public void setReview_type(String review_type) {
		this.review_type = review_type;
	}
	

}
