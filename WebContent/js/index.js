var esm = esm == null ? new Object() : esm;
esm.index = esm.index == null ? new Object() : esm.index;
esm.index.menu = esm.index.menu == null ? new Object() : esm.index.menu;
/**
 * 初始化菜单
 */
esm.index.menu.init = function() {
	jQuery.ajax({
		url : "/getPermissions.do",
		type : "get",
		success : function(data, status, xhr) {
			var permissions = [];
			for ( var i in data) {
				permissions.push(data[i]);
			}
			// console.info(permissions);
			var trees = [];
			esm.index.menu.buildTree(permissions, trees);
			console.info(trees);

			var html = new Array();
			html = esm.index.menu.buildMenuHtml(trees, html);

			jQuery("#tt").html(html)
		}
	});
};

esm.index.menu.buildTree = function(permissions, trees) {
	for (var i = 0; i < permissions.length; i++) {
		var item = permissions[i];
		esm.index.menu.build(item, permissions, trees);
	}

};
esm.index.menu.buildMenuHtml = function(trees, html) {
	for (var i = 0; i < trees.length; i++) {
		var tree = trees[i];
		html.push("<li>");

		if (tree.children != null && tree.children.length > 0) {
			html.push("<span>" + tree.text + "</span>");
			html.push("<ul>");
			esm.index.menu.buildMenuHtml(tree.children, html);
			html.push("</ul>");
		} else {
			html.push(tree.text);
		}
		html.push("</li>");
	}
	return html;
}
esm.index.menu.build = function(node, permissions, trees) {
	if (node.leaderId == 0) {
		esm.index.menu.toTree(node);
		trees.push(node);
	} else if (node.leaderId > 0) {
		for (var j = 0; j < permissions.length; j++) {
			var item = permissions[j];
			if (node.id == item.id) {
				continue;
			}
			if (node.leaderId == item.id) {
				esm.index.menu.toTree(node);
				item.children = item.children == null ? [] : item.children;
				item.children.push(node);
			}
		}
	}
};
esm.index.menu.toTree = function(permission) {
	if (permission.url.trim().length < 1) {
		permission.text = permission.name;
	} else {
		permission.text = "<a href=\"javascript:esm.common.tab.create('"
				+ permission.name + "','" + permission.url + "');\" title=\""
				+ permission.description + "\">" + permission.name + "</a>"
	}
	permission.state = "closed";

	delete permission.description;
	delete permission.leaderId;
	delete permission.name;
	delete permission.seq;
	delete permission.url;
};