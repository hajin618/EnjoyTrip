<!--  2023-03-30  Back-End Project 진행 기록  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- 별도 jsp 파일로 관리 -->
<%@include file="/include/head.jsp"%>
</head>
<body>
	<!-- 별도 jsp 파일로 관리 (네비게이션 바) -->
	<%@include file="/include/header.jsp"%>
	<!-- 중앙 content start -->
	<div class="container">
		<div style="height: 70px"></div>
		<div class="row">
			<!-- 중앙 사진 start -->
			<div id="carouselExampleIndicators" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="3" aria-label="Slide 4"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="./assets/img/m_04.jpg" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="./assets/img/m-01.jpg" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="./assets/img/m-02.jpg" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="./assets/img/m-03.jpg" class="d-block w-100" alt="...">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
			<!-- 중앙 사진 end -->
			<!-- 핫플 자랑하기 start -->
			<div class="row mt-5">
				<h3>핫플 자랑하기</h3>
				<p align="right">
					<button type="button" class="btn btn-secondary"
						onclick="location.href='hotplace.html'" style="width: 80px">더보기</button>
				</p>
			</div>
			<div class="row mt-1">
				<div class="card col-lg-3 col-md-6">
					<img src="./assets/img/1820973_image2_1.jpg" width="50"
						class="card-img-top w-100 mx-auto mt-3" alt="파이썬" />
					<div class="card-body text-center mt-2">
						<h6 class="card-title">제주바다</h6>
						<a href="#" class="btn btn-outline-primary btn-sm mt-3"
							data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
					</div>
				</div>
				<div class="card col-lg-3 col-md-6">
					<img src="./assets/img/hotpl01.png" width="50"
						class="card-img-top w-100 mx-auto mt-3" alt="파이썬" />
					<div class="card-body text-center mt-2">
						<h6 class="card-title">부산 더베이101</h6>
						<a href="#" class="btn btn-outline-primary btn-sm mt-3"
							data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
					</div>
				</div>
				<div class="card col-lg-3 col-md-6">
					<img src="./assets/img/hotpl02.png" width="50"
						class="card-img-top w-100 mx-auto mt-3" alt="파이썬" />
					<div class="card-body text-center mt-2">
						<h6 class="card-title">감천문화마을</h6>
						<a href="#" class="btn btn-outline-primary btn-sm mt-3"
							data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
					</div>
				</div>
				<div class="card col-lg-3 col-md-6">
					<img src="./assets/img/hotpl03.png" width="50"
						class="card-img-top w-100 mx-auto mt-3" alt="파이썬" />
					<div class="card-body text-center mt-2">
						<h6 class="card-title">김녕 해수욕장</h6>
						<a href="#" class="btn btn-outline-primary btn-sm mt-3"
							data-bs-toggle="modal" data-bs-target="#pollModal4">자세히보기</a>
					</div>
				</div>
			</div>
			<!-- 핫플 자랑하기 end -->
			<!-- 게시판 start -->
			<div class="row mt-5">
				<div class="col-md-12">
					<h3>여행정보 공유게시판</h3>
					<p align="right">
						<button type="button" class="btn btn-secondary" id="btn-look"
							style="width: 80px">더보기</button>
					</p>
					<table class="table table-hover">
						<thead>
							<tr class="text-center">
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>부산 더베이 101 추천합니다 <span class="badge bg-primary">2387</span>
								</td>
								<td class="text-center">최제혁</td>
								<td class="text-center">250</td>
							</tr>
							<tr>
								<td>부산 감천문화마을 <span class="badge bg-warning">712</span>
								</td>
								<td class="text-center">김하진</td>
								<td class="text-center">185</td>
							</tr>
							<tr>
								<td>김녕 해수욕장 <span class="badge bg-danger">1526</span>
								</td>
								<td class="text-center">박소현</td>
								<td class="text-center">136</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 게시판 end -->
		</div>
	</div>
	<!-- 중앙 content end -->
	<!-- 하단 footer start -->
	<footer class="section-content p-4 mt-3 container-fluid">
		<%@include file="/include/footer.jsp"%>
	</footer>
	<!-- 하단 footer end -->

	<!-- 저장하기 modal start -->
	<div class="modal fade" id="pollModal4" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div>
					<p style="text-align: center; font-size: 30px; padding-top: 20px">제주바다</p>
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
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 저장하기 modal end -->
	<!-- #################################################################################################################### -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="./assets/js/main.js"></script>
</body>
</body>
</html>