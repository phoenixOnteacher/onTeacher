$(function() {
	var select = '<option value="">하위 분류 선택</option>';

	/* 수업 카테고리 선택 */
	$("#highcategory").change(function() {
		$("#lowcategory").empty().append(select);
		if ($("#hightcategory").val() == "") { //select의 value가 ""이면 "선택해주세요"만 보여주도록
			$("#lowcategory").append(select);
		} else {
			comboChange($(this).val());
		}
	});

	function comboChange(highcategoryid) {
		$.ajax({
			type: "GET",
			url: "http://localhost:8090/teacher/highcategory",
			dataType: "json",
			data: { high_category_id: highcategoryid },
			contentType: "application/json; charset=UTF-8",
			success: function(data) {
				//alert(data);
				if (data.length == 0) {
					$("#lowcategory").append('<option value="">하위 분류 선택</option>');
				} else {
					$(data).each(function(i, item) {
						$("#lowcategory").append("<option value='" + item.id + "'>" + item.name + "</option>");
					});
				}
			}
		});
	}

	/* 오프라인 주소 선택 */
	$('input[id=isOnline]').change(function() {
		if ($(this).val() == '0') {
			$('select[id=sido]').attr('disabled', 'disabled');
			$('select[id=sigungu]').attr('disabled', 'disabled');
		} else {
			$('select[id=sido]').removeAttr('disabled');
			$('select[id=sigungu]').removeAttr('disabled');
		}
	});

	var area1 = ['종로구', '중구', '용산구', '성동구', '광진구', '동대문구', '중랑구', '성북구', '강북구', '도봉구', '노원구', '은평구', '서대문구', '마포구', '양천구', '강서구', '구로구', '금천구', '영등포구', '동작구', '관악구', '서초구', '강남구', '송파구', '강동구'];
	var area2 = ['중구', '서구', '동구', '영도구', '부산진구', '동래구', '남구', '북구', '해운대구', '사하구', '금정구', '강서구', '연제구', '수영구', '사상구', '기장군'];
	var area3 = ['중구', '동구', '서구', '남구', '북구', '수성구', '달서구', '달성군'];
	var area4 = ['중구', '동구', '남구', '미추홀구', '연수구', '남동구', '부평구', '계양구', '서구', '강화군', '옹진군'];
	var area5 = ['동구', '서구', '남구', '북구', '광산구'];
	var area6 = ['동구', '중구', '서구', '유성구', '대덕구'];
	var area7 = ['중구', '남구', '동구', '북구', '울주군'];
	var area8 = ['세종시'];
	var area9 = ['수원시', '성남시', '고양시', '용인시', '부천시', '안산시', '안양시', '남양주시', '화성시', '평택시', '의정부시', '시흥시', '파주시', '광명시', '김포시', '군포시', '광주시', '이천시', '양주시', '오산시', '구리시', '안성시', '포천시', '의왕시', '하남시', '여주시', '여주군', '양평군', '동두천시', '과천시', '가평군', '연천군'];
	var area10 = ['춘천시', '원주시', '강릉시', '동해시', '태백시', '속초시', '삼척시', '홍천군', '횡성군', '영월군', '평창군', '정선군', '철원군', '화천군', '양구군', '인제군', '고성군', '양양군'];
	var area11 = ['청주시', '충주시', '제천시', '청원군', '보은군', '옥천군', '영동군', '진천군', '괴산군', '음성군', '단양군', '증평군'];
	var area12 = ['천안시', '공주시', '보령시', '아산시', '서산시', '논산시', '계룡시', '당진시', '금산군', '연기군', '부여군', '서천군', '청양군', '홍성군', '예산군', '태안군'];
	var area13 = ['전주시', '군산시', '익산시', '정읍시', '남원시', '김제시', '완주군', '진안군', '무주군', '장수군', '임실군', '순창군', '고창군', '부안군'];
	var area14 = ['목포시', '여수시', '순천시', '나주시', '광양시', '담양군', '곡성군', '구례군', '고흥군', '보성군', '화순군', '장흥군', '강진군', '해남군', '영암군', '무안군', '함평군', '영광군', '장성군', '완도군', '진도군', '신안군'];
	var area15 = ['포항시', '경주시', '김천시', '안동시', '구미시', '영주시', '영천시', '상주시', '문경시', '경산시', '군위군', '의성군', '청송군', '영양군', '영덕군', '청도군', '고령군', '성주군', '칠곡군', '예천군', '봉화군', '울진군', '울릉군'];
	var area16 = ['창원시', '진주시', '통영시', '사천시', '김해시', '밀양시', '거제시', '양산시', '의령군', '함안군', '창녕군', '고성군', '남해군', '하동군', '산청군', '함양군', '거창군', '합천군'];
	var area17 = ['제주시', '서귀포시'];

	var sel = '<option value="">시/군/구 선택</option>';

	$("select[id=sido]").change(function() {
		var area = "area" + $("option", $(this)).index($("option:selected", $(this)));
		var sigungu = $(this).next(); // 선택영역 군구 객체

		$("select[id=sigungu]").empty().append(sel);

		if ($(this).val() == "") {
			$("select[id=sigungu]").append();
		} else {
			$.each(eval(area), function() {
				sigungu.append("<option value='" + this + "'>" + this + "</option>");
			});
		}
	});

	/* 참여인원 선택 */
	$('input[id=isGroup]').change(function() {
		if ($(this).val() == '1') {
			$('input[name=minStudent]').attr('disabled', 'disabled');
			$('input[name=maxStudent]').attr('disabled', 'disabled');
		} else {
			$('input[name=minStudent]').removeAttr('disabled');
			$('input[name=maxStudent]').removeAttr('disabled');
		}
	});

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
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    // 글자색
		    ['color', ['forecolor','color']],
		    // 표만들기
		    ['table', ['table']],
		    // 글머리 기호, 번호매기기, 문단정렬
		    ['para', ['ul', 'ol', 'paragraph']],
		    // 그림첨부, 링크만들기, 동영상첨부
		    ['insert',['link','video']],
		    // 코드보기, 확대해서보기, 도움말
		    ['view', ['codeview']]
		  ],
		 // 추가한 폰트사이즈
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
	});
	
	/* 등록버튼 클릭 시 입력필수 필드 확인 */
	$('#submit_btn').on('click', function() {
		if ($('#summernote').val() == "") {
			alert('내용을 입력해주세요');
			$('#summernote').summernote('focus');
			return false;
		}
	})
});
