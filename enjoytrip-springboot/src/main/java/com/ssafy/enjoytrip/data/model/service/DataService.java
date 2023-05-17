package com.ssafy.enjoytrip.data.model.service;

import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;

public interface DataService {
	// 어린이 놀이시설 데이터 삽입
	public void insertChildAttraction(ChildAttractionDTO childAttractionDto) throws Exception;
}
