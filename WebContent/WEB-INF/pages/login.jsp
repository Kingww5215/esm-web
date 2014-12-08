<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>包头供电局－ＸＸＸ系统登录</title>
<link rel="stylesheet" type="text/css"
	href="/ui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="/ui/themes/icon.css">
<script type="text/javascript" src="/ui/jquery.min.js"></script>
<script type="text/javascript" src="/ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/access-control.js"></script>
</head>
<body>
	<div id="dlg" class="easyui-dialog" title="包头供电局－ＸＸ系统"
		style="width: 400px; height: 200px; padding: 10px;">
		<table cellpadding="5">
			<tr>
				<td>用户名:</td>
				<td><input class="easyui-textbox" type="text" name="userName"></input></td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;&nbsp;码:</td>
				<td><input class="easyui-textbox" type="password" name="password"></input></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center">
					<div style="margin: 10px 0;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="esm.login();">登录</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="">重填</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>

</html>