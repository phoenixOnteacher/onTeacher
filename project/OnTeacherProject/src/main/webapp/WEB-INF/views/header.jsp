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
<script src="${path }/resources/js/notification.js"></script>
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
					<li><i id="notificationBell" class="fas fa-bell" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"></i></li>
					<div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
					  <div class="offcanvas-header">
					    <h5 id="offcanvasRightLabel">알림</h5>
					    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
					  </div>
					  <div class="offcanvas-body">
					  </div>
					  <c:if test="">
					  
					  </c:if>
					  <!-- Modal -->
					  <div class="modal fade" id="certReuploadModal" tabindex="-1" aria-labelledby="certReuploadModalLabel" aria-hidden="true" data-bs-backdrop="false">
						  <div class="modal-dialog modal-dialog-centered">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="certReuploadModalLabel">자격 증명서 재업로드</h5>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <div class="mb-3">
						        	<!-- <h3>반려된 증명서</h3>
					        		<table class="table table-bordered">
										<tbody>
											<tr>
												<th>증명서 파일</th>
												<td><a href="/teacher/certfiledownload?filename=${teacher.fileName }">${teacher.fileName }</a></td>
											</tr>
											<tr>
												<th>설명</th>
												<td>${teacher.description }</td>
											</tr>
										</tbody>
									</table>
									<br> -->
									<!-- <h3>반려된 자격 증명서</h3>
									<p>증명서 파일</p>
									<a class="text-decoration-none" href="/teacher/certfiledownload?filename=${teacher.fileName }">${teacher.fileName }</a>
									<p>설명</p>
									<p>${teacher.description }</p> -->
									<form method="post" enctype="multipart/form-data" id="certReuploadForm">
										<div class="mb-3">
										  <label for="fileName" class="form-label float-start fw-bord">증명서 파일</label>
										  <input class="form-control" type="file" id="fileName" name="file">
										</div>
										<div class="mb-3">
										  <label for="description" class="form-label float-start fw-bord">설명</label>
										  <textarea class="form-control" id="description" rows="3" name="description"></textarea>
										</div>
										<button id="certReuploadBtn" type="submit" class="btn btn-primary">제출</button>
										<input type="hidden" name="notificationId"/>
									</form>
								</div>
						      </div>
						    </div>
						  </div>
					  </div>
					</div>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</div>