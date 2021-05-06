<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/search.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<div id="s_wrap">
	<div id="searchbox">
		<div id="s_title_wrap">
			<div id="s_title">수업검색</div>
		</div>
		<form action="searchCourse" method="post" id="courseSearchForm">
			<table id="s_table">
				<tr>
					<td class="thead"><label for="highcategory">수업 분류</label></td>
					<td class="tbody"><select required="required"
						id="highcategory" name="highcategory"
						class="form-select form-select-sm">
							<option value="">상위 분류 선택</option>
							<c:forEach var="high" items="${highCategory }">
								<option value="${high.id }">${high.name }</option>
							</c:forEach>
					</select> <select required="required" id="lowcategory" name="lowcategory"
						class="form-select form-select-sm">
							<!-- jquery에서 option 동적생성 처리 -->
							<option value="">하위 분류 선택</option>
					</select>
				</tr>
				<tr>
					<td class="thead"><label for="target">수업 대상</label></td>
					<td class="tbody"><select required="required" id="target"
						name="target" class="form-select form-select-sm">
							<option value="">선택해주세요</option>
							<option value="초등">초등학생</option>
							<option value="중등">중학생</option>
							<option value="고등">고등학생</option>
					</select></td>
				</tr>
				<tr>
					<td class="thead"><label for="isonline">수업 방식</label></td>
					<td class="tbody"><input type="radio" name="isonline"
						value='1' id="online" class="isonline">&nbsp;온라인&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="radio" name="isonline" value='0' id="offline"
						class="isonline">&nbsp;오프라인&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
			<div id="s_btn_wrap">
				<button type="submit" id="submit_btn" class="btn btn-primary">검색하기</button>
			</div>
		</form>
	</div>
	<div id="searchlist">
		<div class="card mb-3">
			<c:forEach var="course" items="${courses}">
				<div class="row g-0">
					<div class="col-md-4">
						<img src="/thprofileupload/${course.teacher.profileImg}"
							class="l_thimg">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<p class="card-title tcard">
								<a href="">${course.teacher.name } 선생님</a>
							</p>
							<h5 class="card-title ccard">
								<c:choose>
									<c:when test="${fn:substring(sessionScope.id,0,1)=='2'}">
										<!-- 학생인 경우 수업상세+신청 -->
										<a href="/student/searchCourse/apply?courseId=${course.id }">${course.title}</a>
									</c:when>
									<c:otherwise>
										<a href="/searchCourse/detail?courseId=${course.id }">${course.title}</a>
									</c:otherwise>
								</c:choose>
							</h5>
							<p class="card-text">${course.location}${course.studyDay}
								${course.studyTime}</p>
							<p class="card-text">
								<small class="text-muted">${course.startDate} ~
									${course.endDate}</small>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<a href="#" class="gototop"><i class="fas fa-arrow-up"></i><br>TOP</a>
</div>
<script>
$(function() {
	var select = '<option value="">하위 분류 선택</option>';

	/* 수업 카테고리 선택 */
	$("#highcategory").change(function() {
		$("#lowcategory").empty().append(select);
		if ($("#highcategory").val() == "") { //select의 value가 ""이면 "선택해주세요"만 보여주도록
			$("#lowcategory").append(select);
		} else {
			comboChange($(this).val());
		}
	});

	function comboChange(highcategoryid) {
		$.ajax({
			type: "GET",
			url: "http://localhost:8090/teacher/highcategory",
			dataType: "json",
			data: { high_category_id: highcategoryid },
			contentType: "application/json; charset=UTF-8",
			success: function(data) {
				if (data.length == 0) {
					$("#lowcategory").append('<option value="">하위 분류 선택</option>');				
				} else {
					$(data).each(function(i, item) {
						$("#lowcategory").append("<option value='" + item.id + "'>" + item.name + "</option>");
						if('${lowcategory_id}') $('#lowcategory').val('${lowcategory_id}');						
					});
				}
			}
		});
	}
	if('${highcategory_id}'){
  		$('#highcategory').val('${highcategory_id}').trigger('change');
  	}
	if('${target}') $('#target').val('${target}');
	
	if('${isonline}') {
    	var onlineradio = $('.isonline');
    	$.each(onlineradio, function(index,radio) {
			if($(radio).val() == '${isonline}') {
				$(radio).attr("checked",true); 
			}
		});
    }

	$('#submit_btn').on('click', function() {
		if ($('#highcategory').val() == "") {
			alert('수업 카테고리를 선택해주세요');
			return false;
		}
		if ($('#lowcategory').val() == "") {
			alert('수업 카테고리를 선택해주세요');
			return false;	
		}
		if ($('#target').val() == "") {
			alert('수업 대상을 선택해주세요');
			return false;	
		}		
		if(!$('#online').is(':checked') && !$('#offline').is(':checked')) {
			alert('수업 방식을 선택해주세요');
			return false;	
		}
	})
});
</script>