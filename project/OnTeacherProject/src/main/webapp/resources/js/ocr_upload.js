$(function() {
	$('#upload').on('click', function() {
		if ($('#file').val() == "") {
			swal({
				title: "주의사항",
				text: "JPG 파일을 업로드해주세요!",
				icon: "info",
			});
			return false;
		} else {
			var maxSize = 1024 * 1024 * 5    //5MB
			var fileSize = file.files[0].size;
			var currentfile = (fileSize / (1024 * 1024)).toFixed(2);

			if (fileSize > maxSize) {
				swal({
					text: "현재 파일 용량(" + currentfile + "MB)이 업로드 가능한 최대 파일 용량 5MB를 초과했습니다.",
					icon: "error",
				});
				$('#file').focus();
				$('#file').val("");
				return false;
			} else {
				return true;
			}
		}
	})
});