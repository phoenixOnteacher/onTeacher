$(function() {
	$('#btnJoin').on('click', function() {
		var subContent = $("#joinsub");
		var up = $("#up");
		var down = $("#down");

		if (subContent[0].style.display == "block") {
			subContent[0].style.display = "none";
			up[0].style.display = "none";
			down[0].style.display = "inline-block"
		} else {
			subContent[0].style.display = "block";
			up[0].style.display = "inline-block";
			down[0].style.display = "none";
		}
	});
	
	$('.teacherReject').click(function(){
		alert('자격 승인 후 이용할 수 있습니다.');
	});
	
	$('.nonmemberReject').click(function(){
		alert('로그인 후 이용해 주세요.');
	});
});

$(window).scroll(function() {
  if ($(document).scrollTop() > 10) {
    $('.navbar').addClass('navbareffect');
  } else {
    $('.navbar').removeClass('navbareffect');
  }
});
