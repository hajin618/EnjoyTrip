<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 
		쿠키가 생성되었을 경우 값을 저장, 로그인 된 ID 값이 담겨진다. 
		로그인 체크박스(아이디 기억)가 선택된 상태가 마지막이라면 최근 로그인 한 ID 값을 가지고 있는 쿠키의 값을 가져온다.
		체크박스가 해제되면 value=""로 기본값이 적용되며 로그인 이후에도 로그인 ID를 담는 쿠키가 생성되지 않습니다.
-->
<c:set var="checked" value=""></c:set>
<c:if test="${cookie.ssafy_id.value ne null }">
	<c:set var="checked" value="checked"></c:set>
	<c:set var="saveId" value="${cookie.ssafy_id.value}"></c:set>
</c:if>

<!-- 상단 navbar start -->
<nav
	class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
	<div class="container">
		<!-- 메인 페이지 로고 이미지 -->
		<a class="navbar-brand text-primary fw-bold" href="/"> <img
			src="${root}/assets/img/trip_logo.png" alt="" width="250" />
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<!-- 로그인 후 session으로 logininfo 값이 전달되면 나타나는 네비게이션  -->
			<c:if test="${not empty logininfo}">
				<ul class="navbar-nav me-auto mb-lg-0"
					id="header_nav_confirm_on_test">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/attraction/view">지역별여행지</a> <!-- KakaoMap API Page -->
					</li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/plan/list">나의여행계획</a> <!-- Sample  --></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/hotplace.jsp">핫플자랑하기</a> <!-- Sample  --></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/notice/list?pgno=1&key=&word=">공지사항</a> <!-- 게시판 (★경로 수정 필요★) --></li>
				</ul>
			</c:if>
			<div class="header_nav_menu">
				<!--로그인 전 네비게이션 항목  (로그인 + 회원가입)  -->
				<c:if test="${empty logininfo}">
					<div id="header_nav_confirm_off">
						<ul class="navbar-nav me-auto mb-lg-0">
							<li class="nav-item"><a
								class="nav_link text-decoration-none" data-bs-toggle="modal"
								data-bs-target="#login_modal" href="#"
								style="color: black; padding-left: 900px;">로그인&nbsp;&nbsp;&nbsp;</a>
							</li>
							<li class="nav-item"><a
								class="nav_link text-decoration-none" data-bs-toggle="modal"
								data-bs-target="#signup_modal" href="#" style="color: black">회원가입</a>
							</li>
						</ul>
					</div>
				</c:if>
				<!--로그인 후 네비게이션 항목  (로그인 확인 문구 + 로그아웃 + 회원정보 관리[수정, 탈퇴]  -->
				<c:if test="${not empty logininfo}">
					<div id="header_nav_confirm_on">
						<ul class="navbar-nav mb-2 me-2 mb-lg-0">
							<li class="nav-item"><span class="nav-link">반갑습니다.
									${loginid}님</span></li>
							<li class="nav-item"><a class="nav-link" aria-current="page"
								href="${root}/user/logout">로그아웃</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">
									마이페이지 </a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item" href="#"
										data-bs-toggle="modal" data-bs-target="#modify_modal">회원정보수정</a>

									</li>
									<li><a class="dropdown-item" href="#"
										data-bs-toggle="modal" data-bs-target="#delete_modal">회원
											탈퇴</a></li>
								</ul></li>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</nav>
<!-- 상단 navbar end -->


<!-- ####################################################################################################################
         			모달 화면 구성
          -->
<!-- 로그인 modal start -->
<!--
           	모달 id : login_modal
           	아이디 : login_id
           	비밀번호 : login_pw
           	로그인버튼 : login_btn
            -->
<div class="modal fade" id="login_modal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">로그인</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<!-- Login -->
				<form id="form-login" method="POST" action="">
					<input type="hidden" name="action" value="login">
					<div class="mb-3">
						<label for="login_id" class="form-label">아이디</label> <input
							type="text" class="form-control" id="login_id" name="login_id"
							aria-describedby="login_id" value="${saveId}">

					</div>
					<div class="mb-3">
						<label for="login_pw" class="form-label">비밀번호</label> <input
							type="password" class="form-control" id="login_pw"
							name="login_pw">
					</div>
					<div class="mb-3 form-check">
						<input type="checkbox" class="form-check-input" id="login_check"
							name="login_check" value="ok" ${checked}> <label
							class="form-check-label" for="login_check">아이디 기억</label>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="login_btn">로그인</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<!-- 로그인 modal end -->



<!-- 회원 가입 만들기 -->
<!-- 
         	회원가입 모든 이름은 - signup으로 시작  (원래 = pollmodal2)
         	부수적인 것들은 "_이름"
         	모달 id = signup_modal
         	이름:   signup_name
         	아이디:  signup_id
         	PW:    signup_pw
         	PW확인: signup_repw
         	이메일아이디: signup_emailid
         	이메일도메인: signup_emaildomain
          -->
<div class="modal fade" id="signup_modal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">회원가입</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<!-- Sign Up -->
				<form id="form-signup" method="POST" action="">
					<div class="mb-3">
						<label for="signup_name" class="form-label">이름</label> <input
							type="text" class="form-control" id="signup_name"
							name="signup_name" aria-describedby="signup_name">
					</div>
					<div class="mb-3">
						<label for="signup_id" class="form-label">아이디</label> <input
							type="text" class="form-control" id="signup_id" name="signup_id"
							aria-describedby="signup_id">
					</div>
					<div class="mb-3">
						<label for="signup_pw" class="form-label">비밀번호</label> <input
							type="text" class="form-control" id="signup_pw" name="signup_pw"
							aria-describedby="signup_pw">
					</div>
					<div class="mb-3">
						<label for="signup_repw" class="form-label">비밀번호 확인</label> <input
							type="text" class="form-control" id="signup_repw"
							name="signup_repw" aria-describedby="signup_repw">
					</div>
					<div class="mb-3">
						<label for="signup_emailid" class="form-label">이메일 : </label>
						<div class="input-group">
							<input type="text" class="form-control" id="signup_emailid"
								name="signup_emailid" placeholder="이메일아이디" /> <span
								class="input-group-text">@</span> <select class="form-select"
								id="signup_emaildomain" name="signup_emaildomain"
								aria-label="이메일 도메인 선택">
								<option selected>선택</option>
								<option value="ssafy.com">싸피</option>
								<option value="google.com">구글</option>
								<option value="naver.com">네이버</option>
								<option value="kakao.com">카카오</option>
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<!--  버튼 누르면 이벤트 발생하게끔 세팅   -->
				<button type="button" class="btn btn-primary" id="signup_btn">회원가입</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<!-- 회원 가입 Modal End -->
<!-- 회원수정 만들기 -->
<!-- 회원수정 id명 변경
         	   세션에 연결된 Id: mLogin_id
         	   이름 : modify_name
         	  아이디 : modify_id
         	  비밀번호 : modify_pw
         	  비밀번호 확인 : modify_repw
         	  이메일 : modify_email
         	  수정버튼 : modify_btn
          -->
<div class="modal fade" id="modify_modal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">회원정보 수정</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<!-- Modify -->
				<form id="form-modify" method="POST" action="">
					<input type="hidden" class="form-control" id="mLogin_id"
						name="mLogin_id" value="${loginid}">
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">이름</label> <input
							type="text" class="form-control" id="modify_name"
							name="modify_name" aria-describedby="modify_name">
					</div>
					<div class="mb-3">
						<label for="exampleInputEmail1" class="form-label">아이디</label> <input
							type="text" class="form-control" id="modify_id" name="modify_id"
							aria-describedby="modify_id">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">비밀번호</label>
						<input type="text" class="form-control" id="modify_pw"
							name="modify_pw" aria-describedby="modify_pw">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">비밀번호
							확인</label> <input type="text" class="form-control" id="modify_repw"
							name="modify_repw" aria-describedby="modify_repw">
					</div>
					<div class="mb-3">
						<label for="modify_emailid" class="form-label">이메일 : </label>
						<div class="input-group">
							<input type="text" class="form-control" id="modify_emailid"
								name="modify_emailid" placeholder="이메일아이디" /> <span
								class="input-group-text">@</span> <select class="form-select"
								id="modify_emaildomain" name="modify_emaildomain"
								aria-label="이메일 도메인 선택">
								<option selected>선택</option>
								<option value="ssafy.com">싸피</option>
								<option value="google.com">구글</option>
								<option value="naver.com">네이버</option>
								<option value="kakao.com">카카오</option>
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" id="modify_btn">수정하기</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
<!-- 회원 수정 Modal End  -->
<!--  회원 탈퇴 Modal  (삭제 확인 문구 + 확인 비밀번호 입력) -->
<!--  form = form-delete (delete_modal)
         		PW: delete_pw
         		탈퇴문구: delete_text
         		버튼: delete_btn
          -->
<div class="modal fade" id="delete_modal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">회원 탈퇴</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<!-- Delete User -->
				<form id="form-delete" method="POST" action="">
					<input type="hidden" class="form-control" id="dLogin_id"
						name="dLogin_id" value="${loginid}"> <input type="hidden"
						class="form-control" id="dLogin_pw" name="dLogin_pw"
						value="${loginpw}">
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">비밀번호
							입력</label> <input type="text" class="form-control" id="delete_pw"
							name="delete_pw" aria-describedby="modify_name">
					</div>
					<div class="mb-3">
						<label for="exampleInputEmail1" class="form-label">회원탈퇴 문구
							입력 (삭제합니다.)</label> <input type="text" class="form-control"
							id="delete_text" name="delete_text"
							aria-describedby="delete_text" placeholder="삭제합니다.">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" id="delete_btn">탈퇴하기</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script>
	// 회원가입 버튼 이벤트
	document
			.querySelector("#signup_btn")
			.addEventListener(
					"click",
					function() {
						if (!document.querySelector("#signup_name").value) {
							alert("이름 입력!!");
							return;
						} else if (!document.querySelector("#signup_id").value) {
							alert("아이디 입력!!");
							return;
						} else if (!document.querySelector("#signup_pw").value) {
							alert("비밀번호 입력!!");
							return;
						} else if (document.querySelector("#signup_repw").value != document
								.querySelector("#signup_pw").value) {
							alert("비밀번호 확인!!");
							return;
						} else {
							let form = document.querySelector("#form-signup");
							form.setAttribute("action",
									"${root}/user?action=mvsignup");
							form.submit();
							alert("회원가입이 완료되었습니다!!");
						}
					});

	// 로그인 버튼 이벤트
	document.querySelector("#login_btn").addEventListener("click", function() {
		if (!document.querySelector("#login_id").value) {
			alert("아이디 입력!!");
			return;
		} else if (!document.querySelector("#login_pw").value) {
			alert("비밀번호 입력!!");
			return;
		} else {
			let form = document.querySelector("#form-login");
			form.setAttribute("action", "${root}/user/login");
			form.submit();
		}
	});

	// 회원정보수정 -> 수정하기 버튼 이벤트
	document
			.querySelector("#modify_btn")
			.addEventListener(
					"click",
					function() {
						if (!document.querySelector("#modify_name").value) {
							alert("이름 입력!!");
							return;
						} else if (!document.querySelector("#modify_id").value) {
							alert("아이디 입력!!");
							return;
						} else if (!document.querySelector("#modify_pw").value) {
							alert("비밀번호 입력!!");
							return;
						} else if (document.querySelector("#modify_repw").value != document
								.querySelector("#modify_pw").value) {
							alert("비밀번호 확인!!");
							return;
						} else {
							let form = document.querySelector("#form-modify");
							form.setAttribute("action",
									"${root}/user?action=mvmodify");
							form.submit();
						}
					});

	// 마이페이지 -> 회원탈퇴 -> 탈퇴하기 버튼 이벤트
	document
			.querySelector("#delete_btn")
			.addEventListener(
					"click",
					function() {
						if (!document.querySelector("#delete_pw").value) {
							alert("비밀번호 입력!!");
							return;
						} else if (!document.querySelector("#delete_text").value) {
							alert("삭제 확인 문구 입력!!");
							return;
						} else if (document.querySelector("#delete_pw").value != document
								.querySelector("#dLogin_pw").value) {
							alert("비밀번호가 일치하지 않습니다.");
							return;
						} else if (document.querySelector("#delete_text").value != "삭제합니다.") {
							alert("삭제 문구가 올바르지 않습니다.");
							return;
						} else {
							let form = document.querySelector("#form-delete");
							form.setAttribute("action",
									"${root}/user?action=delete");
							form.submit();
						}
					});
</script>