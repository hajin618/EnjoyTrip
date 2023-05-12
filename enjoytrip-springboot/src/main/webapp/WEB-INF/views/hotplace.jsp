<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
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
            <h2 style="text-align: center">핫플 자랑하기</h2>
          </div>
        <div class="row mt-4">
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/1820973_image2_1.jpg"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card- body text-center mt-2">
              <h6 class="card-title">제주바다</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/hotpl01.png"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card-body text-center mt-2">
              <h6 class="card-title">부산 더베이101</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/hotpl02.png"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card-body text-center mt-2">
              <h6 class="card-title">부산 감천문화마을</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/hotpl03.png"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card-body text-center mt-2">
              <h6 class="card-title">김녕 해수욕장</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
        </div>
        <div class="row mt-4">
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/hotpl04.png"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card-body text-center mt-2">
              <h6 class="card-title">사려니숲</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/hotpl05.png"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card-body text-center mt-2">
              <h6 class="card-title">대관령 양떼 목장</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/hotpl06.png"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card-body text-center mt-2">
              <h6 class="card-title">인제 자작나무 숲</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
          <div class="card col-lg-3 col-md-6">
            <img
              src="./assets/img/hotpl07.png"
              width = "50"
              class="card-img-top w-100 mx-auto mt-3"
              alt="파이썬"
            />
            <div class="card-body text-center mt-2">
              <h6 class="card-title">전주 한옥마을</h6>
              <a href="#" class="btn btn-outline-primary btn-sm mt-3" data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
            </div>
          </div>
        </div>
        <!-- 핫플 자랑하기 end -->
      </div>
    </div>
    <!-- 중앙 content end -->
    <!-- 저장하기 modal start -->
  <div class="modal fade" id="pollModal4" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script src="./assets/js/main.js"></script>
  </body>
</html>
