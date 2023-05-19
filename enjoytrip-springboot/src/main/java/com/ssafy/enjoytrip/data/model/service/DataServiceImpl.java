package com.ssafy.enjoytrip.data.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;
import com.ssafy.enjoytrip.data.model.mapper.DataMapper;

@Service("DataServiceImpl")
public class DataServiceImpl implements DataService{
	
	private DataMapper dataMapper;
	
	public DataServiceImpl(DataMapper dataMapper) {
		super();
		this.dataMapper = dataMapper;
	}

	@Override
	public void insertChildAttraction(ChildAttractionDTO childAttractionDto) throws Exception {
		dataMapper.insertChildAttraction(childAttractionDto);
	}

	@Override
	public int getGugunCode(int sido_code, String gugun_name) throws Exception {
		return dataMapper.getGugunCode(sido_code, gugun_name);
	}

}
