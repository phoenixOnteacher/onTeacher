<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/index.css" />
<div id="" class="m-5">
	<div id="">
		<h1>Homework Detail</h1>
		${homework.title }
		${homework.content }
		${homework.deadline }
	</div>
</div>