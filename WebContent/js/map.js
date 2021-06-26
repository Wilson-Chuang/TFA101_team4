var distanceDiff, marker1, marker2;
var osm = new L.TileLayer(
		'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
		{
			'opacity' : 0.7,
			'attribution' : '地圖資料 &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> 發佈者們, Imagery © <a href="https://www.mapbox.com/">圖箱</a>'
		});
var map = new L.Map('map').addLayer(osm);
var layerGroup = L.layerGroup().addTo(map);
var marker, circle;