$(document).ready(function() {
	$("#submit").click(function() {

		var str = "";// 选中的checkbox的值
		var items = $("input[type='checkbox']:checked");
		var userid = $("#userid").val();
		for (var i = 0; i < items.length; i++) {
			if ((i + 1) == items.length) {
				str += items[i].id + "";
			} else {
				str += items[i].id + ",";
			}
		}

		var data = {
			tempItems : str,
			userid : userid
		};

		$.ajax({
			type : "POST",
			url : "/NfCrm/admin/addUserRole.do",
			data : data,
			async : false,
			dataType : "json",
			success : function(data) {
				if (data.code == '000') {
					swal({
						title : "系统提示",
						text : "变更权限成功！",
						type : "success",
						showCancelButton : false,
						confirmButtonColor : "#DD6B55",
						confirmButtonText : "确定"
					}, function() {
						window.location = "../useradmin/queryUserInfo.do";
					});
				}

			},
			error : function(rec) {
				console.info("网络异常,请稍后再试");
			}
		});

	});
});

$("input[type='checkbox']").click(function() {
	$("input[type='checkbox']").attr("checked", false);
	$(this).attr("checked", true);
});