<!-- header.jsp 파일은 글로벌 네비게이션바만 들어가는 파일입니다.
글로벌 네비게이션바를 수정할 때는 이 파일과 header.css, header.js 파일만 수정하면 됩니다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/header.css" />
<script src="https://kit.fontawesome.com/0b88962ca7.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path }/resources/js/header.js"></script>
<div id="h_wrap">
	<nav class="navbar fixed-top" id="g_navbar">
		<div id="logo">
			<a href="#"><img src="${path}/resources/img/logo.png" /></a>
		</div>
		<ul id="navbar_menu">
			<li><a href="#">수업검색</a></li>
			<li><a href="#">수업관리</a></li>
			<li><a href="#">질문게시판</a></li>
			<c:choose>
				<c:when test="${sessionScope.id == null }">
					<li><a href="#">로그인</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
			<li id="btnJoin">회원가입
				<div id="down">
					<i class="fas fa-caret-down"></i>
				</div>
				<div id="up">
					<i class="fas fa-caret-up"></i>
				</div>
				<div id="joinsub">
					<ul id="submenu">
						<li class="submenu_li"><a href="#">학생가입</a></li>
						<li class="submenu_li"><a href="#">선생님가입</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</nav>
</div>