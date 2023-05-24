package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.AttractionDescriptionDTO;
import com.ssafy.enjoytrip.attraction.model.AttractionInfoDTO;
import com.ssafy.enjoytrip.attraction.model.AttractionSelectDTO;
import com.ssafy.enjoytrip.attraction.model.GugunDTO;
import com.ssafy.enjoytrip.attraction.model.SidoDTO;
import com.ssafy.enjoytrip.attraction.model.StatisticsDataDTO;
import com.ssafy.enjoytrip.attraction.model.mapper.AttractionMapper;
import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;

@Service("AttractionServiceImpl")
public class AttractionServiceImpl implements AttractionService{
	
	private AttractionMapper attractionMapper;
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}
	
	@Override
	public List<AttractionInfoDTO> searchAttraction(AttractionSelectDTO attractionSelectDTO) throws Exception {
		return attractionMapper.searchAttraction(attractionSelectDTO);
	}

	@Override
	public List<SidoDTO> searchSido(Map<String, String> param) throws Exception {
		return attractionMapper.searchSido(param);
	}

	@Override
	public List<GugunDTO> searchGugun(int sido_code) throws Exception {
		return attractionMapper.searchGugun(sido_code);
	}

	@Override
	public AttractionInfoDTO searchAttractionById(int content_id) throws Exception {
		return attractionMapper.searchAttractionById(content_id);
	}

	@Override
	public List<ChildAttractionDTO> searchChildAttraction(AttractionSelectDTO attractionSelectDTO) throws Exception {
		return attractionMapper.searchChildAttraction(attractionSelectDTO);
	}

	@Override
	public ChildAttractionDTO searchChildAttractionById(int attraction_idx) throws Exception {
		return attractionMapper.searchChildAttractionById(attraction_idx);
	}

	@Override
	public AttractionDescriptionDTO searchAttractionDesc(int content_id) throws Exception {
		return attractionMapper.searchAttractionDesc(content_id);
	}

	@Override
	public int insertStatisticsData(StatisticsDataDTO statisticsDataDto) throws Exception {
		return attractionMapper.insertStatisticsData(statisticsDataDto);
	}

}
