<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_list.js"></script>
<div id="" class="m-5 px-5">
	<div id="" class="container">
		<h1>수업 관리<span class="my-3 text-secondary h4">${course.title }</span></h1>
		<div class="row mt-3">
			<nav class="nav flex-column col-3">
			  <a class="nav-link active in text-reset" aria-current="page" href="#student" data-toggle="tab" data-load="true">학생 관리</a>
			  <a class="nav-link text-reset" href="#detail" data-toggle="tab" data-load="false">수업 상세</a>
			  <a class="nav-link text-reset" href="#homework" data-toggle="tab" data-load="false">과제</a>
			</nav>
			<div class="tab-content col-9">
				<div class="tab-pane fade show active" id="student">
				  	<c:forEach var="student" items="${students }">
				    	<div class="card mb-3" style="max-width: 540px;">
						  <div class="row g-0">
						    <div class="col-md-4">
						      <img src="" alt="...">
						    </div>
						    <div class="col-md-8">
						      <div class="card-body">
						        <h5 class="card-title h4 mb-3">${student.name } 학생</h5>
						        <p class="card-text"><i class="fas fa-phone"></i> ${student.phoneNumber }</p>
						        <p class="card-text"><i class="fas fa-envelope"></i> ${student.email }</p>
						        <p class="card-text"><i class="fas fa-birthday-cake"></i> ${student.birthday }</p>
						      </div>
						    </div>
						  </div>
						</div>
			    	</c:forEach>
				</div>
				<div class="tab-pane fade" id="detail">
					<h1>상세 페이지</h1>
				</div>
				<div class="tab-pane fade" id="homework">
					<table class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col">No</th>
					      <th scope="col">Title</th>
					      <th scope="col">Deadline</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="homework" items="${homeworks }" varStatus="status">
						    <tr>
						      <th scope="row">${status.count} </th>
						      <td>
						      	<div class="position-relative p-0">
						      		<a href="./homeworkDetail/${homework.id }" class="text-decoration-none text-reset stretched-link p-3">
						      			${homework.title }
						      		</a>
					      		</div>
				      		  </td>
						      <td>${homework.deadline }</td>
						    </tr>
					  	</c:forEach>
					  </tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>