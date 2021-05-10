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
			url: "http://${ipaddress}:${port}/teacher/highcategory",
			dataType: "json",
			data: { high_category_id: highcategoryid },
			contentType: "application/json; charset=UTF-8",
			success: function(data) {
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
		if ($(this).val() == '1') {
			$('select[id=sido]').attr('disabled', 'disabled');
			$('select[id=sigungu]').attr('disabled', 'disabled');
		} else {
			$('select[id=sido]').removeAttr('disabled');
			$('select[id=sigungu]').removeAttr('disabled');
		}
	});

	var area1 = ['강남구', '강동구', '강북구', '강서구', '관악구', '광진구', '구로구', '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구', '서대문구', '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구', '은평구', '종로구', '중구', '중랑구'];
	var area2 = ['강서구', '금정구', '기장군', '남구', '동구', '동래구', '부산진구', '북구', '사상구', '사하구', '서구', '수영구', '연제구', '영도구', '중구', '해운대구'];
	var area3 = ['남구', '달서구', '달성군', '동구', '북구', '서구', '수성구', '중구'];
	var area4 = ['강화군', '계양구', '남구', '남동구', '동구', '미추홀구', '부평구', '서구', '연수구', '옹진군', '중구'];
	var area5 = ['광산구', '남구', '동구', '북구', '서구'];
	var area6 = ['대덕구', '동구', '서구', '유성구', '중구'];
	var area7 = ['남구', '동구', '북구', '울주군', '중구'];
	var area8 = ['세종시'];
	var area9 = ['가평군', '고양시', '과천시', '광명시', '광주시', '구리시', '군포시', '김포시', '남양주시', '동두천시', '부천시', '성남시', '수원시', '시흥시', '안산시', '안성시', '안양시', '양주시', '양평군', '여주군', '여주시', '연천군', '오산시', '용인시', '의왕시', '의정부시', '이천시', '파주시', '평택시', '포천시', '하남시', '화성시'];
	var area10 = ['강릉시', '고성군', '동해시', '삼척시', '속초시', '양구군', '양양군', '영월군', '원주시', '인제군', '정선군', '철원군', '춘천시', '태백시', '평창군', '홍천군', '화천군', '횡성군'];   
	var area11 = ['괴산군', '단양군', '보은군', '영동군', '옥천군', '음성군', '제천시', '증평군', '진천군', '청원군', '청주시', '충주시'];
	var area12 = ['계룡시', '공주시', '금산군', '논산시', '당진시', '보령시', '부여군', '서산시', '서천군', '아산시', '연기군', '예산군', '천안시', '청양군', '태안군', '홍성군'];
	var area13 = ['고창군', '군산시', '김제시', '남원시', '무주군', '부안군', '순창군', '완주군', '익산시', '임실군', '장수군', '전주시', '정읍시', '진안군'];
	var area14 = ['강진군', '고흥군', '곡성군', '광양시', '구례군', '나주시', '담양군', '목포시', '무안군', '보성군', '순천시', '신안군', '여수시', '영광군', '영암군', '완도군', '장성군', '장흥군', '진도군', '함평군', '해남군', '화순군'];
	var area15 = ['경산시', '경주시', '고령군', '구미시', '군위군', '김천시', '문경시', '봉화군', '상주시', '성주군', '안동시', '영덕군', '영양군', '영주시', '영천시', '예천군', '울릉군', '울진군', '의성군', '청도군', '청송군', '칠곡군', '포항시'];
	var area16 = ['거제시', '거창군', '고성군', '김해시', '남해군', '밀양시', '사천시', '산청군', '양산시', '의령군', '진주시', '창녕군', '창원시', '통영시', '하동군', '함안군', '함양군', '합천군'];   
	var area17 = ['서귀포시', '제주시'];

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
		if ($(this).val() == '0') {
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
