package com.ssafy.enjoytrip.data.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;
import com.ssafy.enjoytrip.data.model.MissingChildDTO;

@Mapper
public interface DataMapper {
	// 어린이 놀이시설 데이터 삽입
	public void insertChildAttraction(ChildAttractionDTO childAttractionDto) throws Exception;
	
	// 실종 아동 데이터 삽입
	public void insertMissingChild(MissingChildDTO missingChildDto) throws Exception;
}
