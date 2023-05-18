package com.ssafy.enjoytrip.attraction.model;

public class AttractionSelectDTO {   
    int sido_code;
    int gugun_code;
    int content_type_id;
    String searchWord;
   
	@Override
	public String toString() {
		return "AttractionSelectDTO [sido_code=" + sido_code + ", gugun_code=" + gugun_code + ", content_type_id="
				+ content_type_id + ", searchWord=" + searchWord + "]";
	}

	public int getSido_code() {
		return sido_code;
	}

	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}

	public int getGugun_code() {
		return gugun_code;
	}

	public void setGugun_code(int gugun_code) {
		this.gugun_code = gugun_code;
	}

	public int getContent_type_id() {
		return content_type_id;
	}

	public void setContent_type_id(int content_type_id) {
		this.content_type_id = content_type_id;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	
}
