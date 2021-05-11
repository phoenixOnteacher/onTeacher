<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/user_detail.css" />

<div id="ud-wrap" class="m-5 px-5">
	<div id="" class="container px-5">
		<div class="card mb-3 border-0 p-5">
			<div class="row g-0">
				<div class="col-4">
					<c:choose>
						<c:when test="${empty student.profileImg}">
							<img src="${path}/resources/img/logo.png" class="profImg">
						</c:when>
						<c:otherwise>
							<img src="/fileview/stprofile/${student.profileImg}" class="profImg">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-8">
					<div class="card-body">
						<h3 class="card-title user-name">${student.name} 학생</h3>
						<p class="card-text user-introduction">${student.introduction}</p>	
					</div>
				</div>
			</div>
		</div>
		<div class="">
			<div class="review-list-title">후기</div>
			<c:choose>
				<c:when test="${empty student.studentReviewList }">
					<p class="text-center">아직 후기가 없어요!</p>
				</c:when>
				<c:otherwise>
					<c:forEach items="${student.studentReviewList }" var="studentReview">
						<div class="card m-3">
						  <div class="card-header p-3">
						  	<a href="/searchCourse/detail?courseId=${studentReview.courseId }" class="fw-bold text-decoration-none align-middle h5">${studentReview.courseName }<i class="fas fa-chevron-right ms-1"></i></a>
						  </div>
						  <div class="card-body p-4">
						    <h5 class="card-title">${studentReview.content }</h5>
						    <p class="card-text text-secondary float-end">${studentReview.createdAt } 작성</p>
						  </div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>