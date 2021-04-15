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
	})
});

$(window).scroll(function() {
  if ($(document).scrollTop() > 10) {
    $('.navbar').addClass('navbareffect');
  } else {
    $('.navbar').removeClass('navbareffect');
  }
});