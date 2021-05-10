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

	<form name="formArticle" method="post" action="modArticle">
		<input type="hidden" value="${pageNo}" name="pageNo">
		<table class="table table-borderless">
			<tr>
				<td align="center">글번호</td>
				<td>${article.id }</td>
			</tr>
			<tr>
				<td align="center">작성자</td>
				<td>${article.user_name }</td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td>${article.title }</td>
			</tr>
			<tr>
				<td align="center">내용</td>
				<td>${article.content }</td>
			</tr>
			<tr>
				<td align="center">등록일자</td>
				<td>${article.created_at}</td>
			</tr>
			<tr id="modArticle">
				<td>

					<div class="btn-group-vertical" role="group"
						aria-label="Basic mixed styles example">
						<c:if test="${sessionScope.id == article.user_id }">
							<button type="submit" class="btn btn-danger">수정하기</button>

							<a href="deleteArticle?no=${article.id}" type="submit"
								class="btn btn-warning">삭제하기</a>

						</c:if>
						<a href="listArticle?page=${pageNo}"></a>
						<button type="submit" class="btn btn-light">리스트로 돌아가기</button>
					</div>
				<td>파일보기<br> <a
					href="articleDownload?filename=${article.filename}"
					style="color: orange; font-size: 1.0em; font-weight: bold; background: #efefef;">${article.filename}</a>
				</td>
			</tr>
		</table>
	</form>


	<table align="center" border="1" width="100%">
		<tr height="10" align="center" bgcolor="#FBAD38">

			<div class="p-3 mb-2 bg-warning text-dark">댓글창</div>
			<!-- 
				<td>댓글 번호</td> 
				-->
			<td>내용</td>
			<td>작성자</td>
			<td>작성일</td>
		</tr>
		<c:forEach var="comment" items="${commentList}">
			<tr>
				<!-- 
					<td>${comment.id}</td>
					-->
				<td class="overflow-auto">${comment.content}</td>
				<td>${comment.user_name}</td>
				<td>${comment.created_at}</td>
			</tr>
		</c:forEach>
	</table>

	<!-- 댓글 작성 영역입니다 -->
	<form method="post" action="addComment">
		<input type="hidden" value="${article.id }" name="article_id" /> <input
			type="hidden" value="${sessionScope.id}" name="user_id" />
		<c:if test="${sessionScope.id>=300000 && sessionScope.id<400000}">

			<hr id="comment_line">
			<tr>
				<td>댓글작성란</td>
				<td><textarea name="content" id="content"
						placeholder="댓글을 작성해주세요" class="form-control" required="required"></textarea></td>
				<td>
					<button type="submit" class="btn btn-primary" id="comment_btn">
						댓글<br>작성
					</button>
				</td>
			</tr>
		</c:if>
	</form>
</div>
