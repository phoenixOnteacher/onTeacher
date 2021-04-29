<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.ArrayList" %>    
<%@ page import="com.onteacher.vo.Teacher" %> 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${path}/resources/css/board.css" />
<title>선생님 자격 심사</title>
</head>


<body>

	<!-- 테스트 용 -->
	<P>${tea.name}</P>
	<P>${tea.id}</P>
	<P>${tea.email}</P>
	


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
			<tr>
    			<td>01</td>
    			<td>홍길동</td>
    			<td>30001</td>
    			<td>hong@naver.com</td>
    			<td>010-3452-2342</td>
    			<td>2021-04-29,14:00</td>
    			<td>대학교 학생증</td>			
			</tr>
			<c:forEach var="t" items="${tea}">
				<tr>
					<td></td>
					<td>${t.name}</td>
					<td>${t.id}</td>
					<td>${t.email}</td>
					<td>${t.phone_number}</td>
					<td></td>
					<td>${t.description}</td>
				</tr>
			</c:forEach>
    	</table>
    </div>
</body>

</html>