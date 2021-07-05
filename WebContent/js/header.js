var searchResult;
var reachtime = 15;
var speed = 2;
var distance = speed * (reachtime / 60) * 1000;

if (typeof markerGroup !== 'undefined') {
		$("#page-checker").html(
			'<button type="button" id="btn-range" class="btn btn-primary text-nowrap">確定</button>'
		);		
	} else {
		$("#page-checker").html(
			'<button type="submit" id="btn-range" class="btn btn-primary text-nowrap">確定</button>'
	);
}
function rangeslider(value){
	reachtime = value;
	distance = speed * (reachtime / 60) * 1000;
	/* 範圍滑塊 */
	$("#range-slider").wRunner({
		step: 5,

		// or 'range'
		type: "single",

		limits: {
			minLimit: 5,
			maxLimit: 60,
		},

		// default value
		singleValue: reachtime,

		// root element
		roots: document.body,

		// the number of divisions
		scaleDivisionsCount: 0,

		// shows labels
		valueNoteDisplay: true,

		// theme name
		theme: "default",

		// or 'vertical'
		direction: "horizontal",
	});
	
	$("#range-slider").on(
			"DOMSubtreeModified",
			".wrunner__value-note",
			function () {
				reachtime = $(".wrunner__value-note > div")
					.text()
					.split("分鐘")
					.slice(0, -1)
					.toString();
				$("#reachtime").html(reachtime);
				$("#btn-reachtime").val(reachtime);
				if ($("input").hasClass("hidden-reachtime")) {
					$(".hidden-reachtime").val(reachtime);
				}
				distance = speed * (reachtime / 60) * 1000;
			}
	);
}

/* 交通工具標籤 */
$("#motorbike").on("click", function () {
	speed = 10;
	distance = speed * (reachtime / 60) * 1000;
	$("#route").html("機車");
});
$("#bike").on("click", function () {
	speed = 4;
	distance = speed * (reachtime / 60) * 1000;
	$("#route").html("腳踏車");
});
$("#walk").on("click", function () {
	speed = 2;
	distance = speed * (reachtime / 60) * 1000;
	$("#route").html("走路");
});
$("#car").on("click", function () {
	speed = 15;
	distance = speed * (reachtime / 60) * 1000;
	$("#route").html("汽車");
});
$("#train").on("click", function () {
	speed = 15;
	distance = speed * (reachtime / 60) * 1000;
	$("#route").html("大眾運輸");
});

$(".btn-check").click(function(e){
	if ($("input").hasClass("hidden-route")) {
		$(".hidden-route").val($(this).val());
	}
});

/* 搜尋關鍵字提示 */
const DEFAULTS = {
	treshold: 1,
	maximumItems: 5,
	highlightTyped: true,
	highlightClass: "text-primary",
};

class Autocomplete {
	constructor(field, options) {
		this.field = field;
		this.options = Object.assign({}, DEFAULTS, options);
		this.dropdown = null;

		field.parentNode.classList.add("dropdown");
		field.setAttribute("data-toggle", "dropdown");
		field.classList.add("dropdown-toggle");

		const dropdown = ce(`<div class="dropdown-menu" ></div>`);
		if (this.options.dropdownClass)
			dropdown.classList.add(this.options.dropdownClass);

		insertAfter(dropdown, field);

		this.dropdown = new bootstrap.Dropdown(field, this.options.dropdownOptions);

		field.addEventListener("click", (e) => {
			if (this.createItems() === 0) {
				e.stopPropagation();
				this.dropdown.hide();
			}
		});

		field.addEventListener("input", () => {
			if (this.options.onInput) this.options.onInput(this.field.value);
			this.renderIfNeeded();
		});

		field.addEventListener("keydown", (e) => {
			if (e.keyCode === 27) {
				this.dropdown.hide();
				return;
			}			
		});
	}

	setData(data) {
		this.options.data = data;
		this.renderIfNeeded();
	}

	renderIfNeeded() {
		if (this.createItems() > 0) this.dropdown.show();
		else this.field.click();
	}

	createItem(lookup, item) {
		let label;
		if (this.options.highlightTyped) {
			const idx = item.label.toLowerCase().indexOf(lookup.toLowerCase());
			const className = Array.isArray(this.options.highlightClass)
				? this.options.highlightClass.join(" ")
				: typeof this.options.highlightClass == "string"
				? this.options.highlightClass
				: "";
			label =
				item.label.substring(0, idx) +
				`<span class="${className}">${item.label.substring(
					idx,
					idx + lookup.length
				)}</span>` +
				item.label.substring(idx + lookup.length, item.label.length);
		} else {
			label = item.label;
		}
		if (this.options.showValue) {
			label += ` ${item.value}`;
		}
		return ce(
			`<button type="button" class="dropdown-item" data-label="${item.label}" data-value="${item.value}">${label}</button>`
		);
	}

	createItems() {
		const lookup = this.field.value;
		if (lookup.length < this.options.treshold) {
			this.dropdown.hide();
			return 0;
		}

		const items = this.field.nextSibling;
		items.innerHTML = "";

		let count = 0;
		for (let i = 0; i < this.options.data.length; i++) {
			const { label, value } = this.options.data[i];
			const item = { label, value };
			if (item.label.toLowerCase().indexOf(lookup.toLowerCase()) >= 0) {
				items.appendChild(this.createItem(lookup, item));
				if (
					this.options.maximumItems > 0 &&
					++count >= this.options.maximumItems
				)
					break;
			}
		}

		this.field.nextSibling
			.querySelectorAll(".dropdown-item")
			.forEach((item) => {
				item.addEventListener("click", (e) => {
					let dataValue = e.target.getAttribute("data-value");
					this.field.value = e.target.innerText;
					if (this.options.onSelectItem)
						this.options.onSelectItem({
							value: e.target.dataset.value,
							label: e.target.innerText,
						});
					this.dropdown.hide();
				});
			});

		return items.childNodes.length;
	}
}

function ce(html) {
	let div = document.createElement("div");
	div.innerHTML = html;
	return div.firstChild;
}

function insertAfter(elem, refElem) {
	return refElem.parentNode.insertBefore(elem, refElem.nextSibling);
}

var place = new Autocomplete(document.getElementById("place-bar"), {
	data: placesrc,
	onSelectItem: ({ label, value }) => {
		console.log("搜尋選擇:", label, value);
	},
});

var keyword = new Autocomplete(document.getElementById("shop-keyword-bar"), {
	data: shopsrc,
	onSelectItem: ({ label, value }) => {
		console.log("搜尋選擇:", label, value);
	},
});

$("#search-type").on("change", function () {
	let shop =
		'<input type="text" name="place-bar" id="place-bar" class="form-control w-25 search-bar" placeholder="地點" />' +
		'<input type="text" name="shop-keyword-bar" id="shop-keyword-bar" class="form-control w-25 search-bar" placeholder="關鍵字" />' +
		'<input type="hidden" name="action" class="search-bar" value="shop_search">';

	let article =
		'<input type="text" name="article-keyword-bar" id="article-keyword-bar" class="form-control w-50 search-bar" placeholder="關鍵字" />' +
		'<input type="hidden" name="action" class="search-bar" value="article_search">';

	let product =
		'<input type="text" name="product-keyword-bar" id="product-keyword-bar" class="form-control w-50 search-bar" placeholder="關鍵字" />' +
		'<input type="hidden" name="action" class="search-bar" value="product_search">';

	let party =
		'<input type="text" name="party-keyword-bar" id="party-keyword-bar" class="form-control w-50 search-bar" placeholder="關鍵字" />' +
		'<input type="hidden" name="action" class="search-bar" value="party_search">';

	switch (this.value) {
		case "shop":
			$(".search-bar").remove();
			$(shop).insertAfter("#search-type");
			place = new Autocomplete(document.getElementById("place-bar"), {
				data: placesrc,
				onSelectItem: ({ label, value }) => {
					console.log("搜尋選擇:", label, value);
				},
			});
			keyword = new Autocomplete(document.getElementById("shop-keyword-bar"), {
				data: shopsrc,
				onSelectItem: ({ label, value }) => {
					console.log("搜尋選擇:", label, value);
				},
			});
			if ($(".dropdown-menu.show:hover").length) {
				return;
			}
			killDrop();
			$("#btn-submit").prop("disabled", true);
			$("#place-bar").keyup(function () {
				$("#btn-submit").prop(
					"disabled",
					this.value == "" && $("#shop-keyword-bar").val() == "" ? true : false
				);
			});
			$("#shop-keyword-bar").keyup(function () {
				$("#btn-submit").prop(
					"disabled",
					this.value == "" && $("#place-bar").val() == "" ? true : false
				);
			});
			$("#place-bar").focusout(function () {
				killDrop();
			});
			$("#shop-keyword-bar").focusout(function () {
				killDrop();
			});
			break;
		case "article":
			$(".search-bar").remove();
			$(article).insertAfter("#search-type");
			keyword = new Autocomplete(
				document.getElementById("article-keyword-bar"),
				{
					data: shopsrc,
					onSelectItem: ({ label, value }) => {
						console.log("搜尋選擇:", label, value);
					},
				}
			);
			killDrop();
			$("#btn-submit").prop("disabled", true);
			$("#article-keyword-bar").keyup(function () {
				$("#btn-submit").prop("disabled", this.value == "" ? true : false);
			});
			$("#article-keyword-bar").focusout(function () {
				killDrop();
			});
			break;
		case "product":
			$(".search-bar").remove();
			$(product).insertAfter("#search-type");
			keyword = new Autocomplete(
				document.getElementById("product-keyword-bar"),
				{
					data: shopsrc,
					onSelectItem: ({ label, value }) => {
						console.log("搜尋選擇:", label, value);
					},
				}
			);
			killDrop();
			$("#btn-submit").prop("disabled", true);
			$("#product-keyword-bar").keyup(function () {
				$("#btn-submit").prop("disabled", this.value == "" ? true : false);
			});
			$("#product-keyword-bar").focusout(function () {
				killDrop();
			});
			break;
		case "party":
			$(".search-bar").remove();
			$(party).insertAfter("#search-type");
			keyword = new Autocomplete(document.getElementById("party-keyword-bar"), {
				data: shopsrc,
				onSelectItem: ({ label, value }) => {
					console.log("搜尋選擇:", label, value);
				},
			});
			killDrop();
			$("#btn-submit").prop("disabled", true);
			$("#party-keyword-bar").keyup(function () {
				$("#btn-submit").prop("disabled", this.value == "" ? true : false);
			});
			$("#party-keyword-bar").focusout(function () {
				killDrop();
			});
			break;
	}
});

$("#topnav").on("click", "#logout", function () {
	$("#form-logout").submit();
});

document.addEventListener("DOMContentLoaded", function () {
	document
		.querySelectorAll(".navbar-expand-lg .nav-item")
		.forEach(function (everyitem) {
			everyitem.addEventListener("mouseover", function (e) {
				let el_link = this.querySelector("a[data-bs-toggle]");
				if (el_link != null) {
					let nextEl = el_link.nextElementSibling;
					el_link.classList.add("show");
					nextEl.classList.add("show");
				}
			});
			everyitem.addEventListener("mouseleave", function (e) {
				let el_link = this.querySelector("a[data-bs-toggle]");

				if (el_link != null) {
					let nextEl = el_link.nextElementSibling;
					el_link.classList.remove("show");
					nextEl.classList.remove("show");
				}
			});
		});
	if(searchResult == null || typeof map !== 'undefined'){
		rangeslider(reachtime);
	}
});

function killDrop() {
	if ($(".dropdown-menu.show:hover").length) {
		return;
	}
	$(".dropdown-menu.show").removeClass("show");
}

$("body").on("keypress", ".search-bar", function(e){
	var code = (e.keyCode ? e.keyCode : e.which);
	if (code == 13){
		$("#btn-submit").click();
	}
});

$("#btn-submit").on("click", function () {
	let recentArray = [];
	let searchedObj = new Object();

	if (localStorage.getItem("searched") != null) {
		let searched = JSON.parse(localStorage.getItem("searched"));
		for (let key in searched) {
			recentArray.push(searched[key]);
		}
	}
	if ($("#shop-keyword-bar").val().trim() != "") {
		searchedObj["keyword"] = $("#shop-keyword-bar").val();
	}
	if ($("#place-bar").val().trim() != "") {
		searchedObj["place"] = $("#place-bar").val();
	}
	if (
		$("#shop-keyword-bar").val().trim() != "" ||
		$("#place-bar").val().trim() != ""
	) {
		searchedObj["time"] = new Date();
		recentArray.push(searchedObj);
		localStorage.setItem("searched", JSON.stringify(recentArray));
		if (typeof searchedList !== "undefined") {
			searchedList();
		}
	}
});

$("#popular-key")
	.find("a")
	.on("click", function () {
		let recentArray = [];
		let searchedObj = new Object();

		if (localStorage.getItem("searched") != null) {
			let searched = JSON.parse(localStorage.getItem("searched"));
			for (let key in searched) {
				recentArray.push(searched[key]);
			}
		}
		searchedObj["keyword"] = $(this).text();
		searchedObj["time"] = new Date();
		recentArray.push(searchedObj);
		localStorage.setItem("searched", JSON.stringify(recentArray));
		if (typeof searchedList !== "undefined") {
			searchedList();
		}
		$('#redirect').removeClass("d-none");
		$('.row').fadeTo("fast", 0.5);
	});

$("#btn-submit").prop("disabled", true);
$("#place-bar").keyup(function () {
	$("#btn-submit").prop(
		"disabled",
		this.value == "" && $("#shop-keyword-bar").val() == "" ? true : false
	);
});
$("#shop-keyword-bar").keyup(function () {
	$("#btn-submit").prop(
		"disabled",
		this.value == "" && $("#place-bar").val() == "" ? true : false
	);
});
$("#place-bar").focusout(function () {
	killDrop();
});
$("#shop-keyword-bar").focusout(function () {
	killDrop();
});


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

var latitude = "25.052244795897003";
var longitude = "121.54313659210797";

function success(pos) {
	localStorage.setItem("latitude", pos.coords.latitude);
	localStorage.setItem("longitude", pos.coords.longitude);
	if(searchResult == null){
		latitude = localStorage.getItem("latitude");
		longitude = localStorage.getItem("longitude");
		$("#hidden-lat").val(latitude);
		$("#hidden-lng").val(longitude);
		if (typeof layerGroup !== 'undefined') {
			marker1 = L.marker([latitude,longitude]).getLatLng();
			layerGroup.clearLayers();
			circle = L.circle([latitude,longitude], {radius: distance}).addTo(map);
			map.setView(new L.LatLng(latitude, longitude), 15);
			updateList(latitude,longitude);
		}
	}
}

function error(err) {
	localStorage.removeItem("latitude");
	localStorage.removeItem("longitude");
	if(searchResult == null){
		latitude = "25.052244795897003";
		longitude = "121.54313659210797";
		if (typeof layerGroup !== 'undefined') {
			marker1 = L.marker([latitude,longitude]).getLatLng();
			layerGroup.clearLayers();
			circle = L.circle([latitude,longitude], {radius: distance}).addTo(map);
			map.setView(new L.LatLng(latitude, longitude), 15);
			updateList(latitude,longitude);
		}
	}
}

for(var i = 0; i < 12; i++){
	$("#tag" + i).click(function(){
		$("#shop-keyword-bar").val($(this).html());
		$("#btn-submit").prop("disabled", false);
		$("#form-submit").submit();
	});
}

$("#btn-submit").click(function(e){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$("a > button.guest").click(function(e){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$(".navbar-brand").click(function(e){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$(".nav-link").click(function(e){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$(".nav-item > ul > li").click(function(e){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$("li.nav-item > a.dropdown-toggle").click(function(e){
	window.location.href = $(this).attr("href");
});