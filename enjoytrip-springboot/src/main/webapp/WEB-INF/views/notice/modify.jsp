<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Enjoy Trip</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
      </head>
      <style>
        body{
          font-family: 'Jua', sans-serif;
        }
      </style>
  <body>
    <!-- 상단 navbar start -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
      <div class="container">
        <a class="navbar-brand text-primary fw-bold" href="index.html">
          <img src="./assets/img/trip_logo.png" alt="" width="250" />
          
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="search.html">지역별여행지</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="#">나의여행계획</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="hotplace.html">핫플자랑하기</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="board.html">여행정보공유</a>
            </li>
          </ul>
          <!-- 로그인 전 -->
          <ul class="navbar-nav mb-2 me-2 mb-lg-0">
            
          </ul>
          <!-- 로그인 후 -->
          <ul class="navbar-nav mb-2 me-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="#">로그아웃</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                마이페이지
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li>
                  <a
                    class="dropdown-item"
                    href="#"
                    data-bs-toggle="modal"
                    data-bs-target="#pollModal"
                    >회원정보수정</a
                  >
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- 상단 navbar end -->
    <!-- 공지사항 컨테이너 -->
    <div class="container-fluid p-5">
        <!-- 제목 컨테이너 -->
        <div class="container-fluid">
            <div>
                <h2 style="text-align: center; padding-bottom: 10px; font-weight: bold;">여행 정보 등록</h2>
            </div>
            <div>
                <h2 style="text-align: center; color:blue; background-color:skyblue; border-radius: 10px; padding: 15px; font-weight: bold;">글수정</h2>
            </div>
            <div>
            <form id="form-modify" method="POST" action="">
                <div class="form-group">
          	<input type="hidden" name="action" value="modify">
          	<input type="hidden" name="articleno" value="${article.articleNo}">                
                    <label for="title" style="padding-top: 15px; font-weight: bold; font-size: 20px;">제목:</label>
                    <input type="text" class="form-control" id="subject"
                     placeholder="제목 입력..." name="subject"
                     maxlength="100" required="required"
                     pattern=".{4,100}" value="${article.subject}" />
                  </div>
                  <div class="form-group">
                 <label for="content" style="padding-top: 30px; font-weight: bold; font-size: 20px;">내용:</label>
                 <textarea class="form-control" rows="10" id="content"
                  name="content" placeholder="내용 입력...">${article.content}</textarea>
               </div>
              
               <button type="button" class="btn btn-primary" style="margin-top: 15px; font-weight: bold;" id="btn-modify">수정하기</button>
               <button type="button" class="btn btn-danger" style="margin-top: 15px; font-weight: bold;" id="btn-list" >목록</button>
            </div>
		  </form>
        </div>
    </div>
    <!-- 하단 footer start -->
    <footer class="section-content p-4 mt-3 container-fluid">
        <div class="row">
          <div class="mb-4 text-center col-2">
            <img src="./assets/img/ssafy_logo.png">
          </div>
          <div class="mb-4 ml-auto text-right col-10">
            <div class="ms-auto col-12 align-self-end " style="padding-left: 45%;">
              <a  href="https://www.ssafy.com/" target="_blank" title="SSAFY"><img src="./assets/img/ssafy-logo-small.bd603755.png" alt="SSAFY" class="img-link" height="50px" ></a>
              <a  href="https://www.ssafy.com/" target="_blank" title="SSAFY"><img src="./assets/img/tour-api-logo.ebcb1140.png" alt="SSAFY" class="img-link" height="50px" ></a>
              <a  href="https://www.ssafy.com/" target="_blank" title="SSAFY"><img src="./assets/img/visit-korea-logo.858325ef.png" alt="SSAFY" class="img-link" height="50px" ></a>
              <a  href="https://www.ssafy.com/" target="_blank" title="SSAFY"><img src="./assets/img/mcst-logo.86700cdd.png" alt="SSAFY" class="img-link" height="50px" ></a>
            </div>
          </div>
          <div class="row mt-3"  >
            <div class="text-center company-info col" style="font-weight: bold;" >
              <i class="fas fa-user" ></i>
              대표 : JHJ 주소: 124, Deokmyeong-dong, Yuseong-gu, Daejeon, Republic of Korea
            대표번호: 1588-1111 사업자번호 : 123-4567-987
            </div>

          </div>
        </div>

      </footer>
    <!-- 하단 footer end -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script src="./assets/js/main.js"></script>
   <script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-modify");
          form.setAttribute("action", "${root}/article?action=modify");
          form.submit();
        }
      });
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/article?action=list&pgno=1&key=&word=";
      });
    </script>
   
  </body>
</html>

