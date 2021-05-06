<!-- header.jsp 파일은 글로벌 네비게이션바만 들어가는 파일입니다.
글로벌 네비게이션바를 수정할 때는 이 파일과 header.css, header.js 파일만 수정하면 됩니다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/header.css" />

<script src="https://kit.fontawesome.com/0b88962ca7.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path }/resources/js/header.js"></script>
<div id="h_wrap">
	<nav class="navbar fixed-top" id="g_navbar">
		<div id="logo">
			<a href="/main"><img src="${path}/resources/img/logo.png" /></a>
		</div>
		<ul id="navbar_menu">
			<c:if test="${fn:substring(sessionScope.id,0,1)=='3'}"> <!-- 선생님인 경우 -->
				<li><a href="/teacher/courseregister">수업등록</a></li>
			</c:if>
			<li><a href="/searchCourse">수업검색</a></li>
			<c:choose>
				<c:when test="${fn:substring(sessionScope.id,0,1)=='2'}">
					<li><a href="/student/course-manage">내수업</a></li>
				</c:when>
				<c:otherwise> <!-- 비회원, 선생님일 경우 -->
					<li><a href="/teacher/course-manage">수업관리</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sessionScope.id == null }">
				</c:when>
				<c:otherwise>
					<li><a href="/ocr/main">OCR 인식</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="/listArticle">질문게시판</a></li>
			<c:choose>
				<c:when test="${sessionScope.id == null }">
					<li><a href="/login">로그인</a></li>
					<li id="btnJoin">회원가입
						<div id="down">
							<i class="fas fa-caret-down"></i>
						</div>
						<div id="up">
							<i class="fas fa-caret-up"></i>
						</div>
						<div id="joinsub">
							<ul id="submenu">
								<li class="submenu_li"><a href="/student/join">학생가입</a></li>
								<li class="submenu_li"><a href="/teacher/join">선생님가입</a></li>
							</ul>
						</div>
					</li>
				</c:when>
				<c:otherwise>
					<li><a href="/logout">로그아웃</a></li>
					<li><i id="notificationBell" class="fas fa-bell" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"></i></li>
					<div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
					  <div class="offcanvas-header">
					    <h5 id="offcanvasRightLabel">알림</h5>
					    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
					  </div>
					  <div class="offcanvas-body">
					  </div>
					</div>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</div>
<script>
// 알림 목록 가져오기
$('#notificationBell').click(function() {
	$.ajax({
		type: "POST",
		url: "http://localhost:8090/notification",
		success: function(res) {
			console.log(res);
			console.log(res.data);
			var notifications = res.data;
			console.log(notifications);
			var notificationStr = '';
			for (var i=0; i<notifications.length; i++) {
				notificationStr += '<div class="card text-dark bg-light mb-3" style="max-width: 100%;">';
				notificationStr += '<div class="card-header d-flex justify-content-between">';
				notificationStr += '<p class="text-start mb-0">' + notifications[i].createdAt.substring(0, 10) + '</p>';
				notificationStr += '<button type="button" class="btn-close" aria-label="Close"></button>';
				notificationStr += '</div>';
				notificationStr += '<div class="card-body">';
				notificationStr += '<p class="card-text text-start">' + notifications[i].content + '</p>';
				notificationStr += '</div></div>';
			}
			$('.offcanvas-body').html(notificationStr);
		}
	})
})
</script>