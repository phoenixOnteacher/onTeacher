<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/viewArticle.css" />

<%
request.setCharacterEncoding("UTF-8");
%>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<div id="viewArticle_wrap">
	<div id="viewArticle_title_wrap">
		<div id="viewArticle_title">질문게시판</div>
	</div>

	<input type="hidden" value="${pageNo}" name="pageNo" id="form1">
	<table class="table table-borderless">
		<tr>
			<td colspan="4" id="a_title">${article.title }</td>
		</tr>
		<tr>
			<td class="left1">작성자</td>
			<td colspan="2" class="right1">${article.user_name }</td>
			<td class="cdate">등록일자&nbsp;&nbsp;&nbsp;${article.created_at}</td>
		</tr>
		<tr>
			<td colspan="4" class="right2">${article.content }</td>
		</tr>
		<tr>
			<td class="left2">파일보기</td>
			<td colspan="3" class="right3"><a
				href="articleDownload?filename=${article.filename}"
				style="color: orange; font-size: 1.0em; font-weight: bold;">${article.filename}</a>
			</td>
		</tr>
	</table>
	<div id="btn_group_wrap">
		<div class="btn-group" role="group"
			aria-label="Basic mixed styles example">
			<c:if test="${sessionScope.id == article.user_id }">
				<a href="modArticle?no=${article.id }"><button type="submit"
						class="btn btn-warning">수정하기</button></a>
				<a href="deleteArticle?no=${article.id}" type="submit"
					class="btn btn-secondary">삭제하기</a>
			</c:if>
			<a href="listArticle?page=${pageNo}">
				<button type="submit" class="btn btn-light">목록</button>
			</a>
		</div>
	</div>
	<hr id="comment_line">
	<div id="c_title">댓글</div>
	<table class="table table-borderless" id="c_table">
		<c:forEach var="comment" items="${commentList}">
			<tr>
				<td id="c1">${comment.user_name}</td>
				<td id="c2" class="overflow-auto">${comment.content}</td>
				<td id="c3">${comment.created_at}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 댓글 작성 영역입니다 -->
	<form method="post" action="addComment">
		<input type="hidden" value="${article.id }" name="article_id" /> <input
			type="hidden" value="${sessionScope.id}" name="user_id" />
		<c:if test="${sessionScope.id>=300000 && sessionScope.id<400000}">
			<textarea name="content" id="content" placeholder="댓글을 작성해주세요"
				class="form-control" required="required"></textarea>
			<div id="c_btn_wrap">
				<button type="submit" class="btn btn-primary" id="comment_btn">
					댓글달기</button>
			</div>
		</c:if>
	</form>
</div>
