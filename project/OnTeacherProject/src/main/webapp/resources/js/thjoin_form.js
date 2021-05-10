$(function() {
	$('#joinbtn').on('click', function() {
		if ($('#fileName').val() == "") {
			swal({
				title: "주의사항",
				text: "자격 증명 파일을 업로드해주세요!",
				icon: "info",
			});
			return false;
		} else {
			return true;
		}
	})
});