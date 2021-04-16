$(function() {
	/*var mainClass = {"국어" : "국어", "영어" : "영어", "수학" : "수학", "사회" : "사회", "과학" : "과학"};
	var artClass = {"음악" : "음악", "체육" : "체육", "미술" : "미술", "무용" : "무용"};
	var consultClass = {"진로 상담" : "진로 상담", "입시 상담" : "입시 상담"};
	var langClass = {"영어" : "TEPS/TOEFL/TOEIC", "일본어" : "JPT/JLPT", "중국어" : "HSK", "기타 제2외국어" : "기타 제2외국어"};
	var testClass = {"올림피아드" : "올림피아드", "IT/컴퓨터" : "IT/컴퓨터", "한국사" : "한국사능력검정시험", "기타 대회 및 자격증" : "기타 대회 및 자격증"};*/
	
	var select = "<option>선택해주세요</option>";
	
	$("#highcategory").change(function() {
		if($("#hightcategory").val() == "") { //select의 value가 ""이면 "선택해주세요"만 보여주도록
			$("#lowcategory").find("option").remove().end.append(select);
		} else {
			comboChange($(this).val());
		}
	});
	
	function comboChange() {
		$.ajax({
			type: "POST",
			url: "${contextPath}/sample/courseregister.do",
			dataType: "json",
			data: $("#courseRegForm").serialize(),
			success: function(data) {
				if(data.length == 0) {
					$("#lowcategory").append('<option value="">선택해주세요</option>');
				} else {
					$(data).each(function(i) {
						$("#lowcategory").append("<option value='"+data[i].name+"'>"+data[i].value+"</option>");
					});
				}
			}
		});
	}
})