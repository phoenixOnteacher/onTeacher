<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/listArticles.css" />

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
<div id="tc_wrap">
	<div id="tc_title_wrap">
		<div id="tc_text">궁금한 것을 선생님께 질문해보세요!</div>
	</div>
	<table class="table table-hover">
		<thead class="table-warning">
			<tr>
				<th>No</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>

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
						<tr class="tbody_row" style="TABLE-layout: fixed">
							<td>${article.id }</td>
							<td>${article.user_name}</td>
							<td align='left' width="20%">
								<p class="txt_line">
									<a href="./viewArticle?no=${article.id}&pageNo=${currentPage}">${article.title}</a>
								</p>
							</td>
							<td><fmt:parseDate value="${article.created_at}"
									var="articlecreated" pattern="yy-MM-dd" /> <fmt:formatDate
									value="${articlecreated}" pattern="yy.MM.dd" /></td>
							<td width="8%">${article.hits}</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
	</table>
	<c:if test="${sessionScope.id>=200000 && sessionScope.id<300000}">
		<div id="btn_wrap">
			<a class="cls1" href="articleForm"><button type="button"
					class="btn btn-primary">글쓰기</button></a>
		</div>
	</c:if>
	<fmt:parseNumber var="current" type="number" value="${currentPage}" />
	<fmt:parseNumber var="cnt" type="number" value="${pageCnt}" />
</div>
<div class=cls2>
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