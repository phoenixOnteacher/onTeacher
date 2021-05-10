<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/articleForm.css" />
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

<div id="articleForm_wrap">
	<div id="articleForm_title_wrap">
		<div id="articleForm_title">글쓰기</div>
	</div>

	<form action="/addArticle" method="post" enctype="multipart/form-data">
		<input type="hidden" name="user_id" value="${sessionScope.id}">

		<table class="table-borderless">
			<tr>
				<td class="thead"><label for="name">작성자</label></td>
				<td class="tbody"><input type="text" maxlength="100"
					value="${user_name}" disabled /> <input type="hidden"
					value="${user_name }" name="user_name" /></td>
			</tr>
			<tr>
				<td class="thead"><label for="title">제목</label></td>
				<td class="tbody"><input type="text" maxlength="500" name="title"
					class="form-control" /></td>
			</tr>
			<tr>
				<td class="thead" valign="top"><label for="content">내용</label></td>
				<td class="tbody"><textarea id="summernote" name="content"
						required="required"></textarea></td>
			</tr>
			<tr>
				<td class="thead"><label for="file">파일 첨부</label></td>
				<td class="tbody"><input type="file" name="file" onchange="readURL(this);"
					class="form-control" /></td>
			</tr>
			<tr>
				<td>
					<button type="submit" class="btn btn-danger">글쓰기</button>
				</td>
				<td><a href="listArticle?page=${pageNo}">
						<button type="button" class="btn btn-warning">돌아가기</button>
				</a></td>

			</tr>
		</table>
	</form>
</div>