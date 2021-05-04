<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_list.js"></script>
<div id="" class="m-5 px-5">
	<div id="" class="container">
		<a href="." class="text-secondary h5 text-decoration-none">내 수업 <i
			class="fas fa-chevron-right"></i></a>
		<h2>${course.title }</h2>
		<div class="d-flex justify-content-start align-items-center">
			<a href="././." class="text-secondary h5 text-decoration-none">내
				수업</a> <i class="fas fa-chevron-right h5 secondary mx-2"></i> <a
				href="./." class="text-secondary h5 text-decoration-none">${course.title }</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
		</div>
		<div class="row mt-3">
			<nav class="nav flex-column col-3">
				<a class="nav-link text-reset" aria-current="page" href="#teahcer"
					data-toggle="tab" data-load="false">내 선생님</a> <a
					class="nav-link active in text-reset" href="#detail"
					data-toggle="tab" data-load="false">수업 상세</a> <a
					class="nav-link text-reset" href="#homework" data-toggle="tab"
					data-load="true">과제</a>
			</nav>
			<div class="tab-content col-9">
				<div>
					<jsp:include page="../common/homeworkList.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>