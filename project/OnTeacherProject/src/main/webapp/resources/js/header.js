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
		swal({
		  text: "자격 승인 후 이용할 수 있습니다.",
		  icon: "error",
		})
	});
	
	$('.nonmemberReject').click(function(){
		swal({
		  text: "로그인 후 이용해 주세요.",
		  icon: "error",
		})
		.then (() => {
			location.href="/login";
		})
		return false;
	});
});

$(window).scroll(function() {
  if ($(document).scrollTop() > 10) {
    $('.navbar').addClass('navbareffect');
  } else {
    $('.navbar').removeClass('navbareffect');
  }
});
