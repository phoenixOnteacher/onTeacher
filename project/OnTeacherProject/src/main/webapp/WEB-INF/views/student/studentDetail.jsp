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
<img src="${student.profileImg }" width="100" height="100">
<p>이름 : ${student.name}</p>
<p>소개 : ${student.introduction}</p>

<table>
<tr>
	<th>수업명</th><th>학생후기</th>
</tr>
	<c:forEach items="${student.studentReviewList }" var="studentReview">
		<tr>
			<td>${studentReview.courseName }</td><td>${studentReview.content }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>