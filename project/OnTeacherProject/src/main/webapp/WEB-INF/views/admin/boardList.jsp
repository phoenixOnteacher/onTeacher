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


    <div id="wrap">
    	<h5><b>선생님 자격 심사 페이지</b></h5>
    	<br>
    	<table>
    		<tr>
    			<th>이름</th>
    			<th>아이디</th>
    			<th>이메일</th>
    			<th>전화번호</th>
     			<th>증빙서류 파일명</th>
     			<th>자격 심사</th>
    		</tr>

	 		<c:forEach var="t" items="${tea}">
				<tr>
					<td>${t.name}</td> 
					<td>${t.id}</td>
					<td>${t.email}</td>
					<td>${t.phoneNumber}</td>
					<td><a href="${path}/thcertiupload/${t.fileName}" target="_blank" title="기본증빙서류보기" >${t.fileName}</a></td>
					<td><button type="button" id="accpet"  onclick="certAccept()" >승인</button> <button type="button" id="reject" onclick="certReject()" >반려</button></td>
				</tr>
			</c:forEach> 
			
    	</table>
    </div>
</body>

</html>