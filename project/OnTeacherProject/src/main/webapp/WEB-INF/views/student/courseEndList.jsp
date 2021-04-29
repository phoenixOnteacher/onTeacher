<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/index.css" />
<link rel="stylesheet" href="${path}/resources/bootstrap/css/bootstrap.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${path}/resources/bootstrap/js/bootstrap.js"></script>
<!-- index.jsp를 복붙해서 만진 jsp -->
<br>
<br>
<br>

<div class="card text-center">
  <div class="card-header">
    <ul class="nav nav-pills card-header-pills">
      <li class="nav-item">
        <a class="nav-link" href="./courseWaitingList">대기중인 수업</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="./courseStudyingList">진행중인 수업</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" href="">종료된 수업</a>
      </li>
    </ul>
  </div>
  <div class="course-list">
<c:forEach var="course" items="${courses}"> <!-- 여기서 course가 아니라 조인한 새로운 vo 가 나올 수 있어 그러면 수정. -->
    <div class="course-table-div">
		<table class="table table-borderless">
		  <tbody>
		    <tr>
		      <td rowspan="3" ><a href=""><img src="/thprofileupload/${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></td>
		      <td><a href="">${course.title}</a> </td>
		      <td><a href="">신청취소</a></td>
		      <td>${course.status}</td>
		    </tr>
		    <tr>
		     <td><a href="">${course.teacher.name } 선생님</a></td>
		      <td></td>
		      <td></td>
		      <td></td>
		    </tr>
		    <tr>
		      <td>${course.startDate} ~ ${course.endDate}</td>
		      <td></td>
		      <td></td>
		      <td></td>
		    </tr>
		  </tbody>
		</table>
	</div>
  </c:forEach>
  </div>
</div>