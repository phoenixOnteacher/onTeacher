<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/articleForm.css" />

<%
  request.setCharacterEncoding("UTF-8");
%>



<head>
<meta charset="UTF-8">
<title>글쓰기창</title>



<title>글쓰기창</title>
</head>
<body>
	<div id="articleForm_wrap">
	<div id="articleForm_title_wrap">
		<div id="articleForm_title">글쓰기</div>
	</div>
	
	<form action="/addArticle" method="post" enctype="multipart/form-data">
		<input type="hidden" name="user_id" value="${sessionScope.id}">

	<table class="table table-hover">		
				
</div>			
				
			<tr>	
				<td align="right">작성자</td>
				<td colspan="2">
				<input type="text" size="20" maxlength="100" value="${user_name}" disabled />		
				<input type="hidden" value="${user_name }"  name="user_name"/>
				</td>
			</tr>
			<tr>
				<td align="right">글제목:</td>
				<td colspan="2"><input type="text" size="67" maxlength="500"
					name="title" /></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>글내용:</td>
				<td colspan=2><textarea name="content" rows="10" cols="65"
						maxlength="4000"></textarea></td>
			</tr>
			<tr>
				<td align="right">파일 첨부:</td>
				<td><input type="file" name="file" onchange="readURL(this);" /></td>
			</tr>
			<tr>			
				<td>
				<button type="submit" class="btn btn-danger">글쓰기</button>
				</td>
				<td>
				<a href="listArticle?page=${pageNo}">
				<button type="button" class="btn btn-warning">돌아가기</button></a>
				</td>
		
			</tr>
		</table>
	</form>
</body>
</html>




