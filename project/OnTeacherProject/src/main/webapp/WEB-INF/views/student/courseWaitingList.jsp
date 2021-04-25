<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/index.css" />
<link rel="stylesheet" href="${path}/resources/bootstrap/css/bootstrap.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${path}/resources/bootstrap/js/bootstrap.js"></script>

<script type="text/javascript">
$(function(){
	$('.cancle_button').click(function(){
		if (confirm("정말 취소하시겠습니까??") == true){    //확인
			//alert($(this).val());
			var selBtnClass = $(this).attr('class').split(' ')[1];
			//alert(selBtnClass);
			$("#"+selBtnClass).get(0).click();  
		    alert("취소되었습니다.");
		}else{   //취소
			return false;
		}
	});
});
</script>

<br>
<br>
<br>

<div class="card text-center">
  <div class="card-header">
    <ul class="nav nav-pills card-header-pills">
      <li class="nav-item">
        <a class="nav-link active" href="">대기중인 수업</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="./courseStudyingList">진행중인 수업</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="./courseEndList">종료된 수업</a>
      </li>
    </ul>
  </div>
  <div class="course-list">
<c:forEach var="course" items="${courses}"> <!-- 여기서 course가 아니라 조인한 새로운 vo 가 나올 수 있어 그러면 수정. -->
    <div class="course-table-div">
		<table class="table table-borderless">
		  <tbody>
		    <tr>
		      <td rowspan="3" ><a href=""><img src="${course.teacher.profileImg}" style="width:80px; height:80px;"/></a></td>
		      <td><a href="">${course.title}</a></td>
		      <td><a href="./applyCancle?courseId=${course.id}" id="${course.id}"></a><input type="button" value="신청취소" class="cancle_button ${course.id}"></td>
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