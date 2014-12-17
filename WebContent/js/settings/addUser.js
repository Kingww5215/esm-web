var esm = esm == null ? new Object() : esm;
esm.settings = esm.settings == null ? new Object() : esm.settings;
esm.settings.addUser = esm.settings.addUser == null ? new Object()
		: esm.settings.addUser;
esm.settings.addUser.cussor = 0;
esm.settings.onClickRow = function(index) {
	cussor = index;
	$('#dg').datagrid('beginEdit', index);
}

/** 设置工具栏 */
esm.settings.toolbar = [ {
	text : '保存',
	iconCls : 'icon-save',
	handler : function() {
		$('#dg').datagrid("endEdit", esm.settings.addUser.cussor);
		var rows = jQuery("#dg").datagrid("getRows");
		console.info(rows[0]);
		if (rows != null && rows.length == 1) {
			jQuery.ajax({
				url : "/settings/addUser.do",
				type : "post",
				data : rows[0],
				success : function(data, status, xhr) {
					var message = "";
					if (data.check < 0) {
						message = "数据不合法.错误码:" + data.check
					} else if (data.success) {
						message = "添加成功!";
					} else {
						message = "添加失败!";
					}
					jQuery.messager.alert("提示", message);
				}
			});
		}
	}
} ];