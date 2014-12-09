<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>XXXX-ZZZ</title>
<link rel="stylesheet" type="text/css"
	href="/ui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="/ui/themes/icon.css">
<script type="text/javascript" src="/ui/jquery.min.js"></script>
<script type="text/javascript" src="/ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript">
	window.onload = function() {
		//初始化功能菜单
	};
</script>
</head>
<body class="easyui-layout">
	<!-- 头部 开始 -->
	<div data-options="region:'north',border:false,split:true"
		style="height: 80px; background: #666; padding: 10px">
		<h1>ＸＸＸ</h1>
	</div>
	<!-- 头部结束 -->

	<!-- 左侧功能菜单 开始 -->
	<div data-options="region:'west',split:true,title:'功能菜单',"
		style="width: 30%; padding: 10px;">
		<div class="easyui-panel" style="padding: 5px">
			<ul id="tt" class="easyui-tree" data-options="url:'/getPermissions.do',method:'get',animate:true"></ul>
		</div>
	</div>
	<!-- 　左侧功能菜单 结束 -->

	<!-- 工作区 开始 -->
	<div data-options="region:'center',title:''">
		<div id="workArea" class="easyui-tabs"
			data-options="split:true,border:false"
			style="width: 100%; height: 100%;">
			<div title="系统说明" data-options="iconCls:'icon-help',closable:true"
				style="padding: 5px;">
				<p style="font-size: 14px">系功功能说明.</p>
				<ul>
					<li>用户管理.</li>
					<li>权限管理.</li>
					<li>线路／开关管理.</li>
					<li>complete framework for HTML5 web page.</li>
					<li>easyui save your time and scales while developing your
						products.</li>
					<li>easyui is very easy but powerful.</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- 工作区　结束 -->
</body>
</html>