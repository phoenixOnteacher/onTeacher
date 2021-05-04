<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
  request.setCharacterEncoding("UTF-8");
%>

<!-- >
글 목록보기 
http://localhost:8090/listArticle -->
<!-- >
글 쓰기
http://localhost:8090/articleForm -->
<!-- >
글 상세보기
http://localhost:8090/viewArticle?no=x
x값은 아무거나 넣어서 확인. listArticle에서 글 눌러서 확인도 가능
 -->


<!DOCTYPE html>
<html>
<head>
<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}
</style>
<meta charset="UTF-8">
<title>글목록창</title>
</head>

<body>
	<c:if test="${sessionScope.id>=200000 && sessionScope.id<300000}">
	<a class="cls1" href="articleForm"><p class="cls2">글쓰기</p></a>
	</c:if>
	
	<!-- ><a class="cls1" href="javascript:fn_articleForm"><p class="cls2">글쓰기</p></a>-->

	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>No</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:choose>
			<c:when test="${articlesList ==null }">
				<tr height="10">
					<td colspan="7">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${articlesList !=null }">
				<c:forEach var="article" items="${articlesList }"
					varStatus="articleNO">
					<tr align="center">
						<td width="10%">${article.id }</td>
						<td width="10%">${article.user_name}</td>
						<td align='left' width="35%"><span
							style="padding-right: 30px"></span> <a
							href="./viewArticle?no=${article.id}&pageNo=${currentPage}">${article.title} </a></td>
						<td width="10%">${article.created_at}</td>
						<td width="5%">${article.hits}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<fmt:parseNumber var="current" type="number" value="${currentPage}" />
	<fmt:parseNumber var="cnt" type="number" value="${pageCnt}" />
	
	<div class="bar">
	    <!-- 이전 페이지 -->
		<c:choose>
			<c:when test="${current eq 1}">
				<a href="listArticle?page=${current}">&laquo;</a>
			</c:when>
			<c:otherwise>
				<a href="listArticle?page=${current-1}">&laquo;</a>
			</c:otherwise>
		</c:choose>
		<!-- 게시판 페이징 숫자클릭 -->
		<c:forEach var="i" begin="1" end="${pageCnt}">
			<a href="listArticle?page=${i}" class="button">${i}</a>
		</c:forEach>
		<!-- 다음 페이지 -->
		<c:choose>
			<c:when test="${current eq cnt}">
				<a href="listArticle?page=${current}">&raquo;</a>
			</c:when>
			<c:otherwise>
				<a href="listArticle?page=${current+1}">&raquo;</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>





