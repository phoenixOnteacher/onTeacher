<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<div class="m-5">
	<div id="" class="">
		<h1>Homework Form</h1>
		${course_id }
		<form action="" method="post">
			<div class="mb-3">
			  <label for="homeworkTitle" class="form-label">과제 제목</label>
			  <input type="text" class="form-control" id="homeworkTitle" name="title">
			</div>
			<div class="mb-3">
			  <label for="homeworkContent" class="form-label">과제 내용</label>
			  <textarea class="form-control" id="homeworkContent" rows="5" name="content"></textarea>
			</div>
			<div class="mb-3">
			  <label for="homeworkDeadline" class="form-label">제출 기간</label>
			  <input class="form-control" type="date" id="homeworkDeadline" name="deadline">
			</div>
			<div class="mb-3">
			  <label for="homeworkFile" class="form-label">첨부 파일</label>
			  <input class="form-control" type="file" id="homeworkFile" name="filename">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>