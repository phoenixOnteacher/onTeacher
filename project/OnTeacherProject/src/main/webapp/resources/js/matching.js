$(function(){
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

	$('#matchingBtn').click(function () {
	  	var con = confirm("매칭이 완료되면 더 이상 학생을 받을 수 없습니다. 매칭을 진행하시겠습니까?");
	  	if (con == true) {
			var selectedStudents = [];
			$("input[name='selectedStudent']:checked").each(function() {
				selectedStudents.push($(this).val());
			});
			$.ajax({
				type: "POST",
				url: "http://localhost:8090/teacher/"+$(this).val()+"/match",
				dataType: "json",
				data: { selectedStudents: selectedStudents },
				contentType: "application/json; charset=UTF-8",
				success: function() {
					location.reload();
				},
				error: function(e) {
					console.err(e);
				}
			})
		}
    })
});
