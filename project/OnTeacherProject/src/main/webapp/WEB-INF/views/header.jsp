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
				<c:if test="${fn:substring(sessionScope.id,0,1)=='1'}">
					<li><a href="/admin/cert">자격심사</a></li>
				</c:if>
			<c:choose>
				<c:when test="${fn:substring(sessionScope.id,0,1)=='3' && sessionScope.teacherActive==1}">
					<li><a href="/teacher/courseregister">수업등록</a></li>
				</c:when>
				<c:when test="${fn:substring(sessionScope.id,0,1)=='3' && sessionScope.teacherActive==0}">
					<li><a href="javascript:void(0);" class="teacherReject">수업등록</a></li>
				</c:when>
			</c:choose>
			<li><a href="/searchCourse">수업검색</a></li>
			<c:choose>
				<c:when test="${fn:substring(sessionScope.id,0,1)=='2'}">
					<li><a href="/student/course-manage">내수업</a></li>
				</c:when>
				<c:when test="${sessionScope.id == null }"> <!-- 비회원 -->
					<li><a href="javascript:void(0);" class="nonmemberReject">수업관리</a></li>
				</c:when>
				<c:otherwise> <!-- 선생님, 관리자일 경우 -->
					<li><a href="/teacher/course-manage">수업관리</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sessionScope.id == null }">
					<li><a href="javascript:void(0);" class="nonmemberReject">OCR 인식</a></li>
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
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</div>