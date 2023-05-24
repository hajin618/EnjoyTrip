package com.ssafy.enjoytrip.attraction.model;

public class SidoStatisticsDTO {
	private int cnt;
	private String sido_name;
	
	@Override
	public String toString() {
		return "SidoStatisticsDTO [cnt=" + cnt + ", sido_name=" + sido_name + "]";
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getSido_name() {
		return sido_name;
	}

	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}
	
}
