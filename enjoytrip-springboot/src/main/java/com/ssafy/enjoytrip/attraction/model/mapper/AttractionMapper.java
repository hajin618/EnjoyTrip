package com.ssafy.enjoytrip.attraction.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.AttractionInfoDTO;
import com.ssafy.enjoytrip.attraction.model.AttractionSelectDTO;
import com.ssafy.enjoytrip.attraction.model.GugunDTO;
import com.ssafy.enjoytrip.attraction.model.SidoDTO;
import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;

@Mapper
public interface AttractionMapper {
	// 시도 검색
	public List<SidoDTO> searchSido(Map<String, String> param) throws Exception;

	// 구군 검색
	public List<GugunDTO> searchGugun(int sido_code) throws Exception;

	// 여행지 검색
	public List<AttractionInfoDTO> searchAttraction(AttractionSelectDTO attractionSelectDTO) throws Exception;
	
	// 아이 여행지 검색
	public List<ChildAttractionDTO> searchChildAttraction(AttractionSelectDTO attractionSelectDTO) throws Exception;
	
	// 여행지 검색 (content_id)
	public List<AttractionInfoDTO> searchAttractionById(int content_id) throws Exception;
	
	// 아이 여행지 검색 (attraction_idx)
	public List<ChildAttractionDTO> searchChildAttractionById(int attraction_idx) throws Exception;
}
