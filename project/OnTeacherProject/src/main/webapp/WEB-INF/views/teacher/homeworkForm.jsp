<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<div class="m-5 px-5">
	<div id="" class="container">
		<div class="d-flex justify-content-start align-items-center text-secondary">
			<a href=".." class="text-secondary h5 text-decoration-none">수업 관리</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
			<a href="." class="text-secondary h5 text-decoration-none">${course.title }</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
		</div>
		<h2>Homework Form</h2>
		<form action="" method="post">
			<div class="mb-3">
			  <label for="homeworkTitle" class="form-label">과제 제목</label>
			  <input type="text" class="form-control" id="homeworkTitle" name="title" required>
			</div>
			<div class="mb-3">
			  <label for="homeworkContent" class="form-label">과제 내용</label>
			  <textarea class="form-control" id="homeworkContent" rows="5" name="content" required></textarea>
			</div>
			<div class="mb-3">
			  <label for="homeworkDeadline" class="form-label">제출 기간</label>
			  <input class="form-control" type="date" id="homeworkDeadline" name="deadline" required>
			</div>
			<div class="mb-3">
			  <label for="homeworkFile" class="form-label">첨부 파일</label>
			  <input class="form-control" type="file" id="homeworkFile" name="filename">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>
<script>
	document.getElementById('homeworkDeadline').min = new Date().toISOString().substring(0, 10);
</script>