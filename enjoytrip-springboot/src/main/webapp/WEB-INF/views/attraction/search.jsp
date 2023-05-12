<!--  	"지역별여행지" 네비게이션 화면: KakaoMap API를 이용한 지역별 관광지 졍보 가져오기 화면 
		관광지 정보 DB 구현 -> SQL Dump 파일 수록 (Dump20230324.sql)
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
  	 <%@include file="/include/head.jsp" %> 
  	 <!-- 공공데이터 포털 접근 Encoding key: 선택한 도시 정보에 대한 구군 정보를 가져오기 위한 목적으로 활용 -->
  	 <c:set var="serviceKey" value="32LhEcAt92tVnlXIsGrJ6fSn2BqC1XtzUqxU432qhzk3Lu8%2BU5TEwxJTzLsAW7xmu3JCvM3CfPxBYO7nu82Pqg%3D%3D"/>
  <body>
  	<%@include file="/include/header.jsp" %> 
    

    <!-- 중앙 content start -->
    <div class="container">
		<div style="height: 70px"></div>
		
		<div class="row">
	        <!-- 게시판 start -->
	        <div class="container-fluid p-5">
            	<h2 style="text-align: center">여행지 검색하기</h2>  <!-- Header Name -->
          	</div>
          	
          
      		<div class="col-md-12">
        		<div class="alert alert-primary mt-3 text-center fw-bold" role="alert">  <!-- 중앙 Label -->
          			전국 관광지 정보
        		</div>
       
		        <!-- 관광지 검색 Select Form start -->
				<form id="form-search" method="POST" action="" class="d-flex my-3" role="search" style="padding-bottom:10px ;">
		         	<select id="search-city" class="form-select me-2">
		            	<option value="0" selected>검색 할 지역 선택</option>
		            	<!--	JSTL 적용: DB에서 도시정보를 가져와서 HTML로 표시 (SelectBox에 도시 정보 항목들이 Update) 
		            			sidoCode: 도시 고유 코드번호,  sidoName: 도시 이름
		            	-->
		            	<c:forEach var="city" items="${cities}" >
		            		<option value="${city.sidoCode}">${city.sidoName}</option>
		            	</c:forEach>
		          	</select>
		          
		          	<!-- 도시 정보를 선택하면 JS로 이벤트 처리 발생: 해당 도시에 대한 구군 정보를 가져와서 Update(공공데이터 API 이용) -->
					<select id="search-gugun" class="form-select me-2">
						<option value="0" selected>구군 항목</option>
					</select>
		          
					<!--  관광지 세부 선택 항목  -->
					<select id="search-content-id" class="form-select me-2">
						<option value="0" selected>관광지 유형</option>
			            <option value="12">관광지</option>
			            <option value="14">문화시설</option>
			            <option value="15">축제공연행사</option>
			            <option value="25">여행코스</option>
			            <option value="28">레포츠</option>
			            <option value="32">숙박</option>
			            <option value="38">쇼핑</option>
			            <option value="39">음식점</option>
					</select>
		
		          	<button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
				</form>
		        <!-- 관광지 검색 Select Form End -->
		        
		        
		        <!-- kakaoMap API  start  -->
		        <div id="map" class="mt-3" style="width: 100%; height: 400px;" >
		        	<!-- 	KakaoAPI 활용을 위한 appKey Setting.
		        			★ root 서버 주소 세팅 필요. (127.0.0.1:8088, 8081, 8080 설정? 한 상태로 마지막 확인
		        	 -->
		        	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8820d3277f771b41fa361424002a1649"></script>
		        	
		        	<!-- 카카오맵 API 초기 화면 보여주기  -->
		        	<script>
		        	// 카카오지도
		            var mapContainer = document.getElementById("map"), // 지도를 표시할 div
		              mapOption = {
		                center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
		                level: 5, // 지도의 확대 레벨
		              };
		
		            // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		            var map = new kakao.maps.Map(mapContainer, mapOption);
		        	</script>
		        	
		        	<!-- 검색 이벤트가 발생해서 DB에서 관광지 정보를 가져왔다면 session의 attrinfo에 값이 담겨진다. 그 때 출력. -->
		        	<c:if test="${not empty attrinfo}">
		        		<script>
			        		// 마커 이미지 경로
			        	    var Simg = "./assets/img/maker/Simg.png";  // 쇼핑
			        		var Cimg = "./assets/img/maker/Cimg.png";  // 문화
			        		var Eimg = "./assets/img/maker/Eimg.png";  // 행사
			        		var Fimg = "./assets/img/maker/Fimg.png";  // 음식 
			        		var Pimg = "./assets/img/maker/Pimg.png";  // 숙박
			        		var Rimg = "./assets/img/maker/Rimg.png";  // 레포츠
			        		var Timg = "./assets/img/maker/Timg.png";  // 여행
		        			var pos = new kakao.maps.LatLng(${attrinfo[0].latitude}, ${attrinfo[0].longitude});
		        			map.setCenter(pos);
		        		</script>
		        			
		        		<c:forEach var="position" items="${attrinfo}">
		        			<script>
		        				var imageSize = new kakao.maps.Size(24,35);
		        				var imageSrc = '';
		        				if (${position.contentId} == 12) {     //관광지(여행)
		        					imageSrc = Timg;
		        				}
		        				else if (${position.contentId} == 14){ //문화
		        					imageSrc = Cimg;
		        				}
		        				else if (${position.contentId} == 15){ //축제공연행사
		        					imageSrc = Eimg;
		        				}
		        				else if (${position.contentId} == 28){ //레포츠
		        					imageSrc = Rimg;
		        				}
		        				else if (${position.contentId} == 32){ //숙박
		        					imageSrc = Pimg;
		        				}
		        				else if (${position.contentId} == 38){ //쇼핑
		        					imageSrc = Simg;
		        				}
		        				else if (${position.contentId} == 39){ //음식점
		        					imageSrc = Fimg;
		        				}
		        				else {
		        					imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
		        				}
		        				
		        				var makerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
		        				
		        				//마커 생성
		        				var marker = new kakao.maps.Marker({
							        map: map, // 마커를 표시할 지도
							        position: new kakao.maps.LatLng(${position.latitude}, ${position.longitude}), // 마커를 표시할 위치
							        title : "${position.title}", // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
							        image : makerImage // 마커 이미지 
							    });
		        			</script>
		        		</c:forEach>
		        	</c:if>
		        </div>
		        <!-- kakaoMap API  end -->
		        
		        <!--  하단 검색한 관광지 정보 리스트로 출력 -->
		        <div class="row" style="padding-top: 30px;">
					<table class="table table-striped">
						<thead>
		              		<tr>
				                <th>대표이미지</th>
				                <th>관광지명</th>
				                <th>주소</th>
				                <th>위도</th>
				                <th>경도</th>
		              		</tr>
		            	</thead>
		            	<tbody id="trip-list"></tbody>
		            
		            	<!-- DB에서 가져온 관광지 정보는 attrinfo에 담겨져 있으므로 이를 foreach로 꺼내와 출력 -->
			            <c:forEach var="attr" items="${attrinfo}">
			            	<tr onclick="moveCenter(${attr.latitude}, ${attr.longitude});">
			            		<td><img src="${attr.imgUrl}" width="100px"></td>
			            		<td>${attr.title}</td>
			            		<td>${attr.address}</td>
			            		<td>${attr.latitude}</td>
			            		<td>${attr.longitude}</td>
			          		</tr>
						</c:forEach>
					</table>
				</div>
		        <!-- 관광지 검색 결과 리스트 end -->
			</div>
		</div>  
	</div>
	<!-- 중앙 content end -->   
  

  	<!-- 하단 footer start -->
	<footer class="section-content p-4 mt-3 container-fluid">
      <%@include file="/include/footer.jsp" %>
    </footer>
    <!-- 하단 footer end -->
    
    
    
    
    
</body>
<script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
></script>
 
<script>
		//시 정보 선택 시 발생하는 구군 항목 업데이트
	document.querySelector("#search-city").addEventListener("change", function () {
	  	   let seleted_code = this[this.selectedIndex].value;
	  	   if (seleted_code) {
	  	       getRegionDetail(seleted_code);
	  	   }
	  });

	// "도시" 정보를 선택하면 이벤트로 발생하는 "지역구" 정보 가져오기
	function getRegionDetail(data) {
		let Region_Code_T10_2 = "https://apis.data.go.kr/B551011/KorService1/areaCode1?"
          	+ "serviceKey=${serviceKey}"
          	+ "&numOfRows=100"
          	+ "&MobileOS=ETC"
          	+ "&MobileApp=AppTest"
          	+ "&_type=json"
          	+ "&areaCode=" + data;

      	fetch(Region_Code_T10_2).then((response) => response.json())
          .then((text) => {
              let RDetails = text.response.body.items.item;
              let TagSet = "";
              let Select_div = document.querySelector("#search-gugun");

              if (RDetails) {
                  RDetails.forEach(function (city) {
                      let Rcode = city.code;
                      let Rname = city.name;

                      TagSet += "<option value=" + Rcode + ">" + Rname + "</option>";
                  });
              }
              Select_div.innerHTML = TagSet;
          });
  	}
  	  
  
    // 관광지 정보 가져오기 이벤트 ("검색" 버튼 이벤트) --> DB select 조건에 필요한 매개변수 담아서 던짐.
    document.getElementById("btn-search").addEventListener("click", () => {
    	let sidoCode = document.querySelector("#search-city").value;
    	let gugunCode = document.querySelector("#search-gugun").value;
    	let typeCode = document.querySelector("#search-content-id").value;
    	
    	//console.log(sidoCode, gugunCode, typeCode);
    	//console.log("${root}/navigator?action=showmap&sido="+sidoCode+"&gugun="+gugunCode+"&type="+typeCode)
    	
      	let form = document.querySelector("#form-search");
        form.setAttribute("action", "${root}/navigator?action=showmap&sido="+sidoCode+"&gugun="+gugunCode+"&type="+typeCode);
        form.submit();
    });
    	
    function moveCenter(lat, lng) {
      map.setCenter(new kakao.maps.LatLng(lat, lng));
    }
    

	// 회원정보수정 -> 수정하기 버튼 이벤트
	document.querySelector("#modify_btn").addEventListener("click", function () {
		if (!document.querySelector("#modify_name").value) {
            alert("이름 입력!!");
            return;
    	} else if (!document.querySelector("#modify_id").value) {
            alert("아이디 입력!!");
            return;
      	} else if (!document.querySelector("#modify_pw").value) {
            alert("비밀번호 입력!!");
            return;
      	} else if (document.querySelector("#modify_repw").value != document.querySelector("#modify_pw").value) {
            alert("비밀번호 확인!!");
            return;
       	} else {
            let form = document.querySelector("#form-modify");
            form.setAttribute("action", "${root}/user?action=mvmodify");
            form.submit();
		}
  	});
		
	// 마이페이지 -> 회원탈퇴 -> 탈퇴하기 버튼 이벤트
	document.querySelector("#delete_btn").addEventListener("click", function () {
		if (!document.querySelector("#delete_pw").value) {
			alert("비밀번호 입력!!");
            return;
     	} else if (!document.querySelector("#delete_text").value) {
            alert("삭제 확인 문구 입력!!");
            return;
       	} else if (document.querySelector("#delete_pw").value != document.querySelector("#dLogin_pw").value) {
              alert("비밀번호가 일치하지 않습니다.");
              return;
       	} else if (document.querySelector("#delete_text").value != "삭제합니다.") {
              alert("삭제 문구가 올바르지 않습니다.");
              return;
            
       	} else {
            let form = document.querySelector("#form-delete");
            form.setAttribute("action", "${root}/user?action=delete");
            form.submit();
 		}
	});
</script>
</html>
