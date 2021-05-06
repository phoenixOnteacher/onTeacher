$(function() {
	var select = '<option value="">하위 분류 선택</option>';

	/* 수업 카테고리 선택 */
	$("#highcategory").change(function() {
		$("#lowcategory").empty().append(select);
		if ($("#highcategory").val() == "") { //select의 value가 ""이면 "선택해주세요"만 보여주도록
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
				if (data.length == 0) {
					$("#lowcategory").append('<option value="">하위 분류 선택</option>');				
				} else {
					$(data).each(function(i, item) {
						$("#lowcategory").append("<option value='" + item.id + "'>" + item.name + "</option>");
						if('${lowcategory_id}') $('#lowcategory').val('${lowcategory_id}');						
					});
				}
			}
		});
	}
	if('${highcategory_id}'){
  		$('#highcategory').val('${highcategory_id}').trigger('change');
  	}
	if('${target}') $('#target').val('${target}');
	
	if('${isonline}') {
    	var onlineradio = $('.isonline');
    	$.each(onlineradio, function(index,radio) {
			if($(radio).val() == '${isonline}') {
				$(radio).attr("checked",true); 
			}
		});
    }

	$('#submit_btn').on('click', function() {
		if ($('#highcategory').val() == "") {
			alert('수업 카테고리를 선택해주세요');
			return false;
		}
		if ($('#lowcategory').val() == "") {
			alert('수업 카테고리를 선택해주세요');
			return false;	
		}
		if ($('#target').val() == "") {
			alert('수업 대상을 선택해주세요');
			return false;	
		}		
		if(!$('#online').is(':checked') && !$('#offline').is(':checked')) {
			alert('수업 방식을 선택해주세요');
			return false;	
		}
	})
});