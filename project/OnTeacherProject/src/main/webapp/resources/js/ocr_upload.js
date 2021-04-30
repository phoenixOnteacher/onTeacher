$(function() {

	swal({
		title: "주의사항",
		text: "먼저 JPG 파일을 선택하세요!",
		icon: "info",
	});

});

function fileCheck(file) {
	// 사이즈체크
	var maxSize = 5 * 1024 * 1024    //5MB
	var fileSize = 0;

	fileSize = file.files[0].size;

	swal({
		title: "주의사항",
		text: "파일사이즈 : " + fileSize + ", 최대파일사이즈 : 5MB",
		icon: "info",
	});


	if (fileSize > maxSize) {
		swal("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.");
		return false;
	} else {
		document.fileForm.submit();
	}
}