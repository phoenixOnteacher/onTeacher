<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_tab.js"></script>
<script src="${path }/resources/js/matching.js"></script>
<div id="" class="m-5 px-5">
	<div id="" class="container">
		<div class="d-flex justify-content-start align-items-center text-secondary">
			<a href="/teacher/course-manage" class="h5 text-decoration-none text-reset">수업 관리</a>
			<i class="fas fa-chevron-right h5 mx-2"></i>
		</div>
		<h2 id="course-${course.id }" class="course-title">${course.title }</h2>
		<div class="row mt-3">
			<nav class="nav flex-column col-2" id="manageNav">
			  <a class="nav-link active in text-reset" aria-current="page" href="#detail" data-toggle="tab" data-load="true">수업 상세</a>
			  <c:choose>
			  	<c:when test="${course.status=='studying'}">
				  <a class="nav-link text-reset" href="#studyingStudent" data-toggle="tab" data-load="false">학생 관리</a>
				  <a class="nav-link text-reset" href="#homework" data-toggle="tab" data-load="false">과제</a>
			  	</c:when>
			  	<c:when test="${course.status=='end'}">
			  	  <a class="nav-link text-reset" href="#endStudent" data-toggle="tab" data-load="false">수강한 학생</a>
			  	</c:when>
			  	<c:otherwise>
			  	  <a class="nav-link text-reset" href="#matchingStudent" data-toggle="tab" data-load="false">신청한 학생</a>
			  	</c:otherwise>
			  </c:choose>
			</nav>
			<div class="tab-content col-10">
			  <div class="tab-pane fade" id="studyingStudent">
			  	<c:forEach var="student" items="${students }">
			    	<div class="card mb-3 mx-auto" style="max-width: 40vw;">
					  <div class="row g-0">
					    <div class="col-md-4">
					      <img src="" alt="...">
					    </div>
					    <div class="col-md-8">
					      <div class="card-body">
					        <h5 class="card-title h4 mb-3">${student.name } 학생</h5>
					        <p class="card-text"><i class="fas fa-phone"></i> ${student.phoneNumber }</p>
					        <p class="card-text"><i class="fas fa-envelope"></i> ${student.email }</p>
					        <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${student.address }</p>
					        <p class="card-text"><i class="fas fa-user"></i> ${student.grade } ${student.gender }자</p>
					        <p class="card-text"><i class="fas fa-birthday-cake"></i> ${student.birthday }</p>
					      </div>
					    </div>
					  </div>
					</div>
		    	</c:forEach>
			  </div>
			  <div class="tab-pane fade" id="matchingStudent">
			  	<c:forEach var="student" items="${students }">
			    	<div class="card mb-3 mx-auto" style="max-width: 40vw;">
					  <div class="row g-0">
					    <div class="col-md-4">
					      <img src="" alt="...">
					    </div>
					    <div class="col-md-8">
					      <div class="card-body">
					        <div class="card-title h4 mb-3">${student.name } 학생
					        	<c:choose>
					        		<c:when test="${course.status=='matching' }">
								        <div class="form-check float-end">
										  <input type="checkbox" class="btn-check" name="selectedStudent" value="${student.id }" id="btn-check-${student.id }" autocomplete="off">
										  <label class="btn btn-outline-primary btn-sm" for="btn-check-${student.id }">선택</label>
										</div>
					        		</c:when>
					        		<c:otherwise>
					        			<div class="form-check float-end">
								        	<button class="btn btn-danger btn-sm cancelMatchingBtn" value="${student.id }">매칭 취소</button>
										</div>
					        		</c:otherwise>
					        	</c:choose>
					        </div>
					        <p class="card-text"><i class="fas fa-phone"></i> ${student.phoneNumber }</p>
					        <p class="card-text"><i class="fas fa-envelope"></i> ${student.email }</p>
					        <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${student.address }</p>
					        <p class="card-text"><i class="fas fa-user"></i> ${student.grade } ${student.gender }자</p>
					        <p class="card-text"><i class="fas fa-birthday-cake"></i> ${student.birthday }</p>
					      </div>
					    </div>
					  </div>
					</div>
		    	</c:forEach>
		    	<c:if test="${course.status=='matching' }">
		    		<div class="d-flex justify-content-center"><button id="matchingBtn" class="btn btn-primary" value="${course.id }">매칭하기</button></div>
		    	</c:if>
			  </div>
			  <div class="tab-pane fade" id="endStudent">
			  	<c:forEach var="student" items="${students }">
			    	<div class="card mb-3 mx-auto" style="max-width: 40vw;">
					  <div class="row g-0">
					    <div class="col-md-4">
					      <img src="" alt="...">
					    </div>
					    <div class="col-md-8">
					      <div class="card-body">
					        <div class="card-title h4 mb-3">${student.name } 학생
						        <div class="form-check float-end">
						        	<button id="writeReviewBtn${course.id }${student.id }" class="btn btn-success btn-sm"  data-bs-toggle="modal" data-bs-target="#reviewModal${course.id }${student.id }" value="${student.id }">후기 작성</button>
									<!-- Modal -->
									<div class="modal fade" id="reviewModal${course.id }${student.id }" tabindex="-1" aria-labelledby="reviewModalLabel${course.id }${student.id }" aria-hidden="true">
									  <div class="modal-dialog modal-dialog-centered">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="reviewModalLabel${course.id }${student.id }"><b>${course.title}</b> 수업 후기</h5>
									        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									      </div>
									      <div class="modal-body">
									        <div class="mb-3">
											  <label for="reviewContent" class="form-label h6">${student.name } 학생은 어땠나요?</label>
											  <textarea class="form-control" id="reviewContent${course.id }${student.id }" rows="4"></textarea>
											</div>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
									        <button type="button" class="btn btn-primary">작성 완료</button>
									      </div>
									    </div>
									  </div>
									</div>
								</div>
					        </div>
					        <p class="card-text"><i class="fas fa-phone"></i> ${student.phoneNumber }</p>
					        <p class="card-text"><i class="fas fa-envelope"></i> ${student.email }</p>
					        <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${student.address }</p>
					        <p class="card-text"><i class="fas fa-user"></i> ${student.grade } ${student.gender }자</p>
					        <p class="card-text"><i class="fas fa-birthday-cake"></i> ${student.birthday }</p>
					      </div>
					    </div>
					  </div>
					</div>
		    	</c:forEach>
			  </div>
			  <div class="tab-pane fade show active" id="detail">
				<h1>상세 페이지</h1>
			  </div>
			  <div class="tab-pane fade" id="homework">
				<a href="${course.id }/homework" class="btn btn-primary float-end">과제 내기</a>
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
					      <th scope="row">${status.count} </th>
					      <td>
					      	<div class="position-relative p-0">
					      		<a href="/homework/${homework.id }" class="text-decoration-none text-reset stretched-link p-3">${homework.title }</a>
				      		</div>
			      		  </td>
					      <td>${homework.deadline }</td>
					    </tr>
				  	</c:forEach>
				  </tbody>
				</table>
			  </div>
			</div>
		</div>
	</div>
</div>