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
		'<label class="bg-warning px-3 py-1">開始搜尋吧!</label>' + 
		'<div class="px-3 py-3">只要是您搜過的關鍵字，之後將會出現在這裡，<br>讓您能夠再次找到您的搜尋。' + 
		'</div></div>');
	}
}

function visitedList(){
	if (localStorage.getItem("visited") != null && localStorage.getItem("visited").length > 2){
		let visited = JSON.parse(localStorage.getItem("visited"));
		var printsearch = "";
		let times = 0;
		for(let key = visited.length - 1; key >= 0; key-- ) {
			printsearch += '<li class="list-group-item d-flex justify-content-between align-items-center">' + 
				'<div class="col-sm-7"><a href="#" class="fw-bold text-decoration-none">';
			if(visited[key].keyword != null){
				printsearch += visited[key].keyword
				if(visited[key].place != null){
					printsearch += ', ';
				}
			}
			
			if(visited[key].place != null){
				printsearch += visited[key].place;
			}
			printsearch += '</a></div><label class="text-secondary">' + 
				timeSince(new Date(visited[key].time)) + '</label>' + 
				'<a href="#" id="visited-list" data-id="' + key + '"><i class="bi bi-x"></i></a></li>';
			times++;
			if(times >= 5){
				break;
			}
		}
		$("#visited-group").html("");
		$("#visited-group").append(printsearch);
	}else{
		$("#visited-group").append('<div class="row bg-light g-0">' + 
		'<div class="col-sm-6"><label class="bg-warning px-3 py-1">找到最愛商家</label>' + 
		'<div class="px-3 py-3">目前暫無商家瀏覽紀錄。<br>您可以在這裡找到您過去拜訪過的商家頁面。' + 
		'</div></div><div class="col-sm-6 px-3"><img class="h-100 mw-100 px-3 py-3" src="./img/home/fav-house.svg"/></div></div>');
	}
}

function timeSince(date) {
	  var seconds = Math.floor((new Date() - date) / 1000);
	  var interval = seconds / 31536000;
	  if (interval > 1) {
	    return Math.floor(interval) + " 年前";
	  }
	  interval = seconds / 2592000;
	  if (interval > 1) {
	    return Math.floor(interval) + " 月前";
	  }
	  interval = seconds / 86400;
	  if (interval > 1) {
	    return Math.floor(interval) + " 天前";
	  }
	  interval = seconds / 3600;
	  if (interval > 1) {
	    return Math.floor(interval) + " 小時前";
	  }
	  interval = seconds / 60;
	  if (interval > 1) {
	    return Math.floor(interval) + " 分鐘前";
	  }
	  return Math.floor(seconds) + " 秒前";
}

$("body").on("click", "a", function(){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});

$("body").on("click", "button", function(){
	$('#redirect').removeClass("d-none");
	$('.row').fadeTo("fast", 0.5);
});