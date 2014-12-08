var esm = esm == null ? new Object() : esm;
esm.login = function() {
	var map = new Object();
	map.userName = jQuery("input[name=userName]").val();
	map.password = jQuery("input[name=password]").val();
	map.verifyCode = "";
	console.info(map);
	jQuery.ajax({
		url : "/login.do",
		type : "post",
		dataType : "json",
		data : map,
		success : function(data, status, xhr) {
			if (data.logined) {
				window.location = '/index.do';
			}
		},
		error : function() {

		}
	});
};
esm.logout = function() {

};
