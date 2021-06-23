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