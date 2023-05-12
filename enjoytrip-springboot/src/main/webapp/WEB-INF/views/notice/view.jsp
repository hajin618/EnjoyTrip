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
					<h2 style="text-align: center">글보기</h2>
				</div>
				<div class="row my-2">
					<h2 class="text-secondary px-5">${notice.notice_idx}.
						${notice.notice_title}</h2>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="clearfix align-content-center">
							<img class="avatar me-2 float-md-start bg-light p-2"
								src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
							<p>
								<span class="fw-bold">${notice.userDto.user_name}</span> <br />

								<span class="text-secondary fw-light">
									${notice.notice_create} 조회수 : ${notice.notice_hit + 1} </span>
							</p>
						</div>
					</div>
					<div class="col-md-4 align-self-center text-end">댓글 :</div>
					<div class="divider mb-3"></div>
					<div class="text-secondary">${notice.notice_content}</div>
					<div class="divider mt-3 mb-3"></div>
					<div class="d-flex justify-content-end">
						<button type="button" id="btn-list"
							class="btn btn-outline-primary mb-3">글목록</button>
						<c:if test="${userinfo.userId eq noticeIdx.userId}">
							<button type="button" id="btn-mv-modify"
								class="btn btn-outline-success mb-3 ms-1">글수정</button>
							<button type="button" id="btn-delete"
								class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!--  페이지 번호 이동 끝 -->
	</div>
	<!-- 게시판 end -->
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
		<input type="hidden" name="pgno" value="${pgno}"> 
		<input type="hidden" name="key" value="${key}"> 
		<input type="hidden" name="word" value="${word}"> 
		<input type="hidden" name="notice_idx" value="${notice.notice_idx}"> 
	</form>
	
    <form id="form-param" method="get" action="">
      <!-- 나중에  pgno value ${pgno}로 바꿀것!! -->
      <input type="hidden" id="pgno" name="pgno" value="1">
      <input type="hidden" id="key" name="key" value="${key}">
      <input type="hidden" id="word" name="word" value="${word}">
    </form>
    
	<script>
      document.querySelector("#btn-list").addEventListener("click", function () {
    	  alert("목록으로 가자");
			let form = document.querySelector("#form-param");
   	  		form.setAttribute("action", "${root}/notice/list");
       		form.submit();
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        alert("글수정하자!!!");
        location.href = "${root}/article?action=mvmodify&articleno=${article.articleNo}";
      });
      document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글삭제하자!!!");
		let form = document.querySelector("#form-no-param");
  	  	form.setAttribute("action", "${root}/notice/delete");
      	form.submit();
      });
    </script>
</body>
</html>
