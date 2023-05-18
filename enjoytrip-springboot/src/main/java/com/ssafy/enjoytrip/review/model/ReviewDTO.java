package com.ssafy.enjoytrip.review.model;

import java.util.List;

import com.ssafy.enjoytrip.user.model.UserDTO;

public class ReviewDTO {
	private int review_idx;
	private int user_idx;
	private int sido_code;
	private String review_title;
	private String review_content;
	private String review_create;
	private String review_update;
	private int review_hit;
	private String review_type;
	private UserDTO userDto;
	private List<ReviewCommentDTO> review_comment;
	private List<ReviewImageDTO> review_image;
	
	@Override
	public String toString() {
		return "ReviewDTO [review_idx=" + review_idx + ", user_idx=" + user_idx + ", sido_code=" + sido_code
				+ ", review_title=" + review_title + ", review_content=" + review_content + ", review_create="
				+ review_create + ", review_update=" + review_update + ", review_hit=" + review_hit + ", review_type="
				+ review_type + ", userDto=" + userDto + ", review_comment=" + review_comment + ", review_image="
				+ review_image + "]";
	}

	public List<ReviewCommentDTO> getReview_comment() {
		return review_comment;
	}

	public void setReview_comment(List<ReviewCommentDTO> review_comment) {
		this.review_comment = review_comment;
	}

	public List<ReviewImageDTO> getReview_image() {
		return review_image;
	}

	public void setReview_image(List<ReviewImageDTO> review_image) {
		this.review_image = review_image;
	}

	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public int getSido_code() {
		return sido_code;
	}
	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_create() {
		return review_create;
	}
	public void setReview_create(String review_create) {
		this.review_create = review_create;
	}
	public String getReview_type() {
		return review_type;
	}

	public void setReview_type(String review_type) {
		this.review_type = review_type;
	}

	public String getReview_update() {
		return review_update;
	}
	public void setReview_update(String review_update) {
		this.review_update = review_update;
	}
	public int getReview_hit() {
		return review_hit;
	}
	public void setReview_hit(int review_hit) {
		this.review_hit = review_hit;
	}
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
}
