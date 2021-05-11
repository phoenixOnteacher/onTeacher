<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" href="${path}/resources/css/course_manage.css" />
<script src="${path }/resources/js/course_tab.js"></script>
<script src="${path }/resources/js/matching.js"></script>
<spring:eval expression="@environment.getProperty('ipaddress')" var="ipaddress" />
<spring:eval expression="@environment.getProperty('server.port')" var="port" />

<div id="cm-wrap" class="m-5 px-5">
	<div id="" class="container">
		<div class="d-flex justify-content-start align-items-center text-secondary course-manage-menu">
			<a href="/teacher/course-manage" class="h5 text-decoration-none text-reset course-manage-menu">수업 관리</a>
			<i class="fas fa-chevron-right h5 mx-2"></i>
		</div>
		<h2 id="course-${course.id }" class="course-title" class="course-manage-menu" data-min-student="${course.minStudent }" data-max-student="${course.maxStudent }">${course.title }</h2>
		<div class="row mt-3">
			<nav class="nav flex-column col-2 course-manage-menu" id="manageNav">
			  <a class="nav-link active in text-reset" aria-current="page" href="#detail" data-toggle="tab" data-load="true">수업 상세</a>
			  <a class="nav-link text-reset position-relative" href="#students" data-toggle="tab" data-load="false">
			  	<c:choose>
			  		<c:when test="${course.status=='studying'}">학생 관리</c:when>
			  		<c:when test="${course.status=='end'}">수강한 학생<span id="unreviewedAlert" class="position-absolute top-10 start-90 translate-middle badge border border-light rounded-circle bg-danger p-1 mx-2"><span class="visually-hidden">unread messages</span></span></c:when>
			  		<c:otherwise>신청한 학생</c:otherwise>
			  	</c:choose>
			  </a>
		  	  <c:if test="${course.status=='studying' || course.status=='end'}">
			  	<a class="nav-link text-reset" href="#homework" data-toggle="tab" data-load="false">과제</a>
		  	  </c:if>
			</nav>
			<div class="tab-content col-10">
			  <div class="tab-pane fade" id="students">
			  	<c:forEach var="student" items="${students }">
			    	<div class="card mb-3 mx-auto st-prof-card">
					  <div class="row g-0">
					    <div class="col-lg-4">
					      <img src="/fileview/stprofile/${student.profileImg}" alt="${student.name } 학생 프로필 사진" class="st-profImg card-img-top">
					    </div>
					    <div class="col-lg-8">
					      <div class="card-body">
					        <div class="card-title h4 mb-3"><a href="/student/studentDetail?studentId=${student.id}" class="user-detail-link">${student.name } 학생</a>
					        	<c:choose>
					        		<c:when test="${course.status=='matching' }">
								        <div class="form-check float-end">
										  <input type="checkbox" class="btn-check" name="selectedStudent" value="${student.id }" id="btn-check-${student.id }" autocomplete="off">
										  <label class="btn btn-outline-primary btn-sm" for="btn-check-${student.id }">선택</label>
										</div>
					        		</c:when>
					        		<c:when test="${course.status=='matched' }">
					        			<div class="form-check float-end">
								        	<button class="btn btn-warning btn-sm cancelMatchingBtn" value="${student.id }">매칭 취소</button>
										</div>
					        		</c:when>
					        		<c:when test="${course.status=='end' }">
					        			<div class="form-check float-end">
								        	<c:choose>
								        		<c:when test="${empty student.studentReview }">
										        	<button class="btn btn-warning btn-sm"  data-bs-toggle="modal" data-bs-target="#reviewModal${course.id }${student.id }" name="unreviewed">후기 작성</button>
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
					        		</c:when>
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
		    		<c:choose>
		    			<c:when test="${empty students }">
		    				<p class="text-center mt-5 empty-text text-secondary">아직 신청한 학생이 없어요!</p>
		    			</c:when>
		    			<c:otherwise>
				    		<div class="d-flex justify-content-center"><button id="matchingBtn" class="btn btn-primary" value="${course.id }">매칭하기</button></div>
		    			</c:otherwise>
		    		</c:choose>
		    	</c:if>
			  </div>
			  <div class="tab-pane fade show active" id="detail">
				<jsp:include page="../common/courseDetail.jsp"/>
			  </div>
			  <div class="tab-pane fade" id="homework">
			  	<c:if test="${course.status=='studying' }">
					<a href="/teacher/course-manage/${course.id }/homework" class="btn btn-warning float-end">과제 내기</a>
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
		var course_id = $("h2[class='course-title']").attr('id').substring(7);
		var student_id = $(this).val();
		var content = $("textarea[id='reviewContent"+course_id+student_id+"']").val();
    	swal({
		  text: "후기 작성을 완료하시겠습니까?",
		  buttons: true,
		})
		.then((willSubmit) => {
		  if (willSubmit) {
		    writeReview(course_id, student_id, content);
		  }
		});
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
				swal({
				  title: "후기 작성 완료",
				  text: "다른 선생님들에게 큰 도움이 될 거예요!\n감사합니다 :)",
				  icon: "success",
				  button: "OK!",
				})
				.then((value) => {
					location.reload();
				})
			}
		})
	}

	// 매칭하기
	$('#matchingBtn').click(function () {
		var course_id = $(this).val();
		swal({
		  text: "매칭이 완료되면 더 이상 학생을 받을 수 없습니다.\n매칭을 진행하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		})
		.then((willMatching) => {
		  if (willMatching) {
		    matching(course_id);
		  }
		});
    });
    
    function matching(course_id) {
		var selectedStudents = [];
		$("input[name='selectedStudent']:checked").each(function() {
			selectedStudents.push($(this).val());
		});
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
			type: "POST",
			url: "http://${ipaddress}:${port}/teacher/" + course_id + "/matching",
			dataType: "json",
			data: JSON.stringify({
				"selectedStudents": selectedStudents
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				swal({
				  title: "매칭 완료",
				  text: "이제 수업을 시작할 수 있어요!",
				  icon: "success",
				  button: "OK!",
				})
				.then((value) => {
					location.reload();
				})
			}
		})
	}

	// 매칭 취소
	$('.cancelMatchingBtn').click(function () {
		var student_id = $(this).val();
		var minStudent = $(".course-title").data("minStudent");
		var course_id = $("h2[class='course-title']").attr('id').substring(7);
		if (minStudent == 0) {
			minStudent = 1;
		}
		console.log(minStudent);
		if ($('.cancelMatchingBtn').length==minStudent) {
			swal({
			  text: "매칭된 학생 인원이 최소 인원보다 적어 수업이 취소 처리됩니다.\n매칭을 취소하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((res) => {
				console.log('최소인원');
				console.log(res);
				if (res) {
					cancelMatching(course_id,student_id,null);
				}
			});
		} else {
			swal({
			  text: "매칭을 취소하면 복구할 수 없습니다.\n매칭을 취소하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((res) => {
				console.log('그냥 취소');
				console.log(res);
				if (res) {
					cancelMatching(course_id,student_id,true);
				}
			});
		}
    })
    
	function cancelMatching(course_id,student_id,con) {
		$.ajax({
			type: "DELETE",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/matching",
			dataType: "json",
			data: JSON.stringify({
				"studentId": student_id
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				swal("매칭이 취소되었습니다.")
				.then(() => {
					if (con==null) {
						console.log('수업 취소됨');
						window.location.replace("http://${ipaddress}:${port}/teacher/course-manage");
					} else {
						console.log('수업 취소됨');
						location.reload();
					}
				});
			}
		})
	}
});
</script>