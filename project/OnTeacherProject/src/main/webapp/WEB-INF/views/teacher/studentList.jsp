<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<div id="" class="m-5 px-5">
	<div id="" class="container">
		<h1 class="my-3">수업 관리</h1>
		<h2 class="my-3">${course.title }</h2>
		<div class="row">
			<nav class="nav flex-column col-4">
			  <a class="nav-link active" aria-current="page" href="#">학생 관리</a>
			  <a class="nav-link" href="#">과제</a>
			  <a class="nav-link" href="#">수업 상세</a>
			  <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">전달 사항</a>
			</nav>
			<div class="col-8">
				<c:forEach var="student" items="${studentList }">
			    	<div class="card mb-3" style="max-width: 540px;">
					  <div class="row g-0">
					    <div class="col-md-4">
					      <img src="..." alt="...">
					    </div>
					    <div class="col-md-8">
					      <div class="card-body">
					        <h5 class="card-title">${student.name } 학생</h5>
					        <p class="card-text"><i class="fas fa-envelope"></i> ${student.email }</p>
					        <p class="card-text"><i class="fas fa-phone"></i> ${student.phoneNumber }</p>
					        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
					      </div>
					    </div>
					  </div>
					</div>
			    </c:forEach>
			</div>
		</div>
	</div>
</div>