<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate" />

<div class="">
	<div id="" class="">
		<br>
		<c:choose>
			<c:when test="${!empty homeworkAnswer }">	
				<h2 class="hw-title">제출된 과제</h2>
				<table class="table table-bordered">
					<tbody>
						<tr>	
							<th colspan="1" class="px-3">Content</th>
							<td colspan="3" class="text-center px-3">${homeworkAnswer.content }</td>
						</tr>
						<tr>
							<th colspan="1" class="px-3">과제 업로드</th>
							<td colspan="3" class="text-center px-3"><a href="/hafiledownload?filename=${homeworkAnswer.filename }">${homeworkAnswer.filename }</a></td>
						</tr>
					</tbody>
				</table>
			</c:when>
			<c:when test="${empty homeworkAnswer && nowDate>homework.deadline }">
				<h5 class="text-center text-secondary">제출 기한이 지났습니다.</h5>
			</c:when>
			<c:otherwise>
				<h2 class="hw-title">과제 작성</h2>
				<form action="/student/${homework.id}/homeworkanswer" method="post" enctype="multipart/form-data">
					<div class="mb-3">
					  <label for="homeworkContent" class="form-label">과제 내용</label>
					  <textarea class="form-control" id="homeworkContent" rows="5" id="content" name="content"></textarea>
					</div>
					<div class="mb-3">
					  <label for="homeworkFile" class="form-label">첨부 파일</label>
					  <input class="form-control" type="file" id="filename" name="file">
					</div>
					<div class="d-flex justify-content-center">
					  <button type="submit" class="btn btn-primary">제출</button>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>
