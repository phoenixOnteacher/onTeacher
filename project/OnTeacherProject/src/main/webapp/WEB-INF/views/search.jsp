<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수업 검색</title>

</head>
<body>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path }/resources/js/course_register.js"></script>
<div id="cr_wrap">
	<form action="#" method="post" id="courseRegForm">
		<table border="1">
			<tr>
				<td><label for="highcategory">수업 카테고리</label></td>
				<td><select required="required" id="highcategory">
						<option value="">선택해주세요</option>
						<c:forEach var="high" items="${highCategory }">
							<option value="${high.id }">${high.name }</option>
						</c:forEach>
				</select> <select required="required" id="lowcategory">
						<!-- jquery에서 option 동적생성 처리 -->
						<option value="">선택해주세요</option>
				</select>
			</tr>
			<tr>
				<td><label for="target">수업 대상</label></td>
				<td><select required="required" id="target">
						<option value="">선택해주세요</option>
						<option value="초등학생">초등학생</option>
						<option value="중학생">중학생</option>
						<option value="고등학생">고등학생</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="classform">수업 형태</label></td>
				<td><input type="radio" name="classform" value="oneday"
					id="classform">1일 특강 <input type="radio" name="classform"
					value="multi" id="classform">다회차 수업</td>
			</tr>
			<tr>
				<td><label for="classdate">수업 일정</label></td>
				<td><input type="date" id="classdate" name="classstart"
					min="2000-01-01" max="2999-12-31"> <input type="date"
					id="classdate" name="classend" min="2000-01-01" max="2999-12-31"></td>
			</tr>
			<tr>
				<td><label for="isweekend">수업 요일</label></td>
				<td><input type="radio" name="isweekend" id="isweekend"
					value="weekdays">주중<input type="radio" name="isweekend"
					id="isweekend" value="weekend">주말<input type="radio"
					name="isweekend" id="isweekend" value="anytime">주중+주말</td>
			</tr>
			<tr>
				<td><label for="ismorning">수업 시간</label></td>
				<td><input type="radio" name="ismorning" id="ismorning"
					value="morning">오전<input type="radio" name="ismorning"
					id="ismorning" value="afternoon">오후<input type="radio"
					name="ismorning" id="ismorning" value="discuss">협의</td>
			</tr>
			<tr>
				<td><label for="isonline">수업 방식</label></td>
				<td><input type="radio" name="isonline" value="online"
					id="isonline">온라인 <input type="radio" name="isonline"
					value="offline" id="isonline">오프라인</td>
			</tr>
			<tr>
				<td><label for="isgroup">참여인원</label></td>
				<td><input type="radio" name="isgroup" value="onetoone"
					id="isgroup">1:1수업 <input type="radio" name="isgroup"
					value="group" id="isgroup">그룹수업<input name="mingroup"
					type="number" id="mingroup" min="2" /> <input name="maxgroup"
					type="number" id="maxgroup" min="2" /></td>
			</tr>
			<tr>
				<td><button type="submit">검색하기</button></td>
			</tr>
		</table>
	</form>
</div>


<div>
<table>

<tr><td>
<select id="browsers" name="browsers" multiple size="1" required="required">
    <option value="최신순">최신순</option>
    <option value="마감순">마감순</option>
    <option value="인기순">인기순</option>
</select></td></tr>
    
<tr><td>
  <img src="${path}/resources/img/blankprofile.png">

  <p>[수원시 장안구][체육] 축구 교실 운영합니다. </p>
  <p> 매주 금요일 축구 교습 가능합니다.</p>
 
  <input type="checkbox" name="checkbox" value="checkbox">찜하기<br> 
</td></tr>

<tr><td>
  <img src="${path}/resources/img/blankprofile.png">

  <p>[온라인][수학] 중학교1~3학년 수학 기본 공부합니다. </p>
  <p> 매주 월~목요일 수학 기본 과정 같이 공부합니다.</p>
 
  <input type="checkbox" name="checkbox" value="checkbox">찜하기<br> 
</td></tr>
</table>
</div>






</body>
</html>