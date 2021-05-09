<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/homeworkList.css" />
<script src="${path }/resources/js/course_list.js"></script>
<br>
<br>
<div id="cd_title_wrap">
	<div id="cd_title">과제 목록</div>
</div>

<div id="cd_wrap">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">Title</th>
				<th scope="col">Deadline</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="homework" items="${homeworks }" varStatus="status">
				<tr>
					<th scope="row">${status.count}</th>
					<td>
						<div class="position-relative p-0">
							<a href="/homework/${homework.id }"
								class="text-decoration-none text-reset stretched-link p-3">
								${homework.title } </a>
						</div>
					</td>
					<td>${homework.deadline }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
