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

function pagelist(){
	$('.pagination').pagination({
	    dataSource: refine,
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
	pagelist();
});

$("#orderbypopular").click(function(e){
	e.preventDefault();
	$("#search-order-now > span").text($(this).text());
	quickSortDesc(refine, 0, refine.length, "shop_total_view");
	pagelist();
});


$("#orderbycost").click(function(e){
	e.preventDefault();
	$("#search-order-now > span").text($(this).text());
	quickSortAsc(refine, 0, refine.length, "shop_price_level");
	pagelist();
});

$("#orderbydistance").click(function(e){
	e.preventDefault();
	$("#search-order-now > span").text($(this).text());
	quickSortDistance(refine, 0, refine.length, "shop_latitude", "shop_longitude");
	pagelist();
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