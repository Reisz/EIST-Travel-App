//Map display functionality
var map = L.map('map')
L
		.tileLayer(
				'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
				{
					attribution : '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
				}).addTo(map);

$(document).ready(function() {
	resizeMap();
	map.setView([48.264679, 11.6713], 13).locate({setView: true});
});
window.onresize = function(event) {
	resizeMap();
}

function resizeMap() {
	vph = $(window).height() - 50;
	$('#map').css({
		'height' : vph + 'px'
	});
}

// Find Route Modal
$('#findRouteModal').on('show.bs.modal', function() {
	$('.main').addClass('blurry');
});
$('#findRouteModal').on('shown.bs.modal', function() {
	$('#origin').focus();
});
$('#findRouteModal').on('hide.bs.modal', function() {
	$('.main').removeClass('blurry');
});

// ripples
$('.ripple').on('mousedown',function(event) {
	event.preventDefault();

	var $div = $('<div/>'), btnOffset = $(this)
			.offset(), xPos = event.pageX
			- btnOffset.left, yPos = event.pageY
			- btnOffset.top;

	$div.addClass('ripple-effect');
	var $ripple = $(".ripple-effect");

	$ripple.css("height", $(this).height());
	$ripple.css("width", $(this).height());
	$div.css({
		top : yPos - ($ripple.height() / 2),
		left : xPos - ($ripple.width() / 2),
		background : "#fff"
	}).appendTo($(this));

	window.setTimeout(function() {
		$div.remove();
	}, 2000);
});

$('.ripple-fixed').on('mousedown',function(event) {
	event.preventDefault();

	var $div = $('<div/>');
	$div.addClass('ripple-effect-fixed');
	var $ripple = $(".ripple-effect-fixed");

	$ripple.css("height", $(this).height());
	$ripple.css("width", $(this).height());
	$div.css({
		top : -($ripple.height() / 2),
		left : -($ripple.width() / 2),
		background : "#fff"
	}).appendTo($(this));

	window.setTimeout(function() {
		$div.remove();
	}, 250);
});

//interactive form icons
$("label").each(function() {
	var input = $("#" + $(this).attr("for"));
	var label = $(this)
	input.focusin(function(e) {
		label.addClass("focus");
	});
	input.focusout(function(e) {
		label.removeClass("focus");
	});
});

//dropdowns
$(".dropdown.dropdown-eist").on("show.bs.dropdown", function() {
  var menu = $(this).children(".dropdown-menu");
  var offset = menu.children(":has(.active)").index() * 30;
  menu.css({"top": "-" + (5 + offset) + "px"});
});


$(".dropdown-menu.dropdown-eist").on("click", "li", function() {
  $(this).parent().parent().children(".dropdown-toggle").get(0).firstChild.nodeValue = $(this).children().first().text();
  $(this).parent().children(":has(.active)").children().first().removeClass("active");
  $(this).children().first().addClass("active");
});

// debug
$('#findRouteModal').modal('show');
