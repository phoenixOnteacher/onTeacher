$(function(){
    $('#classNav .nav-link').click(function (e) {
      e.preventDefault()
      $(this).tab('show')
    })
	$('#manageNav .nav-link').click(function (e) {
      e.preventDefault()
      $(this).tab('show')
    })
});