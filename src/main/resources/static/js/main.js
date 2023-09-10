$(document).ready(function() {
	dashboard();
});

function transaction() {
	$.ajax({
		url : 'transaction',
		type : 'get',
		dataType : 'html',
		success : function(result) {
			$('#main').html(result);
		}
	});
}
function dashboard() {
	$.ajax({
		url : 'dashboard',
		type : 'get',
		dataType : 'html',
		success : function(result) {
			$('#main').html(result);
		}
	});
}