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
					<td><a href="certiDownload?fileName=${t.fileName}"
						title="증빙서류 보기">${t.description}</a></td>
					<td>
						<form action="/admin/certApproved" method="POST">
							<input type="hidden" value="${t.email}" name="email">
							<button type="submit" onClick="javascript:approve()" value="승인"
								class="btn btn-success">승인</button>
						</form>
						<form action="/admin/certRejected" method="POST">
							<input type="hidden" value="${t.email}" name="email">
							<button type="button" class="btn btn-danger"
								data-bs-toggle="modal" data-bs-target="#rejectModal${t.id}">
								반려</button>

							<!-- Modal -->
							<div class="modal fade" id="rejectModal${t.id}" tabindex="-1"
								aria-labelledby="rejectModalLabel${t.id}" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="rejectModalLabel${t.id}">반려 사유 작성</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<div class="mb-3">
												<label for="exampleFormControlTextarea1"
													class="form-label float-start h6">어떤 점이 부족한지 적어주세요!<small
													class="text-secondary ms-2">(해당 내용은 선생님에게 전달됩니다.)</small></label>
												<textarea class="form-control"
													id="exampleFormControlTextarea1" rows="2" name="message"
													required></textarea>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">닫기</button>
											<button type="submit" onClick="javascript:reject()"
												value="반려" class="btn btn-primary">확인</button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
