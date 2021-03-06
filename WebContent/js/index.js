function removeSearchedItem(id) {
	var obj = JSON.parse(localStorage.getItem("searched"));
	for (let key in obj) {
		if (key == id) {
			obj.splice(key, 1);
			break;
		}
	}
	localStorage.setItem("searched", JSON.stringify(obj));
}

function removeVistedItem(id) {
	var obj = JSON.parse(localStorage.getItem("visited"));
	for (let key in obj) {
		if (key == id) {
			obj.splice(key, 1);
			break;
		}
	}
	localStorage.setItem("visited", JSON.stringify(obj));
}


$("#searched-group").on("click", "#searched-list", function(){
	let self = $(this).attr("data-id");
	$(this).closest('li').remove();
	removeSearchedItem(self);
	searchedList();
});

$("#visited-group").on("click", "#visited-list", function(){
	let self = $(this).attr("data-id");
	$(this).closest('li').remove();
	removeVistedItem(self);
	visitedList();
});

$(function () {	
	searchedList();
	visitedList();
});

function searchedList(){	
	if (localStorage.getItem("searched") != null && localStorage.getItem("searched").length > 2){
		let searched = JSON.parse(localStorage.getItem("searched"));
		var printsearch = "";
		let times = 0;
		for(let key = searched.length - 1; key >= 0; key-- ) {
			var keytag = "#keysave" + key;
			if(searched[key].keyword != null && searched[key].place != null){
				printsearch += '<li class="list-group-item d-flex justify-content-between align-items-center">' + 
				'<div class="col-sm-7">' + 
				'<a href="#" id="keysave' + key + '" class="fw-bold text-decoration-none">';
				printsearch += searched[key].keyword + ', ' + searched[key].place;
				$("#searched-group").on("click", keytag, function(){
					$("#shop-keyword-bar").val($(this).html().split(", ")[0]);
					$("#place-bar").val($(this).html().split(", ")[1]);
					$("#btn-submit").prop("disabled", false);
					$("#form-submit").submit();
				});
			} else if(searched[key].keyword != null) {
				printsearch += '<li class="list-group-item d-flex justify-content-between align-items-center">' + 
				'<div class="col-sm-7">' + 
				'<a href="#" id="keysave' + key + '" class="fw-bold text-decoration-none">';
				printsearch += searched[key].keyword;
				$("#searched-group").on("click", keytag, function(){
					$("#shop-keyword-bar").val($(this).html());
					$("#btn-submit").prop("disabled", false);
					$("#form-submit").submit();
				});
			} else {
				printsearch += '<li class="list-group-item d-flex justify-content-between align-items-center">' + 
				'<div class="col-sm-7">' + 
				'<a href="#" id="keysave' + key + '" class="fw-bold text-decoration-none">';
				printsearch += searched[key].place;
				$("#searched-group").on("click", keytag, function(){
					$("#place-bar").val($(this).html());
					$("#btn-submit").prop("disabled", false);
					$("#form-submit").submit();
				});
			}			
			printsearch += '</a></div><label class="text-secondary">' + 
				timeSince(new Date(searched[key].time)) + '</label>' + 
				'<a href="#" id="searched-list" data-id="' + key + '"><i class="bi bi-x"></i></a></li>';
			times++;
			if(times >= 5){				
				break;
			}			
		}
		$("#searched-group").html("");
		$("#searched-group").append(printsearch);
	}else{
		$("#searched-group").append('<div class="bg-light g-0">' + 
		'<label class="bg-warning px-3 py-1">???????????????!</label>' + 
		'<div class="px-3 py-3">???????????????????????????????????????????????????????????????<br>???????????????????????????????????????' + 
		'</div></div>');
	}
}

function timeSince(date) {
	  var seconds = Math.floor((new Date() - date) / 1000);
	  var interval = seconds / 31536000;
	  if (interval > 1) {
	    return Math.floor(interval) + " ??????";
	  }
	  interval = seconds / 2592000;
	  if (interval > 1) {
	    return Math.floor(interval) + " ??????";
	  }
	  interval = seconds / 86400;
	  if (interval > 1) {
	    return Math.floor(interval) + " ??????";
	  }
	  interval = seconds / 3600;
	  if (interval > 1) {
	    return Math.floor(interval) + " ?????????";
	  }
	  interval = seconds / 60;
	  if (interval > 1) {
	    return Math.floor(interval) + " ?????????";
	  }
	  return Math.floor(seconds) + " ??????";
}

$("body").on("click", '[class*="map-popup-"] > a', function(){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$("body").on("click", '[class*="col-"] > a', function(){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$('body').on('click', ".map-popup-direction", function () {
	$(this).closest('form').submit();
});