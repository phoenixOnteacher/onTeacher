<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/index.css" />
<div id="i_wrap">
	<div id="i_content">
		<div id="carouselExampleDark" class="carousel carousel-dark slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="7000">
					<img src="${path}/resources/img/indeximg01.jpg"
						class="d-block w-100">
				</div>
				<div class="carousel-item" data-bs-interval="4000">
					<img src="${path}/resources/img/indeximg02.jpg"
						class="d-block w-100">
				</div>
				<div class="carousel-item">
					<img src="${path}/resources/img/indeximg03.jpg"
						class="d-block w-100">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleDark" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleDark" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
		<div id="loginblock">
			<div id="loginbtndiv">
				<div id="logintext">교육봉사 참여 / 수업 신청하려면?</div>
				<a id="loginbtn" class="btn btn-primary" href="#">로그인하러 가기</a>
			</div>
		</div>
		<div id="mainthumbblock">
			<div id="mainthumbtext">
				<p>현재 신청 가능한 수업</p>
				<a id="gotosearch" href="#">전체보기</a>
			</div>
		</div>
		<div class="row row-cols-1 row-cols-md-4 g-4">
			<div class="col">
				<div class="card">
					<img src="${path}/resources/img/logo.png" class="card-img-top">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">This is a longer card with supporting
							text below as a natural lead-in to additional content. This
							content is a little bit longer.</p>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<img src="${path}/resources/img/logo.png" class="card-img-top">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">This is a longer card with supporting
							text below as a natural lead-in to additional content. This
							content is a little bit longer.</p>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<img src="${path}/resources/img/logo.png" class="card-img-top">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">This is a longer card with supporting
							text below as a natural lead-in to additional content.</p>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<img src="${path}/resources/img/logo.png" class="card-img-top">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">This is a longer card with supporting
							text below as a natural lead-in to additional content.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>