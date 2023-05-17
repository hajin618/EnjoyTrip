package com.ssafy.enjoytrip.data.model;

public class ChildAttractionDTO {
	
	int attraction_idx;
	String attraction_name;
	String category1;
	String category2;
	String category3;
	String latitude;
	String longitude;
	String road_address;
	String address;
	String phone_number;
	String homepage;
	String closed_day;
	String operating_time;
	String nursing_room;
	String stroller;
	String kid_zone;
	
	@Override
	public String toString() {
		return "ChildAttractionDTO [attraction_idx=" + attraction_idx + ", attraction_name=" + attraction_name
				+ ", category1=" + category1 + ", category2=" + category2 + ", category3=" + category3 + ", latitude="
				+ latitude + ", longitude=" + longitude + ", road_address=" + road_address + ", address=" + address
				+ ", phone_number=" + phone_number + ", homepage=" + homepage + ", closed_day=" + closed_day
				+ ", operating_time=" + operating_time + ", nursing_room=" + nursing_room + ", stroller=" + stroller
				+ ", kid_zone=" + kid_zone + "]";
	}

	public int getAttraction_idx() {
		return attraction_idx;
	}

	public void setAttraction_idx(int attraction_idx) {
		this.attraction_idx = attraction_idx;
	}

	public String getAttraction_name() {
		return attraction_name;
	}

	public void setAttraction_name(String attraction_name) {
		this.attraction_name = attraction_name;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRoad_address() {
		return road_address;
	}

	public void setRoad_address(String road_address) {
		this.road_address = road_address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getClosed_day() {
		return closed_day;
	}

	public void setClosed_day(String closed_day) {
		this.closed_day = closed_day;
	}

	public String getOperating_time() {
		return operating_time;
	}

	public void setOperating_time(String operating_time) {
		this.operating_time = operating_time;
	}

	public String getNursing_room() {
		return nursing_room;
	}

	public void setNursing_room(String nursing_room) {
		this.nursing_room = nursing_room;
	}

	public String getStroller() {
		return stroller;
	}

	public void setStroller(String stroller) {
		this.stroller = stroller;
	}

	public String getKid_zone() {
		return kid_zone;
	}

	public void setKid_zone(String kid_zone) {
		this.kid_zone = kid_zone;
	}
	
	
}
