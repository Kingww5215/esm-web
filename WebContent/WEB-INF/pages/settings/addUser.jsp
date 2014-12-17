<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/settings/addUser.js"></script>
<title>添加用户</title>
</head>
<body>
	<table id="dg" class="easyui-datagrid" title="新增用户"
		style="width: 100%; height: 200px;"
		data-options="singleSelect:true,collapsible:false,onClickRow: esm.settings.onClickRow,toolbar:esm.settings.toolbar">
		<thead>
			<tr>
				<th data-options="field:'id',width:80">用户编号</th>
				<th data-options="field:'name',width:100,editor:'text'">用户名</th>
				<th
					data-options="field:'password',width:80,align:'right',editor:'text'">密码</th>
				<th
					data-options="field:'realName',width:60,align:'center',editor:'text'">用户姓名</th>
				<th
					data-options="field:'email',width:80,align:'right',editor:'text'">电子邮件</th>
				<th data-options="field:'phone',width:250,editor:'text'">手机号</th>
				<th
					data-options="field:'roleId',width:250,formatter:function(value,row){
							return row.roleId;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								method:'get',
								url:'/settings/roles.do',
								required:true
							}
						}">角色</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</body>

</html>