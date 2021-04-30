<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="${path}/resources/css/courseManage.css" />
<div id="" class="m-5 px-5">
	<div id="" class="container">
		<div class="d-flex justify-content-start align-items-center text-secondary">
			<a href="/teacher/course-manage" class="text-secondary h5 text-decoration-none">수업 관리</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
			<a href="/teacher/course-manage/${course.id }" class="text-secondary h5 text-decoration-none">${course.title }</a>
			<i class="fas fa-chevron-right h5 secondary mx-2"></i>
		</div>
		<h2 id="course-${course.id }" class="course-title">${homework.title }</h2>
		<table class="table table-bordered">
		  <tbody>
		    <!-- <tr>
		      <th colspan="1" class="px-3">Title</th>
		      <td colspan="3" class="text-center px-3">${homework.title }</td>
		    </tr>-->
		    <tr>
		      <th scope="row" colspan="1" class="text-center px-3">File</th>
		      <td colspan="1" class="text-center px-3">
			      <c:choose>
	  				<c:when test="${empty homework.filename }">
	  					<p class="text-secondary mb-0">파일 없음</p>
  					</c:when>
  					<c:otherwise>
		      			<i class="fas fa-paperclip"></i> ${homework.filename }
  					</c:otherwise>
 				  </c:choose>
		      </td>
		      <th scope="row" colspan="1" class="text-center px-3">Deadline</th>
		      <td colspan="1" class="text-center px-3">${fn:substring(homework.deadline,0,10) }</td>
		    </tr>
		    <tr>
		      <th colspan="1" class="text-center px-3">Content</th>
		      <td colspan="3" class="px-3" style="white-space: pre-wrap;">${homework.content }</td>
		    </tr>
		  </tbody>
		</table>
		<c:if test="${fn:substring(user_id,0,1)=='2'}">
			<jsp:include page="../student/homeworkAnswer.jsp"/>
		</c:if>
		<c:if test="${fn:substring(user_id,0,1)=='3'}">
	  		<div class="card" style="width: 18rem;">
			  <div class="card-header text-center">제출된 과제 목록</div>
			  <div class="list-group list-group-flush d-flex bd-highlight mb-3">
		  	    <c:forEach var="answer" items="${homeworkAnswerList }" varStatus="status">
				  <a href="" class="list-group-item">
					  <span class="p-2 bd-highlight">제출한 학생 이름</span>
					  <span class="p-2 bd-highlight"><i class="fas fa-paperclip"></i> ${answer.filename }</span>
					  <span class="ms-auto p-2 bd-highlight text-secondary">${answer.createdAt }</span>
				  </a>
		  	    </c:forEach>
  			  </div>
			</div>
		</c:if>
	</div>
</div>