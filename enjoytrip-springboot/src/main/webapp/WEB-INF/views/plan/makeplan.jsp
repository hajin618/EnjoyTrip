<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <%@include file="/include/head.jsp" %>

    <body>
        <%@include file="/include/header.jsp" %>


            <!-- 중앙 content start -->
            <div class="container">
                <div style="height: 70px">
                </div>
                <div class="row">
                    <!-- 핫플 자랑하기 start -->
                    <div class="container-fluid p-5">
                        <h2 style="text-align: center">내맘대로 여행코스</h2>
                    </div>

                    <!-- 여행 시작일, 여행 종료일 -->
                    <form id="form-register" method="POST" action="">
                        <div class="form-group">
                            <label for="title"
                                style="padding-top: 15px; font-weight: bold; font-size: 20px;">제목:</label>
                            <input type="text" class="form-control" id="subject" placeholder="제목 입력..." name="subject"
                                maxlength="100" required="required" pattern=".{4,100}" />
                        </div>

                        <div style="padding-left: 12px; padding-right: 12px; padding-top: 10px;">
                            <div class="row">
                                <div class="border col-sm-12 col-md-12 col-lg-2 col-2"
                                    style="border: 1px; border-color:#ced4da; border-radius: 0.375rem;">
                                    관광지를 검색해주세요!
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" placeholder="Recipient's username"
                                            aria-label="Recipient's username" aria-describedby="button-addon2">
                                        <button class="btn btn-outline-secondary" type="button" id="button-addon2"><i
                                                class="bi bi-search"></i></button>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-12 col-lg-8 col-8">
                                    <!-- kakaoMap API  start  -->
                                    <div id="map" style="width: 100%; height: 400px;">
                                        <!-- 	KakaoAPI 활용을 위한 appKey Setting.
		        			★ root 서버 주소 세팅 필요. (127.0.0.1:8088, 8081, 8080 설정? 한 상태로 마지막 확인-->
                                        <script type="text/javascript"
                                            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8820d3277f771b41fa361424002a1649"></script>

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
                                    </div>
                                    <!-- kakaoMap API  end -->
                                </div>
                                <div class="border col-sm-12 col-md-12 col-lg-2 col-2"
                                    style="border: 1px; border-color:#ced4da; border-radius: 0.375rem;">
                                    나의 여행코스
                                </div>
                            </div>

                        </div>


                        <div class="form-group">
                            <label for="content"
                                style="padding-top: 30px; font-weight: bold; font-size: 20px;">내용:</label>
                            <textarea class="form-control" rows="10" id="content" name="content"
                                placeholder="내용 입력..."></textarea>
                        </div>

                        <button type="button" class="btn btn-primary" style="margin-top: 15px; font-weight: bold;"
                            id="btn-register">글작성</button>
                        <button type="button" class="btn btn-danger" style="margin-top: 15px; font-weight: bold;"
                            href="${root}/article?action=list" id="btn-list">목록</button>
                    </form>
                </div>
            </div>
            <!-- 중앙 content end -->
            <!-- 저장하기 modal start -->
            <div class="modal fade" id="pollModal4" tabindex="-1" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div>
                            <p style="text-align:center; font-size:30px; padding-top:20px">제주바다</p>
                        </div>
                        <div class="modal-body">
                            <!-- Login -->
                            <form>
                                <div class="mb-12">
                                    <img src="./assets/img/m_04.jpg" alt="" style="height: 260px;" />
                                    <div id="emailHelp" class="form-text">제주도 해변가의 사진이다.</div>
                                </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 저장하기 modal end -->
            <!-- 하단 footer start -->
            <footer class="section-content p-4 mt-3 container-fluid">
                <%@include file="/include/footer.jsp" %>
            </footer>
            <!-- 하단 footer end -->

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
            <script src="./assets/js/main.js"></script>
    </body>

    </html>