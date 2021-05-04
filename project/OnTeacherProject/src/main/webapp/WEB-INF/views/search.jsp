<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
	
<head>
	<style>
	h2 {
		text-align: center;
	}
	table {
		width: 50%;
	}
	#outter {
		display: block;
		width: 60%;
		margin: auto;
	}
	a {
		text-decoration: none;
	}
	.center {
    margin: auto;
    width: 50%;
    border: 1px solid black;
    padding: 10px;
    }
    .center2 {
    margin: auto;
    width: 30%;
    border: 1px solid black;
    padding: 10px;
}
</style>
</head>	
	
<body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<div>
	<form action="searchCourse" method="post" id="courseRegForm">
		<div style="display: block; text-align: center;">	
		<table class="center" border="1">
			<tr>
				<td class="thead"><label for="highcategory">수업 카테고리</label></td>
				<td class="tbody"><select required="required" id="highcategory" name="highcategory">
					<option value="">선택해주세요</option>
					<c:forEach var="high" items="${highCategory }">
						<option value="${high.id }">${high.name }</option>
					</c:forEach>
				</select> <select required="required" id="lowcategory" name="lowcategory">
					<!-- jquery에서 option 동적생성 처리 -->
					<option value="">선택해주세요</option>
				</select>
			</tr>
			<tr>
				<td class="thead"><label for="target">수업 대상</label></td>
				<td class="tbody"><select required="required" id="target" name="target">
					<option value="">선택해주세요</option>
					<option value="초등">초등학생</option>
					<option value="중등">중학생</option>
					<option value="고등">고등학생</option>
				</select></td>
			</tr>
			<tr>
				<td class="thead"><label for="isonline">수업 방식</label></td>
				<td class="tbody"><input type="radio" name="isonline" value='1'
					id="online" class="isonline">온라인 <input type="radio" name="isonline"
					value='0' id="offline" class="isonline">오프라인</td>
			</tr>		
				<tr><td class="tbody">
				<button type="submit" id="submit_btn">검색하기</button>
				<td></tr>		
		</table>	
		</div>
	</form>
	<div>
	<p>&nbsp;</p>
	</div>
	
</div>
</body>

<div id="outter">
	<div style="float: right;">
<table>
<tr><td>
<select id="browsers" name="browsers" multiple size="1" required="required">
    <option value="최신순">최신순</option>
    <option value="마감순">마감순</option>
    <option value="인기순">인기순</option>
</select></td></tr>
</table>
</div>


</div>
</body>

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
				//alert(data);
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

<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="boardList?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
<body>
	<div>
		<p>&nbsp;</p>	
	</div>
	
  	<c:forEach var="course" items="${courses}">
		<table class="center2" border="1">
			<tr><td>${course.title}</td></tr>
			<tr><td>${course.location}</td></tr>
			<tr><td>${course.studyDay} ${course.studyTime}</td></tr>
			<tr><td>${course.startDate} ~ ${course.endDate}</td></tr>	
		</table>
	</c:forEach>
<!-- 
<div class="bar">
	    <!-- 이전 페이지 -->
		<c:choose>
			<c:when test="${current eq 1}">
				<a href="CourseList?page=${current}">&laquo;</a>
			</c:when>
			<c:otherwise>
				<a href="CourseList?page=${current-1}">&laquo;</a>
			</c:otherwise>
		</c:choose>
		<!-- 게시판 페이징 숫자클릭 -->
		<c:forEach var="i" begin="1" end="${pageCnt}">
			<a href="CourseList?page=${i}" class="button">${i}</a>
		</c:forEach>
		<!-- 다음 페이지 -->
		<c:choose>
			<c:when test="${current eq cnt}">
				<a href="CourseList?page=${current}">&raquo;</a>
			</c:when>
			<c:otherwise>
				<a href="CourseList?page=${current+1}">&raquo;</a>
			</c:otherwise>
		</c:choose>
	</div> -->	
<!-- 	
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="/boardList?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="/boardList?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="/boardList?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
 -->
</html>