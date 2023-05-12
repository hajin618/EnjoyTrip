<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/include/head.jsp"%>
<body>
	<%@ include file="/include/header.jsp"%>


	<!-- 중앙 content start -->
	<div class="container">
		<div style="height: 70px"></div>
		<div class="row">
			<!-- 게시판 start -->
			<!-- 공지사항 컨테이너 -->
			<div class="container-fluid p-5">
				<!-- 제목 컨테이너 -->
				<div class="container-fluid">
					<h2 style="text-align: center">공지사항</h2>
				</div>
				<!-- 검색 컨테이너 -->
				<div style="display: flex; padding-top: 50px;">
					<!--  <button type="button" class="btn btn-outline-primary" onclick = "location.href='write.jsp'" >공지 작성하기</button> -->
					<button type="button" class="btn btn-outline-primary"
						onclick="location.href='${root}/notice/write'">게시글 작성하기</button>
					<form>
						<div
							style="display: flex; justify-content: end; flex-direction: row; padding-left: 700px;">
							<select class="form-select" aria-label="Default select example"
								style="margin-right: 5px">
								<option selected>검색조건</option>
								<option value="1">글번호</option>
								<option value="2">제목</option>
								<option value="3">작성자</option>
							</select> <input type="email" class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" style="margin-right: 5px" />
							<button type="button" class="btn btn-outline-secondary w-50">검색</button>
						</div>
					</form>
				</div>
				<!-- 테이블 컨테이너 -->
				<div>
					<table class="table table-hover">
						<thead>
							<tr class="text-center">
								<th scope="col">글번호</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">조회수</th>
								<th scope="col">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${notice}">
								<tr class="text-center">
									<th scope="row">${article.notice_idx}</th>
									<td class="text-start"><a href="#"
										class="article-title link-dark" data-no="${article.notice_idx}"
										style="text-decoration: none">
											${article.notice_title} </a></td>
									<td>${article.userDto.user_name}</td>
									<td>${article.notice_hit}</td>
									<td>${article.notice_create}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!--  테이블 컨테이너 끝  -->
					<!--  페이지 번호 이동 시작  -->
					<div class="row">${navigation.navigator}</div>
				</div>
			</div>
			<!--  페이지 번호 이동 끝 -->
		</div>
	</div>
	<!-- 게시판 end -->
	</div>
	</div>
	<!-- 중앙 content end -->
	<!-- 하단 footer start -->
	<footer class="section-content p-4 mt-3 container-fluid">
		<%@ include file="/include/footer.jsp"%>

	</footer>
	<!-- 하단 footer end -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="./assets/js/main.js"></script>
    
    <form id="form-no-param" method="get" action="${root}/notice/view">
<%--       <input type="hidden" name="pgno" value="${pgno}">
      <input type="hidden" name="key" value="${key}">
      <input type="hidden" name="word" value="${word}"> --%>
      <input type="hidden" id="notice_idx" name="notice_idx" value="">
    </form>
    
	<script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          console.log(this.getAttribute("data-no"));
          document.querySelector("#notice_idx").value = this.getAttribute("data-no");
          document.querySelector("#form-no-param").submit();
        });
      });

      
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
          console.log(this.parentNode.getAttribute("data-pg"));
          document.querySelector("#p-action").value = "list";
       	  document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
       	  document.querySelector("#p-key").value = "${param.key}";
       	  document.querySelector("#p-word").value = "${param.word}";
          document.querySelector("#form-param").submit();
       	  document.querySelector("#notice_idx").value = this.getAttribute("data-no");
       	 });
      });
    </script>
</body>
</html>
