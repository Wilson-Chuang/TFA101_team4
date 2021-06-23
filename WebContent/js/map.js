var osm = new L.TileLayer(
		'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
		{
			'opacity' : 0.7,
			'attribution' : '地圖資料 &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> 發佈者們, Imagery © <a href="https://www.mapbox.com/">圖箱</a>'
		});
var latitude = "25.052244795897003";
var longitude = "121.54313659210797";
var map = new L.Map('map').addLayer(osm);
var layerGroup = L.layerGroup().addTo(map);
var marker, circle;

$(function() {
	var options = {
		enableHighAccuracy : true,
		timeout : 1000,
		maximumAge : 0
	};

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(success, error, options);
	}
});