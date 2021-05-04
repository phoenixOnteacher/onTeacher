$(function(){
	if ($("button[name='unreviewed']").length==0) {
		$("#unreviewedAlert").hide();
	}
	
	
	// 학생 리뷰 작성
    $('.writeReviewBtn').click(function () {
	  	var con = confirm("후기 작성을 완료하시겠습니까?");
		var course_id = $(this).val();
		var content = $("textarea[id='reviewContent"+course_id+"']").val();
	  	if (con == true) {
			console.log(course_id);
			console.log(content);
			writeReview(course_id, content);
		}
    })
	
	function writeReview(course_id, content) {
		$.ajax({
			type: "POST",
			url: "http://localhost:8090/student/"+course_id+"/review",
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