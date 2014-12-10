var esm = esm == null ? new Object() : esm;
esm.common = esm.common == null ? new Object() : esm.common;

/**
 * 添加选项卡
 */
esm.common.tab = esm.common.tab == null ? new Object() : esm.common.tab;
esm.common.tab.create = function(name, url) {
	String
	html = "";
	jQuery.get(url, function(data) {
		html = data;
	});
	jQuery('#workArea').tabs('add', {
		title : name,
		content : '<div style="padding:10px">' + html + '</div>',
		closable : true
	});
};