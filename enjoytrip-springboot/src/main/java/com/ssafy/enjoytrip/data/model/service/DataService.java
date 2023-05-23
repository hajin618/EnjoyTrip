package com.ssafy.enjoytrip.data.model.service;

import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;
import com.ssafy.enjoytrip.data.model.MissingChildDTO;

public interface DataService {
	// 어린이 놀이시설 데이터 삽입
	public void insertChildAttraction(ChildAttractionDTO childAttractionDto) throws Exception;
	
	// 구군 코드 찾기
	public int getGugunCode(int sido_code, String gugun_name) throws Exception;
	
	// 실종 아동 정보
	public MissingChildDTO getMissingChild(int child_idx) throws Exception;
}
