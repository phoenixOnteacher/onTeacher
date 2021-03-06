<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<c:if test="${sessionScope.id == null }">
			<div id="loginblock">
				<div id="loginbtndiv">
					<div id="logintext">교육봉사 참여 / 수업 신청하려면?</div>
					<a id="loginbtn" class="btn btn-primary" href="/login">로그인하러 가기</a>
				</div>
			</div>
		</c:if>
		<c:if test="${fn:substring(sessionScope.id,0,1)=='2' }">
			<div id="loginblock">
				<div id="loginbtndiv">
					<div id="logintext">나에게 알맞은 수업을 검색 / 신청하려면?</div>
					<a id="loginbtn" class="btn btn-primary" href="/searchCourse">수업검색
						하기</a>
				</div>
			</div>
		</c:if>
		<c:choose>
			<c:when
				test="${fn:substring(sessionScope.id,0,1)=='3' && sessionScope.teacherActive==1}">
				<div id="loginblock">
					<div id="loginbtndiv">
						<div id="logintext">수업을 개설하고 학생과 매칭하려면?</div>
						<a id="loginbtn" class="btn btn-primary"
							href="/teacher/courseregister">수업등록 하기</a>
					</div>
				</div>
			</c:when>
			<c:when
				test="${fn:substring(sessionScope.id,0,1)=='3' && sessionScope.teacherActive==0}">
				<div id="loginblock">
					<div id="loginbtndiv">
						<div id="logintext">수업을 개설하고 학생과 매칭하려면?</div>
						<a id="loginbtn" class="btn btn-primary teacherReject"
							href="javascript:void(0);">수업등록 하기</a>
					</div>
				</div>
			</c:when>
		</c:choose>
		<div id="mainthumbblock">
			<div id="mainthumbtext">
				<p>현재 신청 가능한 수업</p>
				<a id="gotosearch" href="/searchCourse">전체보기</a>
			</div>
		</div>
		<div class="row row-cols-1 row-cols-md-4 g-4">
			<c:forEach var="course" items="${courses }">
				<div class="col">
					<div class="card" style="cursor: pointer;" onclick="location.href='/searchCourse/detail?courseId=${course.id }';">
						<c:choose>
							<c:when test="${course.teacher.profileImg == null}">
								<img src="/resources/img/logo.png" class="card-img-top">
							</c:when>
							<c:otherwise>
								<img src="/fileview/thprofile/${course.teacher.profileImg}"
									class="card-img-top">
							</c:otherwise>
						</c:choose>
						<div class="card-body">
							<h5 class="card-title">${course.title }</h5>
							<p class="card-text">${course.curriculum }</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>