package com.ssafy.enjoytrip.review.model;

public class ReviewCommentDTO {
	private int review_idx;
	private int comment_idx;
	private int user_idx;
	private String user_name;
	private String review_comment_create;
	private String review_comment_update;
	private String review_comment_content;
	
	@Override
	public String toString() {
		return "ReviewCommentDTO [review_idx=" + review_idx + ", comment_idx=" + comment_idx + ", user_idx=" + user_idx
				+ ", user_name=" + user_name + ", review_comment_create=" + review_comment_create
				+ ", review_comment_update=" + review_comment_update + ", review_comment_content="
				+ review_comment_content + "]";
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getReview_comment_create() {
		return review_comment_create;
	}
	public void setReview_comment_create(String review_comment_create) {
		this.review_comment_create = review_comment_create;
	}
	public String getReview_comment_update() {
		return review_comment_update;
	}
	public void setReview_comment_update(String review_comment_update) {
		this.review_comment_update = review_comment_update;
	}
	public String getReview_comment_content() {
		return review_comment_content;
	}
	public void setReview_comment_content(String review_comment_content) {
		this.review_comment_content = review_comment_content;
	}
}
