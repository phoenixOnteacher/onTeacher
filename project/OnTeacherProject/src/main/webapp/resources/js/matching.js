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
});
