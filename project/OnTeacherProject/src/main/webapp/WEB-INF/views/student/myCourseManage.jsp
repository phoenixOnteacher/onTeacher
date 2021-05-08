<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<script src="${path }/resources/js/course_tab.js"></script>
<%-- <script src="${path }/resources/js/my_course.js"></script> --%>
<%-- <script src="${path }/resources/js/course_review_write.js"></script> --%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@environment.getProperty('server.address')" var="ipaddress" />
<spring:eval expression="@environment.getProperty('server.port')" var="port" />
<script>
$(function(){
	// 수업 신청 취소
    $('.cancelApplyBtn').click(function () {
	  	var con = confirm("취소하면 복구할 수 없습니다.\n수업 신청을 취소하시겠습니까?");
	  	if (con == true) {
			cancelCourse($(this).val())
		}
    })
	
	function cancelCourse(course_id) {
		$.ajax({
			type: "DELETE",
			url: "http://${ipaddress}:${port}/student/applyCancel?courseId="+course_id,
			success: function() {
				location.reload();
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
		var course_id = $(this).val();
		var content = $("textarea[id='reviewContent"+course_id+"']").val();
	  	if (con == true) {
			console.log(course_id);
			console.log(content);
			writeReview(course_id, content);
		}
    })
	
	function writeReview(course_id, content) {
		$.ajax({
			type: "POST",
			url: "http://${ipaddress}:${port}/student/"+course_id+"/review",
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
		<h1>내 수업</h1>
		<div class="row mt-3">
		
			<nav class="nav flex-column col-3" id="classNav">
				<a class="nav-link active in text-reset" aria-current="page" href="#studying" data-toggle="tab" data-load="true">진행 중인 수업</a>
				<a class="nav-link text-reset" href="#match" data-toggle="tab" data-load="false">대기 중인 수업</a>
				<a class="nav-link text-reset" href="#end" data-toggle="tab" data-load="false">종료된 수업</a>
			</nav>
			
			<div class="tab-content col-9">
			
				<div class="tab-pane fade show active" id="studying"> <!-- 진행 중인 수업 리스트 조회 -->
				  	<c:forEach var="course" items="${studyingList }">
						<div class="card m-2">
					
						  <h5 class="card-header py-3">
							  	<a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						  </h5>
						  <div class="card-body">
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
   						    <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${course.location }</p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
						  </div>
						</div>
				  	</c:forEach>
				</div>
				
				
				<div class="tab-pane fade" id="match"> <!-- 대기중인 수업 목록 조회 -->
					
					<c:forEach var="course" items="${matchingList }"> <!-- 매칭대기 조회 -->
						<div class="card m-2">
						  <h5 class="card-header p-3">
						  	<a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						  	<small class="btn btn-primary float-end btn-sm">매칭 대기</small>
						  	<button type="button" class="btn btn-danger btn-sm float-end mx-2 cancelApplyBtn" value="${course.id }">신청 취소</button>
					  	  </h5>
						  <div class="card-body">
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${course.location }</p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
						  </div>
						</div>
				  	</c:forEach>
				  	
				  	<c:forEach var="course" items="${matchedList }">  <!-- 매칭완료 조회 -->
						<div class="card m-2">
						  <h5 class="card-header p-3">
						  	<a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle" id="title-${course.id }">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
						  	<small class="btn btn-secondary float-end btn-sm mx-2">매칭 완료</small>
						  </h5>
						  <div class="card-body">
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${course.location }</p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
						  </div>
						</div>
				  	</c:forEach>
				</div>
				
				
				<div class="tab-pane fade" id="end"> <!-- 종료된 수업 목록 조회 -->
					<c:forEach var="course" items="${endList }">
						<div class="card m-2">
						  <div class="card-header p-3 h5"><a href="/student/course-manage/${course.id }" class="fw-bold text-decoration-none align-middle">${course.title }<i class="fas fa-chevron-right ms-1"></i></a>
							  <div class="form-check float-end">
							        	<c:choose>
							        		<c:when test="${empty course.teacher.courseReview }">
									        	<button class="btn btn-success btn-sm"  data-bs-toggle="modal" data-bs-target="#reviewModal${course.id }" name="unreviewed">후기 작성</button>
							        		</c:when>
							        		<c:otherwise>
									        	<button class="btn btn-secondary btn-sm"  data-bs-toggle="modal" data-bs-target="#reviewModal${course.id }">후기 확인</button>
							        		</c:otherwise>
							        	</c:choose>
										<!-- Modal -->
										<div class="modal fade" id="reviewModal${course.id }" tabindex="-1" aria-labelledby="reviewModalLabel${course.id }" aria-hidden="true">
										  <div class="modal-dialog modal-dialog-centered">
										    <div class="modal-content">
										      <div class="modal-header">
										        <h5 class="modal-title" id="reviewModalLabel${course.id }"><b>${course.title}</b> 수업 후기</h5>
										        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										      </div>
										      <div class="modal-body">
										        <div class="mb-3">
										        	<c:choose>
										        		<c:when test="${empty course.teacher.courseReview }">
														  <label for="reviewContent" class="form-label h6">${course.title } 수업은 어땠나요?</label>
														  <textarea class="form-control" id="reviewContent${course.id }" rows="4"></textarea>
										        		</c:when>
										        		<c:otherwise>
												          <p class="h5" style="white-space: pre-wrap;">${course.teacher.courseReview.content }</p>
												          <small class="text-secondary float-end h6">작성 날짜 : ${course.teacher.courseReview.createdAt }</small>
										        		</c:otherwise>
										        	</c:choose>
												</div>
										      </div>
							        		  <c:if test="${empty course.teacher.courseReview }">
											      <div class="modal-footer">
											        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
											        <button type="button" class="btn btn-primary writeReviewBtn" value="${course.id}">작성 완료</button>
											      </div>
							        		  </c:if>
										    </div>
										  </div>
										</div>
									</div>
						  </div>
							 
							<div class="card-body">
						    <p class="card-text"><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i><a href="">${course.teacher.name } 선생님</a></p>
						    <p class="card-text"><i class="fas fa-map-marker-alt"></i> ${course.location }</p>
						    <p class="card-text"><i class="far fa-clock"></i> ${course.studyDay } ${course.studyTime }</p>
						    <p class="card-text"><i class="far fa-calendar"></i> ${course.startDate } ~ ${course.endDate }</p>
					  	    </div>
						</div>
				  	</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>