var esm = esm == null ? new Object() : esm;
esm.common = esm.common == null ? new Object() : esm.common;

/**
 * 添加选项卡
 */
esm.common.tab = esm.common.tab == null ? new Object() : esm.common.tab;
esm.common.tab.create = function(name, url) {
	jQuery.get(url, function(data) {
		jQuery('#workArea').tabs('add', {
			title : name,
			async : false,
			content : '<div style="padding:10px">' + data + '</div>',
			closable : true
		});
	});
};