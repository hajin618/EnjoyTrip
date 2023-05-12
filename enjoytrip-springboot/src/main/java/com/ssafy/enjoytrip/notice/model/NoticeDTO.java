package com.ssafy.enjoytrip.notice.model;

import com.ssafy.enjoytrip.user.model.UserDTO;

import lombok.Data;


public class NoticeDTO {
	int notice_idx;
	int user_idx;
	String notice_title;
	String notice_content;
	String notice_create;
	String notice_update;
	int notice_hit;
	UserDTO userDto;
	
	@Override
	public String toString() {
		return "NoticeDTO [notice_idx=" + notice_idx + ", user_idx=" + user_idx + ", notice_title=" + notice_title
				+ ", notice_content=" + notice_content + ", notice_create=" + notice_create + ", notice_update="
				+ notice_update + ", notice_hit=" + notice_hit + ", userDto=" + userDto + "]";
	}
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getNotice_create() {
		return notice_create;
	}
	public void setNotice_create(String notice_create) {
		this.notice_create = notice_create;
	}
	public String getNotice_update() {
		return notice_update;
	}
	public void setNotice_update(String notice_update) {
		this.notice_update = notice_update;
	}
	public int getNotice_hit() {
		return notice_hit;
	}
	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}
	
	
}
