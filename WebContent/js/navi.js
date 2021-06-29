let map;
var directionsService = new google.maps.DirectionsService();
var directionsDisplay = new google.maps.DirectionsRenderer();

function initMap(latitude, longitude) {
	map = new google.maps.Map(document.getElementById("map"), {
		center: { lat: latitude, lng: longitude },
		zoom: 15,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
	});
	directionsDisplay.setMap(map);
	directionsDisplay.setPanel(document.getElementById("panel"));
}

function navigate(ori_lat, ori_lng, des_lat, des_lng, mode) {
	switch (mode) {
	  case "motorbike":
		mode = google.maps.DirectionsTravelMode.DRIVING;
	    break;
	  case "bike":
		mode = google.maps.DirectionsTravelMode.BICYCLING;
	    break;
	  case "walk":
		mode = google.maps.DirectionsTravelMode.WALKING;
	    break;
	  case "car":
		mode = google.maps.DirectionsTravelMode.DRIVING;
	    break;
	  case "train":
		mode = google.maps.DirectionsTravelMode.TRANSIT;
		break;
	}
	
	var request = {
		origin: new google.maps.LatLng(ori_lat, ori_lng),
		destination: new google.maps.LatLng(des_lat, des_lng),
		travelMode: mode,
	};

	directionsService.route(request, function (response, status) {
		if (status == google.maps.DirectionsStatus.OK) {
			directionsDisplay.setDirections(response);
		}
	});
}

$(".btn-check").click(function(e){
	navigate(parseFloat(latitude), parseFloat(longitude), shopVO.shop_latitude, shopVO.shop_longitude, $(this).val());
});

$("body").on("click", "a", function(){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$("body").on("click", "button", function(){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});