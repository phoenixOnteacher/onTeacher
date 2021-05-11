$(function() {
	/* summernote text editor */
	$('#summernote').summernote({
		tabsize: 2,
		width: 750,
		height: 400,
		minHeight: 400,
		maxHeight: 400,
		toolbar: [
			// 글자 크기 설정
			['fontsize', ['fontsize']],
			// 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
			['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
			// 글자색
			['color', ['forecolor', 'color']],
			// 표만들기
			['table', ['table']],
			// 글머리 기호, 번호매기기, 문단정렬
			['para', ['ul', 'ol', 'paragraph']],
			// 그림첨부, 링크만들기, 동영상첨부
			['insert', ['link', 'video']],
			// 코드보기, 확대해서보기, 도움말
			['view', ['codeview']]
		],
		// 추가한 폰트사이즈
		fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72']
	});
})