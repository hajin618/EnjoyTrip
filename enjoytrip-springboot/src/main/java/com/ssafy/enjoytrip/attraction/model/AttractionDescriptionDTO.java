package com.ssafy.enjoytrip.attraction.model;

public class AttractionDescriptionDTO {
	private int content_id;
	private String homepage;
	private String overview;
	private String telname;
	
	@Override
	public String toString() {
		return "AttractionDescriptionDTO [content_id=" + content_id + ", homepage=" + homepage + ", overview="
				+ overview + ", telname=" + telname + "]";
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getTelname() {
		return telname;
	}
	public void setTelname(String telname) {
		this.telname = telname;
	}
	
	
}
