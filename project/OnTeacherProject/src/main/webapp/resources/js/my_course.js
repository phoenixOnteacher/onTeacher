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
			url: "http://localhost:8090/student/applyCancle?courseId="+course_id,
			success: function() {
				location.reload();
			}
		})
	}
});