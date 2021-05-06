<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.onteacher.vo.Teacher"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/boardList.css" />
<script src="${path}/resources/js/boardList.js"></script>
<div id="tc_wrap">
	<div id="tc_title_wrap">
		<div id="tc_title">선생님 자격 심사 목록</div>
	</div>
	<table class="table table-hover">
		<thead class="table-warning">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>자격증빙서류</th>
				<th>자격 심사</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="t" items="${tea}">
				<tr class="tbody_row">
					<td>${t.id}</td>
					<td>${t.name}</td>
					<td>${t.email}</td>
					<td>${t.phoneNumber}</td>
					<td><a href="${path}/thcertiupload/${t.fileName}"
						target="_blank" title="증빙서류 보기">${t.fileName}</a></td>
					<td>
						<form action="/admin/certApproved" method="POST">
							<input type="hidden" value="${t.email}" name="email">
							<button type="submit" onClick="javascript:approve()" value="승인"
								class="btn btn-success">승인</button>
						</form>
						<form action="/admin/certRejected" method="POST">
							<input type="hidden" value="${t.email}" name="email">
							<button type="submit" onClick="javascript:reject()" value="반려"
								class="btn btn-danger">반려</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
