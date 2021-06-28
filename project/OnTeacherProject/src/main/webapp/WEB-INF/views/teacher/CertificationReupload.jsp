<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>${teacher.name} 선생님 자격증명서</p>

<h3>반려된 증명서</h3>
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
<br>
<h3>심사 다시 받기</h3>
<form action="/teacher/cert-reupload" method="post"  enctype="multipart/form-data">
	<table class="table table-bordered">
		<tbody>
			<tr>
				<th>증명서 파일</th>
				<td><input type="file" id="fileName" name="file"></td>
			</tr>
			<tr>
				<th>설명</th>
				<td><textarea id="description" name="description"></textarea></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="제출"></td>
			</tr>
		</tbody>
	</table>
</form>
</body>
</html>