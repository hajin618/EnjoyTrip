package com.ssafy.enjoytrip.attraction.model;

public class StatisticsDataDTO {
	private int statistics_idx;
	private int sido_code;
	private String sido_name;
	private String attraction_name;
	
	@Override
	public String toString() {
		return "StatisticsDataDTO [statistics_idx=" + statistics_idx + ", sido_code=" + sido_code + ", sido_name="
				+ sido_name + ", attraction_name=" + attraction_name + "]";
	}

	public int getStatistics_idx() {
		return statistics_idx;
	}

	public void setStatistics_idx(int statistics_idx) {
		this.statistics_idx = statistics_idx;
	}

	public int getSido_code() {
		return sido_code;
	}

	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}

	public String getSido_name() {
		return sido_name;
	}

	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}

	public String getAttraction_name() {
		return attraction_name;
	}

	public void setAttraction_name(String attraction_name) {
		this.attraction_name = attraction_name;
	}
	
}
