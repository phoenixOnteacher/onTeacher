$(function(){
	// 매칭 선택 버튼 처리
    $(".btn-check").click(function(){
		var studentId = this.id.substring(10);
        var isChecked = $(this).is(":checked");
        if (isChecked) {
			$("#btn-check-"+studentId).prop('checked', true);
			$("label[for='btn-check-"+studentId+"']").text('선택 취소');
		} else {
			$("#btn-check-"+studentId).prop('checked', false);
			$("label[for='btn-check-"+studentId+"']").text('선택');
		}
    });
	
	// 매칭하기
	$('#matchingBtn').click(function () {
	  	var con = confirm("매칭이 완료되면 더 이상 학생을 받을 수 없습니다.\n매칭을 진행하시겠습니까?");
	  	if (con == true) {
			var selectedStudents = [];
			$("input[name='selectedStudent']:checked").each(function() {
				selectedStudents.push($(this).val());
			});
			jQuery.ajaxSettings.traditional = true;
			$.ajax({
				type: "POST",
				url: "http://${ipaddress}:${port}/teacher/"+$(this).val()+"/matching",
				dataType: "json",
				data: JSON.stringify({
					"selectedStudents": selectedStudents
				}),
				contentType: "application/json; charset=UTF-8",
				complete: function() {
					alert("매칭이 완료되었습니다.");
					location.reload();
				}
			})
		}
    });

	// 매칭 취소
	$('.cancelMatchingBtn').click(function () {
	  	var con1 = confirm("매칭을 취소하면 복구할 수 없습니다.\n매칭을 취소하시겠습니까?");
		var con2 = null;
	  	if (con1) {
			if ($('.cancelMatchingBtn').length==1) {
				con2 = confirm("매칭된 학생 인원이 0명이 되어, 수업이 취소 처리되고 복구할 수 없습니다. \n매칭을 취소하시겠습니까?");
			}
			if (con2!=false) {
				var course_id = $("h2[class='course-title']").attr('id').substring(7);
				cancelMatching(course_id,$(this).val(),con2)
			}
		}
    })
	
	function cancelMatching(course_id,student_id,con2) {
		$.ajax({
			type: "DELETE",
			url: "http://${ipaddress}:${port}/teacher/"+course_id+"/matching",
			dataType: "json",
			data: JSON.stringify({
				"studentId": student_id
			}),
			contentType: "application/json; charset=UTF-8",
			complete: function() {
				alert("매칭이 취소되었습니다.");
				if (con2==null) {
					location.reload();
				} else {
					window.location.replace("http://localhost:8090/teacher/course-manage");
				}
			}
		})
	}
});
