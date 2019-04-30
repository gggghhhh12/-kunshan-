$(document).ready(function() {
	$("#links dd").hide();
	$("#links dt").each(function() {
		var dt = $(this);

		// 找到dt下的dd
		var objList = new Array();
		var tmpDD = dt.next();
		// 循环查找dt下的所有dd，并且不查找下一个dt的内容
		while (tmpDD.attr("tagName") != null && tmpDD.attr("tagName") != "DT") {
			// push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。
			objList.push(tmpDD);
			// 循环添加获取到的dd元素
			tmpDD = tmpDD.next();
		}
		// alert(dt.html()+" 有:"+objList.length+"个dd");

		dt.toggle(function() {
			$(objList).each(function() {
				$(this).show();
			});
		},

		function() {
			$(objList).each(function() {
				$(this).hide();
			});
		});

	});

});
