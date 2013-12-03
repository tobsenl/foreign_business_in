$(document).ready(function() {
	function getUrl(v, a, b) {
		var page_url = $("#pageurl").val();
		var url = page_url.split("?");
		var pageurl = url[0].replace(a, b);
		var attr = url[1].split("&");
		var s = 0;
		for ( var i = 0; i < attr.length; i++) {
			if (v == "page") {
				if (attr[i].match("pagesize") != null) {
					continue;
				} else if (attr[i].match("nowpage") != null) {
					continue;
				} else {
					if (s == 0) {
						pageurl = pageurl + "?" + attr[i];
					} else if (s > 0) {
						pageurl = pageurl + "&" + attr[i];
					}
					s++;
				}
			} else if (v == "query") {
				if (attr[i].match("kind") != null) {
					pageurl = pageurl + "?" + attr[i];
				}
			}
		}
		return pageurl;
	}
	function check_submit(e) {
		var e = e.target;
		var name = e.nodeName;
		var now_index = "";
		if (name == "A") {
			now_index = $(e).attr("name");
		}
		var page_size = $("#pagesize").val();
		var allcount = $("#allcount").val();
		var page_url = $("#pageurl").val();
		var attr = page_url.split("&");
		var pageurl = getUrl("page");
		var allpagesize = getUrl("allpagesize");
		$("#nowpage").val(now_index);
		if (parseInt(page_size) > parseInt(allcount)) {
			alert("每页显示数不能超出总数据量！请重新填写每页显示数");
		} else if (parseInt(allpagesize) > parseInt(now_index)) {
			alert("已经是最后一页!");
		} else {
			$("#page").attr("action", pageurl);
			$("#page").submit();
		}
	}
	$("#pagination-clean").on("click", "a", function(e) {
		check_submit(e);
	});
	$("#pagination-clean").on("blur", "input", function(e) {
		check_submit(e);
	});
});