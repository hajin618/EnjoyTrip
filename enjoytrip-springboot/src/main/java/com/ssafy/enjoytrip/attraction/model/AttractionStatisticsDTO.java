package com.ssafy.enjoytrip.attraction.model;

public class AttractionStatisticsDTO {
	private int cnt;
	private String attraction_name;
	
	@Override
	public String toString() {
		return "AttractionStatisticsDTO [cnt=" + cnt + ", attraction_name=" + attraction_name + "]";
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getAttraction_name() {
		return attraction_name;
	}

	public void setAttraction_name(String attraction_name) {
		this.attraction_name = attraction_name;
	}
	
	
}
