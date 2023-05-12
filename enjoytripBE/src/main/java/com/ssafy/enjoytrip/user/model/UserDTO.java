package com.ssafy.enjoytrip.user.model;

public class UserDTO {
	private int user_idx;
	private String user_name;
	private String user_id;
	private String user_pwd;
	private String user_email;
	private char is_admin;
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public char getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(char is_admin) {
		this.is_admin = is_admin;
	}
	@Override
	public String toString() {
		return "UserDTO [user_idx=" + user_idx + ", user_name=" + user_name + ", user_id=" + user_id + ", user_pwd="
				+ user_pwd + ", user_email=" + user_email + ", is_admin=" + is_admin + "]";
	}
}
