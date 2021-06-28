$(function(){
	// 수업 취소
    $('.cancelCourseBtn').click(function () {
	  	var con = confirm("취소하면 복구할 수 없습니다.\n수업을 취소하시겠습니까?");
	  	if (con == true) {
			cancelCourse($(this).val())
		}
    })
	
	function cancelCourse(course_id) {
		$.ajax({
			type: "DELETE",
			url: "http://${ipaddress}:${port}/teacher/"+course_id,
			success: function() {
				location.reload();
			}
		})
	}
	
	// 수업 시작
    $('.startCourseBtn').click(function () {
	  	var con = confirm("수업을 시작하시겠습니까?");
	  	if (con == true) {
			startCourse($(this).val())
		}
    })
	
	function startCourse(course_id) {
		$.ajax({
			type: "POST",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/start",
			success: function() {
				var course_title = $("a[id='title-"+course_id+"']").text();
				alert(course_title + " 수업을 시작하였습니다.");
				location.reload();
			}
		})
	}
});