var esm = esm == null ? new Object() : esm;
esm.index = esm.index == null ? new Object() : esm.index;
esm.index.menu = esm.index.menu == null ? new Object() : esm.index.menu;
/**
 * 初始化菜单
 */
esm.index.menu.init = function() {
   jQuery.ajax({
	   url:"/getPermissions.do",
	   type:"get",
	   success:function(data,status,xhr){
		   var permissions = [];
		   for(var i in data){
			   permissions.push(data[i]);
		   }
		   console.info(permissions);
	   }
   });
}