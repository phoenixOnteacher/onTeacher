$(function() {
	// 알림 목록 가져오기
	$('#notificationBell').click(function() {
		$.ajax({
			type: "POST",
			url: "http://localhost:8090/notification",
			success: function(res) {
				var notifications = res.data;
				var notificationStr = '';
				for (var i=0; i<notifications.length; i++) {
					notificationStr += '<div class="shadow p-3 mb-3 bg-body rounded">';
					notificationStr += '<p class="text-start">' + notifications[i].content + '</p>';
					notificationStr += '<div class="d-flex justify-content-between"><p class="text-secondary text-start mb-0">' + notifications[i].createdAt.substring(0, 10) + '</p>';
					if (notifications[i].content.substring(0,11)=='[자격 증명(반려)]') {
						notificationStr += '<a class="text-decoration-none text-end" data-bs-toggle="modal" href="#certReuploadModal" role="button">재업로드 하기 <i class="fas fa-chevron-right mx-2"></i></a>';
						$('input[name="notificationId"]').val(notifications[i].id);
					}
					notificationStr += '</div></div>';
				}
				$('.offcanvas-body').html(notificationStr);
			}
		})
	})
	$('#certReuploadBtn').click(function() {
		event.preventDefault();
		var form = $('#certReuploadForm')[0];
		var formData = new FormData(form);
		console.log(formData);
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
		    url: 'http://localhost:8090/teacher/cert-reupload',
		    data: formData,
		    dataType: 'json',
		    processData: false,
		    contentType: false,
		    cache: false,
		    success: function(res){
		    	console.log(res);
				location.reload();
		    },
		    error: function(e){
		        console.log(e);
				location.reload();
		    }
		});
	})
});