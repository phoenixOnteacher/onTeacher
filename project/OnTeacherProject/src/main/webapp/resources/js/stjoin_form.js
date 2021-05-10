$(function() {
	$('#joinbtn').on('click', function() {
		var pw = $("#password").val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

		if (pw.length < 8 || pw.length > 20) {
			swal({
				title: "주의사항",
				text: "비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.",
				icon: "info",
			});
			return false;
		} else if (pw.search(/\s/) != -1) {
			swal({
				title: "주의사항",
				text: "비밀번호는 공백 없이 입력해주세요.",
				icon: "info",
			});
			return false;
		} else if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {
			swal({
				title: "주의사항",
				text: "영문, 숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.",
				icon: "info",
			});
			return false;
		}
	})
});