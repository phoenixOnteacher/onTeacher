<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${path}/resources/css/board.css" />
<title>선생님 자격 심사</title>
</head>

<body>
    <div id="wrap">
    	<h5><b>선생님 자격 심사 페이지</b></h5>
    	<br>
    	<table>
    		<tr>
    			<th>번호</th>
    			<th>이름</th>
    			<th>아이디</th>
    			<th>이메일</th>
    			<th>전화번호</th>
    			<th>신청일</th>
    			<th>증빙서류</th>
    		</tr>
			<c:forEach var="posting" items="${list }">
				<tr>
					<td>${posting.num}</td>
					<td>${posting.title}</td>
					<td>${posting.name}</td>
					<td>${posting.name}</td>
					<td>${posting.writeDate}</td>
					<td>${posting.readCount}</td>
					<td>${posting.readCount}</td>
				</tr>
			</c:forEach>
    	</table>
    </div>
</body>

</html>