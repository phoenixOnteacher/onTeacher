<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/modifyArticle.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${path}/resources/js/modifyArticle.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${path }/resources/js/articleForm.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<%
request.setCharacterEncoding("UTF-8");
%>

<div id="modArticle_wrap">
	<div id="modArticle_title_wrap">
		<div id="modArticle_title">질문게시판 - 수정</div>
	</div>
	<form action="modArticle" method="post" name="formArticle">
		<input type="hidden" value="${pageNo}" name="pageNo" id="form1">
		<input type="hidden" value="${article.created_at }" /> <input
			type="hidden" value="${article.user_name }" /> <input type="hidden"
			value="${article.id }" name="id" />
		<table class="table-borderless">
			<tr>
				<td class="thead"><label for="title">제목</label></td>
				<td class="tbody"><input type="text" maxlength="500"
					name="title" class="form-control" value="${article.title }"
					required="required" /></td>
			</tr>
			<tr>
				<td class="thead" valign="top"><label for="content">내용</label></td>
				<td class="tbody"><textarea id="summernote" name="content"
						required="required">${article.content }</textarea></td>
			</tr>
		</table>
		<div id="btn_wrap">
			<button type="submit" class="btn btn-primary">수정하기</button>
		</div>
	</form>
</div>
