//Map display functionality
var map = L.map('map')
L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

$(document).ready(function(){
  resizeMap();
  map.setView([48.137222222222, 11.575555555556], 13);
});
window.onresize = function(event) {
	resizeMap();
}

function resizeMap() {
	vph = $(window
  ).height() - 50;
	$('#map').css({'height': vph + 'px'});
}


//Find a Route Modal
$('#findRouteModal').on('show.bs.modal', function () {
	$('#map').toggleClass('blurry', true);
});
$('#findRouteModal').on('shown.bs.modal', function () {
  //$('#myInput').focus();
});
$('#findRouteModal').on('hide.bs.modal', function () {
	$('#map').toggleClass('blurry', false);
});

//debug
$('#findRouteModal').modal('show');
