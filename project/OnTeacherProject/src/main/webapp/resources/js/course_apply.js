$(function(){
	// 수업 신청
    $('.courseApplyBtn').click(function () {
	  	var con = confirm("수업을 신청하시겠습니까?");
	  	if (con == true) {
			courseApply($(this).val())
		}
    })
	
	function courseApply(course_id) {
		$.ajax({
			type: "POST",
			url: "http://localhost:8090/student/courseApply?courseId="+course_id,
			success: function(data,status) {
				alert(data);
			},
			error: function(data,status){
				alert(data);
			}
		})
	}
});