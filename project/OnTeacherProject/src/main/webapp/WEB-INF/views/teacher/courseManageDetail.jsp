<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_tab.js"></script>
<%-- <script src="${path }/resources/js/matching.js"></script> --%>
<script src="${path }/resources/js/review_write.js"></script>
<spring:eval expression="@environment.getProperty('ipaddress')" var="ipaddress" />
<spring:eval expression="@environment.getProperty('server.port')" var="port" />
<script>
$(function(){
	// 매칭 선택 버튼 처리
    $(".btn-check").click(function(){
		var studentId = this.id.substring(10);
        var isChecked = $(this).is(":checked");
        if (isChecked) {
			$("#btn-check-"+studentId).prop('checked', true);
			$("label[for='btn-check-"+studentId+"']").text('선택 취소');
		} else {
			$("#btn-check-"+studentId).prop('checked', false);
			$("label[for='btn-check-"+studentId+"']").text('선택');
		}
    });
	
	// 매칭하기
	$('#matchingBtn').click(function () {
	  	var con = confirm("매칭이 완료되면 더 이상 학생을 받을 수 없습니다.\n매칭을 진행하시겠습니까?");
	  	if (con == true) {
			var selectedStudents = [];
			$("input[name='selectedStudent']:checked").each(function() {
				selectedStudents.push($(this).val());
			});
			jQuery.ajaxSettings.traditional = true;
			$.ajax({
				type: "POST",
				url: "http://${ipaddress}:${port}/teacher/"+$(this).val()+"/matching",
				dataType: "json",
				data: JSON.stringify({
					"selectedStudents": selectedStudents
				}),
				contentType: "application/json; charset=UTF-8",
				complete: function() {
					alert("매칭이 완료되었습니다.");
					location.reload();
				}
			})
		}
    });

	// 매칭 취소
	$('.cancelMatchingBtn').click(function () {
	  	var con1 = confirm("매칭을 취소하면 복구할 수 없습니다.\n매칭을 취소하시겠습니까?");
		var con2 = null;
	  	if (con1) {
			if ($('.cancelMatchingBtn').length==1) {
				con2 = confirm("매칭된 학생 인원이 0명이 되어, 수업이 취소 처리되고 복구할 수 없습니다. \n매칭을 취소하시겠습니까?");
			}
			if (con2!=false) {
				var course_id = $("h2[class='course-title']").attr('id').substring(7);
				console.log(course_id);
				cancelMatching(course_id,$(this).val(),con2)
			}
		}
    })
	
	function cancelMatching(course_id,student_id,con2) {
		$.ajax({
			type: "DELETE",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/matching",
			dataType: "json",
			data: JSON.stringify({
				"studentId": student_id
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				alert("매칭이 취소되었습니다.");
				if (con2==null) {
					location.reload();
				} else {
					window.location.replace("http://${ipaddress}:${port}/teacher/course-manage");
				}
			}
		})
	}
});
</script>
<script>
$(function(){
	if ($("button[name='unreviewed']").length==0) {
		$("#unreviewedAlert").hide();
	}
	
	
	// 학생 리뷰 작성
    $('.writeReviewBtn').click(function () {
	  	var con = confirm("후기 작성을 완료하시겠습니까?");
		var course_id = $("h2[class='course-title']").attr('id').substring(7);
		var student_id = $(this).val();
		var content = $("textarea[id='reviewContent"+course_id+student_id+"']").val();
	  	if (con == true) {
			writeReview(course_id, student_id, content);
		}
    })
	
	function writeReview(course_id, student_id, content) {
		$.ajax({
			type: "POST",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/review/"+student_id,
			dataType: "json",
			data: JSON.stringify({
				"content": content
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				alert("후기 작성이 완료되었습니다.");
				location.reload();
			}
		})
	}
});
</script>
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
			  	  <a class="nav-link text-reset position-relative" href="#endStudent" data-toggle="tab" data-load="false">수강한 학생<span id="unreviewedAlert" class="position-absolute top-10 start-90 translate-middle badge border border-light rounded-circle bg-danger p-1 mx-2"><span class="visually-hidden">unread messages</span></span></a>
			  	  <a class="nav-link text-reset" href="#homework" data-toggle="tab" data-load="false">과제</a>
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
						        	<c:choose>
						        		<c:when test="${empty student.studentReview }">
								        	<button class="btn btn-success btn-sm"  data-bs-toggle="modal" data-bs-target="#reviewModal${course.id }${student.id }" name="unreviewed">후기 작성</button>
						        		</c:when>
						        		<c:otherwise>
								        	<button class="btn btn-secondary btn-sm"  data-bs-toggle="modal" data-bs-target="#reviewModal${course.id }${student.id }">후기 확인</button>
						        		</c:otherwise>
						        	</c:choose>
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
									        	<c:choose>
									        		<c:when test="${empty student.studentReview }">
													  <label for="reviewContent" class="form-label h6">${student.name } 학생은 어땠나요?</label>
													  <textarea class="form-control" id="reviewContent${course.id }${student.id }" rows="4"></textarea>
									        		</c:when>
									        		<c:otherwise>
											          <p class="h5" style="white-space: pre-wrap;">${student.studentReview.content }</p>
											          <small class="text-secondary float-end h6">작성 날짜 : ${student.studentReview.createdAt }</small>
									        		</c:otherwise>
									        	</c:choose>
											</div>
									      </div>
						        		  <c:if test="${empty student.studentReview }">
										      <div class="modal-footer">
										        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
										        <button type="button" class="btn btn-primary writeReviewBtn" value="${student.id }">작성 완료</button>
										      </div>
						        		  </c:if>
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
				<jsp:include page="../common/courseDetail.jsp"/>
			  </div>
			  <div class="tab-pane fade" id="homework">
			  	<c:if test="${course.status=='studying' }">
					<a href="/teacher/course-manage/${course.id }/homework" class="btn btn-primary float-end">과제 내기</a>
			  	</c:if>
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
<script>
$(function(){
	if ($("button[name='unreviewed']").length==0) {
		$("#unreviewedAlert").hide();
	}
	
	// 학생 리뷰 작성
    $('.writeReviewBtn').click(function () {
	  	var con = confirm("후기 작성을 완료하시겠습니까?");
		var course_id = $("h2[class='course-title']").attr('id').substring(7);
		var student_id = $(this).val();
		var content = $("textarea[id='reviewContent"+course_id+student_id+"']").val();
	  	if (con == true) {
			writeReview(course_id, student_id, content);
		}
    })
	
	function writeReview(course_id, student_id, content) {
		$.ajax({
			type: "POST",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/review/"+student_id,
			dataType: "json",
			data: JSON.stringify({
				"content": content
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				alert("후기 작성이 완료되었습니다.");
				location.reload();
			}
		})
	}

	// 매칭하기
	$('#matchingBtn').click(function () {
	  	var con = confirm("매칭이 완료되면 더 이상 학생을 받을 수 없습니다.\n매칭을 진행하시겠습니까?");
	  	if (con == true) {
			var selectedStudents = [];
			$("input[name='selectedStudent']:checked").each(function() {
				selectedStudents.push($(this).val());
			});
			jQuery.ajaxSettings.traditional = true;
			$.ajax({
				type: "POST",
				url: "http://${ipaddress}:${port}/teacher/"+$(this).val()+"/matching",
				dataType: "json",
				data: JSON.stringify({
					"selectedStudents": selectedStudents
				}),
				contentType: "application/json; charset=UTF-8",
				complete: function() {
					alert("매칭이 완료되었습니다.");
					location.reload();
				}
			})
		}
    });

	// 매칭 취소
	$('.cancelMatchingBtn').click(function () {
	  	var con1 = confirm("매칭을 취소하면 복구할 수 없습니다.\n매칭을 취소하시겠습니까?");
		var con2 = null;
	  	if (con1) {
			if ($('.cancelMatchingBtn').length==1) {
				con2 = confirm("매칭된 학생 인원이 0명이 되어, 수업이 취소 처리되고 복구할 수 없습니다. \n매칭을 취소하시겠습니까?");
			}
			if (con2!=false) {
				var course_id = $("h2[class='course-title']").attr('id').substring(7);
				cancelMatching(course_id,$(this).val(),con2)
			}
		}
    })
	function cancelMatching(course_id,student_id,con2) {
		$.ajax({
			type: "DELETE",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/matching",
			dataType: "json",
			data: JSON.stringify({
				"studentId": student_id
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				alert("매칭이 취소되었습니다.");
				if (con2==null) {
					location.reload();
				} else {
					window.location.replace("http://${ipaddress}:${port}/teacher/course-manage");
				}
			}
		})
	}
});
</script>