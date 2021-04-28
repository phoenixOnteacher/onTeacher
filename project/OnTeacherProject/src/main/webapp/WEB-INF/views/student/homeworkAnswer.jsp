<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="">
	<div id="" class="">
		<h1>Homework Answer</h1>
		<c:choose>
			<!-- 과제를 제출한 경우 과제 제출 내용을 보여줌 -->
			<c:when test="${!empty homeworkAnswer }">
			</c:when>
			<!-- 그렇지 않은 경우, 과제 제출 폼 보여줌 -->
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</div>
</div>