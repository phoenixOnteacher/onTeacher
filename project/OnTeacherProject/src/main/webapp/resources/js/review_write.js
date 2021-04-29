$(function(){
	// 학생 리뷰 작성
    $('.writeReviewBtn').click(function () {
	  	var con = confirm("후기 작성을 완료하시겠습니까?");
		var course_id = $("h2[class='course-title']").attr('id').substring(7);
		var student_id = $(this).val();
		var content = $("textarea[id='reviewContent"+course_id+student_id+"']").val();
		console.log(content);
	  	if (con == true) {
			writeReview(course_id, student_id, content);
		}
    })
	
	function writeReview(course_id, student_id, content) {
		$.ajax({
			type: "POST",
			url: "http://localhost:8090/teacher/"+course_id+"/review/"+student_id,
			dataType: "json",
			data: JSON.stringify({
				"content": content
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				alert("후기 작성이 완료되었습니다.");
				location.reload();
			}
		})
	}
});