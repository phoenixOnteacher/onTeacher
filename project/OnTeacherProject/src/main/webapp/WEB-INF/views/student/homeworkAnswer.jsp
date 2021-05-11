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
				<h2>제출된 과제</h2>
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
				<h2>과제 작성</h2>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th colspan="1" class="px-3">Content</th>
							<td colspan="3" class="text-center px-3">제출 기한이 지났습니다.</td>
						</tr>
						<tr>
							<th colspan="1" class="px-3">과제 업로드</th>
							<td colspan="3" class="text-center px-3"></td>
						</tr>
						<tr>
							<td></td>
							<td style="text-align: right;"><input type="submit" disabled="disabled"></td>
						</tr>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h2>과제 작성</h2>
				<form action="/student/${homework.id}/homeworkanswer" method="post" enctype="multipart/form-data">
					<table class="table table-bordered">
						<tbody>
							<tr>	
								<th colspan="1" class="px-3">Content</th>
								<td colspan="3" class="text-center px-3"><textarea id="content" name="content"></textarea></td>
							</tr>
							<tr>
								<th colspan="1" class="px-3">과제 업로드</th>
								<td colspan="3" class="text-center px-3"><input type="file" id="filename" name="file"></td>
							</tr>
							<tr>
								<td></td>
								<td style="text-align:right;"><input type="submit"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>
