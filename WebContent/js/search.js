var refine = [];
var markerGroup = [];
var filtertemp = [];

var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
  return new bootstrap.Popover(popoverTriggerEl)
});

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

function pagelist(data){
	markerFactory(data);
	$('.pagination').pagination({
	    dataSource: data,
	    showGoInput: true,
	    showGoButton: true,
	    pageSize: 15,
	    className: 'paginationjs-theme-blue',
	    callback: function(data, pagination) {
	    	var html = simpleTemplating(data,pagination);
	        $('.searchResult').html(html);
	    }
	});	
}

$("#orderbyrate").click(function(e){
	e.preventDefault();
	$("#search-order-now > span").text($(this).text());
	quickSortDesc(refine, 0, refine.length, "shop_rating");
	if(filtertemp.length > 0){
		pagelist(filtertemp);
	}else{
		pagelist(refine);
	}
});

$("#orderbypopular").click(function(e){
	e.preventDefault();
	$("#search-order-now > span").text($(this).text());
	quickSortDesc(refine, 0, refine.length, "shop_total_view");
	if(filtertemp.length > 0){
		pagelist(filtertemp);
	}else{
		pagelist(refine);
	}
});


$("#orderbycost").click(function(e){
	e.preventDefault();
	$("#search-order-now > span").text($(this).text());
	quickSortAsc(refine, 0, refine.length, "shop_price_level");
	if(filtertemp.length > 0){
		pagelist(filtertemp);
	}else{
		pagelist(refine);
	}
});

$("#orderbydistance").click(function(e){
	e.preventDefault();
	$("#search-order-now > span").text($(this).text());
	quickSortDistance(refine, 0, refine.length, "shop_latitude", "shop_longitude");
	if(filtertemp.length > 0){
		pagelist(filtertemp);
	}else{
		pagelist(refine);
	}
});

function quickSortDistance(arr, startIdx, endIdx, prop1, prop2) {
	if (endIdx - startIdx <= 1) return
		
	const midIdx = Math.floor((endIdx + startIdx) / 2);
	marker2 = L.marker([arr[midIdx][prop1],arr[midIdx][prop2]]).getLatLng();
	distanceDiff = marker1.distanceTo(marker2);
	const mid = distanceDiff;
	
	const tmp = arr[startIdx];
	arr[startIdx] = arr[midIdx];
	arr[midIdx] = tmp;

	let i = startIdx
	for (let j = startIdx + 1; j < endIdx; j++) {
		marker2 = L.marker([arr[j][prop1],arr[j][prop2]]).getLatLng();
		distanceDiff = marker1.distanceTo(marker2);
		if (distanceDiff < mid) {
			i++;
			const tmp = arr[j];
			arr[j] = arr[i];
			arr[i] = tmp;
		}
	}

	if (i !== startIdx) {
		const tmp = arr[startIdx]
		arr[startIdx] = arr[i]
		arr[i] = tmp
	}

	quickSortDistance(arr, startIdx, i, prop1, prop2)
	quickSortDistance(arr, i + 1, endIdx, prop1, prop2)
}

function quickSortAsc(arr, startIdx, endIdx, prop) {
	if (endIdx - startIdx <= 1) return
		
	const midIdx = Math.floor((endIdx + startIdx) / 2)
	const mid = arr[midIdx][prop]

	const tmp = arr[startIdx]
	arr[startIdx] = arr[midIdx]
	arr[midIdx] = tmp

	let i = startIdx
	for (let j = startIdx + 1; j < endIdx; j++) {
		if (arr[j][prop] < mid) {
			i++
			const tmp = arr[j]
			arr[j] = arr[i]
			arr[i] = tmp
		}
	}

	if (i !== startIdx) {
		const tmp = arr[startIdx]
		arr[startIdx] = arr[i]
		arr[i] = tmp
	}

	quickSortAsc(arr, startIdx, i, prop)
	quickSortAsc(arr, i + 1, endIdx, prop)
}

function quickSortDesc(arr, startIdx, endIdx, prop) {
	if (endIdx - startIdx <= 1) return
		
	const midIdx = Math.floor((endIdx + startIdx) / 2)
	const mid = arr[midIdx][prop]

	const tmp = arr[startIdx]
	arr[startIdx] = arr[midIdx]
	arr[midIdx] = tmp

	let i = startIdx
	for (let j = startIdx + 1; j < endIdx; j++) {
		if (arr[j][prop] > mid) {
			i++
			const tmp = arr[j]
			arr[j] = arr[i]
			arr[i] = tmp
		}
	}

	if (i !== startIdx) {
		const tmp = arr[startIdx]
		arr[startIdx] = arr[i]
		arr[i] = tmp
	}

	quickSortDesc(arr, startIdx, i, prop)
	quickSortDesc(arr, i + 1, endIdx, prop)
}

$("#filterzero").click(function(e){
	e.preventDefault();
	$("#search-filter-menu > span").text($(this).text());
	filtertemp = [];
	pagelist(refine);
});

$("#filterone").click(function(e){
	e.preventDefault();
	$("#search-filter-menu > span").text($(this).text());
	filtertemp = [];
	$.each(refine, function(index, shopVO){
		if(shopVO.shop_price_level == 1){
			filtertemp.push(shopVO);
		}
	});
	pagelist(filtertemp);
});

$("#filtertwo").click(function(e){
	e.preventDefault();
	$("#search-filter-menu > span").text($(this).text());
	filtertemp = [];
	$.each(refine, function(index, shopVO){
		if(shopVO.shop_price_level == 2){
			filtertemp.push(shopVO);
		}
	});
	pagelist(filtertemp);
});

$("#filterthree").click(function(e){
	e.preventDefault();
	$("#search-filter-menu > span").text($(this).text());
	filtertemp = [];
	$.each(refine, function(index, shopVO){
		if(shopVO.shop_price_level == 3){
			filtertemp.push(shopVO);
		}
	});
	pagelist(filtertemp);
});

$("#filterfour").click(function(e){
	e.preventDefault();
	$("#search-filter-menu > span").text($(this).text());
	filtertemp = [];
	$.each(refine, function(index, shopVO){
		if(shopVO.shop_price_level == 4){
			filtertemp.push(shopVO);
		}
	});
	pagelist(filtertemp);
});

$("#search-filter-open").click(function(e){
	e.preventDefault();
		
	if($(this).hasClass("active")){
		$("#indicator").css("background-color", "#dce0e0");
		$("#open-title").removeClass("fw-bold");
		$(this).removeClass("active");
		filtertemp = [];
		pagelist(refine);
	} else {
		$(this).addClass("active");
		$("#indicator").css("background-color", "#7ed321");
		$("#open-title").addClass("fw-bold");		
		filtertemp = [];
		
		$.each(refine, function(index, shopVO){
			if(shopVO.shop_opening_time != null){
				opening_time = shopVO.shop_opening_time;
				
				if(opening_time.slice(0, 2) == "[\"") {
					var week_ch = ['日', '一', '二', '三', '四', '五', '六'];
					var today = new Date();
					opening_time = opening_time.replace(/\\u2013|\\u2014/g, "-");
					opening_time = JSON.parse(opening_time);
					for (var i = 0; i < opening_time.length; i++) {
						if(opening_time[i].slice(2, 3) == week_ch[today.getDay()]){
							opening_time[i] = opening_time[i].slice(5);
							var isOpen = false;
							var opening_time_array = opening_time[i].split(", ");
							for (var j = 0; j < opening_time_array.length; j++) {
								opening_time_array[j] = opening_time_array[j].split(" - ");								
								if(opening_time_array[j].length > 1){
									var start_date = new Date(today.getTime());
									var end_date = new Date(today.getTime());
									var start_time = opening_time_array[j][0];
									var end_time = opening_time_array[j][1];
									start_date.setHours(start_time.split(":")[0]);
									start_date.setMinutes(start_time.split(":")[1]);
									end_date.setHours(end_time.split(":")[0]);
									end_date.setMinutes(end_time.split(":")[1]);
									isOpen = start_date < today && end_date > today;
								}								
							}
							if(isOpen) {
								filtertemp.push(shopVO);
							}
						}
					}					
				}
			}
		});
		pagelist(filtertemp);
	}
});

$('.searchResult').on('click', ".result-direction", function () {
	$(this).closest('form').submit();
});

$('body').on('click', ".map-popup-direction", function () {
	$(this).closest('form').submit();
});