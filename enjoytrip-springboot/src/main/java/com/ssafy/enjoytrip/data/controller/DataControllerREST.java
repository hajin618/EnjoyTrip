package com.ssafy.enjoytrip.data.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;
import com.ssafy.enjoytrip.data.model.service.DataService;

@RestController
@RequestMapping("/api")
public class DataControllerREST {

	@Autowired
	@Qualifier("DataServiceImpl")
	private DataService service;
	
	public DataControllerREST(DataService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/childAttraction")
	public Map<String, Object> putChildAttraction(){
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
		
			for(int idx=1; idx<=86; idx++) {
				String result = "";
			
				String urlString = "https://api.odcloud.kr/api/15111391/v1/uddi:19c0c9ab-ac89-486b-b4b8-b026506dc3fa?page="+idx+"&perPage=100&serviceKey=gJGKEIYvy7ORL9tFlhEUAn04VevGXopziJ%2FcNbZYf4I0ufqTglOz2GMvl3uoBz0V1%2FXKhTXN9Svvd4CF%2FiA28A%3D%3D";
				//String urlString = "https://api.odcloud.kr/api/15111391/v1/uddi:19c0c9ab-ac89-486b-b4b8-b026506dc3fa?page="+1+"&perPage=2&serviceKey=gJGKEIYvy7ORL9tFlhEUAn04VevGXopziJ%2FcNbZYf4I0ufqTglOz2GMvl3uoBz0V1%2FXKhTXN9Svvd4CF%2FiA28A%3D%3D";

				URL url = new URL(urlString);
				HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
				
				urlConnection.setRequestMethod("GET");
				urlConnection.setRequestProperty("Content-type", "application/json");
				
				BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
				result = bf.readLine();
				
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = new JSONObject();
				jsonObject = (JSONObject)jsonParser.parse(result);
				JSONArray jsonArray = (JSONArray)jsonObject.get("data");
				
				for(int i=0; i<jsonArray.size(); i++) {
					ChildAttractionDTO childAttractionDto = new ChildAttractionDTO();
					JSONObject object = (JSONObject)jsonArray.get(i);
					
					String attraction_name = (String)object.get("시설명");
					String category1 = (String)object.get("카테고리1");
					String category2 = (String)object.get("카테고리2");
					String category3 = (String)object.get("카테고리3");
					String latitude = (String)object.get("위도");
					String longitude = (String)object.get("경도");
					String road_address = (String)object.get("도로명주소");
					String address = (String)object.get("지번주소");
					String phone_number = (String)object.get("전화번호");
					String homepage = (String)object.get("홈페이지");
					String closed_day = (String)object.get("휴무일");
					String operating_time = (String)object.get("운영시간");
					String nursing_room = (String)object.get("수유실 보유 여부");
					String stroller = (String)object.get("유모차 대여 여부");
					String kid_zone = (String)object.get("키즈존 여부");
					
					childAttractionDto.setAttraction_name(attraction_name);
					childAttractionDto.setCategory1(category1);
					childAttractionDto.setCategory2(category2);
					childAttractionDto.setCategory3(category3);
					childAttractionDto.setLatitude(latitude);
					childAttractionDto.setLongitude(longitude);
					childAttractionDto.setRoad_address(road_address);
					childAttractionDto.setAddress(address);
					childAttractionDto.setPhone_number(phone_number);
					childAttractionDto.setHomepage(homepage);
					childAttractionDto.setClosed_day(closed_day);
					childAttractionDto.setOperating_time(operating_time);
					childAttractionDto.setNursing_room(nursing_room);
					childAttractionDto.setStroller(stroller);
					childAttractionDto.setKid_zone(kid_zone);
					
					service.insertChildAttraction(childAttractionDto);
				}
				resultMap.put("isSuccess", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("isSuccess", "false");
		}
		
		return resultMap;
	}	
	
	
}
