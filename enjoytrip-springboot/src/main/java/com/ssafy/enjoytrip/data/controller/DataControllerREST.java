package com.ssafy.enjoytrip.data.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.data.model.ChildAttractionDTO;
import com.ssafy.enjoytrip.data.model.MissingChildDTO;
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
		StringTokenizer st;
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
					String free_parking = (String)object.get("무료주차 가능여부");
					String paid_parking = (String)object.get("유료주차 가능여부");
					String sido_name = (String)object.get("시도 명칭");
					String gugun_name_origin = (String)object.get("시군구 명칭");
					String admission_fee = (String)object.get("입장료 유무 여부");
					int sido_code = 0;
					int gugun_code = 0;
					String gugun_name = null;
					
					// sido_code 구하자
					if(sido_name.equals("서울특별시"))
						sido_code = 1;
					else if(sido_name.equals("인천광역시"))
						sido_code = 2;
					else if(sido_name.equals("대전광역시"))
						sido_code = 3;
					else if(sido_name.equals("대구광역시"))
						sido_code = 4;
					else if(sido_name.equals("광주광역시"))
						sido_code = 5;
					else if(sido_name.equals("부산광역시"))
						sido_code = 6;
					else if(sido_name.equals("울산광역시"))
						sido_code = 7;
					else if(sido_name.equals("세종특별자치시"))
						sido_code = 8;
					else if(sido_name.equals("경기도"))
						sido_code = 31;
					else if(sido_name.equals("강원도"))
						sido_code = 32;
					else if(sido_name.equals("충청북도"))
						sido_code = 33;
					else if(sido_name.equals("충청남도"))
						sido_code = 34;
					else if(sido_name.equals("경상북도"))
						sido_code = 35;
					else if(sido_name.equals("경상남도"))
						sido_code = 36;
					else if(sido_name.equals("전라북도"))
						sido_code = 37;
					else if(sido_name.equals("전라남도"))
						sido_code = 38;
					else if(sido_name.equals("제주특별자치도"))
						sido_code = 39;
					
					// gugun_code 구하자 (창원시 마산회원구 처리 : StringTokenizer // 세종특별자치시 처리 : 구군 이름도 세종특별자치시)
					if(gugun_name_origin == null) {
						gugun_name = "세종특별자치시";
						gugun_code = 1;
					}
					else {
						st = new StringTokenizer(gugun_name_origin);
						gugun_name = st.nextToken();
						gugun_code = service.getGugunCode(sido_code, gugun_name);
					}
					
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
					childAttractionDto.setFree_parking(free_parking);
					childAttractionDto.setPaid_parking(paid_parking);
					childAttractionDto.setSido_name(sido_name);
					childAttractionDto.setSido_code(sido_code);
					childAttractionDto.setGugun_name(gugun_name);
					childAttractionDto.setGugun_code(gugun_code);
					childAttractionDto.setAdmission_fee(admission_fee);
					
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
	
	
	@GetMapping("/mChild/{child_idx}")
	public ResponseEntity<MissingChildDTO> getChildInfo(@PathVariable("child_idx") String child_idx){
		
		MissingChildDTO missingChildDto = null;
		
		try {
			missingChildDto = service.getMissingChild(Integer.parseInt(child_idx));
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<MissingChildDTO>(missingChildDto, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<MissingChildDTO>(missingChildDto, HttpStatus.OK);
	}
	
}
