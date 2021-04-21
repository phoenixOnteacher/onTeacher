$(function() {
	var select = '<option value="">선택해주세요</option>';
	
	$("#highcategory").change(function() {
		$("#lowcategory").empty().append(select);
		if($("#hightcategory").val() == "") { //select의 value가 ""이면 "선택해주세요"만 보여주도록
			$("#lowcategory").append(select);
		} else {
			comboChange($(this).val());
		}
	});
	
	function comboChange(highcategoryid) {
		$.ajax({
			type: "GET",
			url: "http://localhost:8090/highcategory",
			dataType: "json",
			data: {high_category_id: highcategoryid},
			contentType: "application/json; charset=UTF-8",
			success: function(data) {
				//alert(data);
				if(data.length == 0) {
					$("#lowcategory").append('<option value="">선택해주세요</option>');
				} else {
					$(data).each(function(i, item) {
						$("#lowcategory").append("<option value='"+item.id+"'>"+item.name+"</option>");
					});
				}
			}
		});
	}
})