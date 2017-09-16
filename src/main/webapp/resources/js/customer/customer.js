
var str = "";
var arrayobj = new Array();

function checkin(e) {
	if (e.checked) {
		arrayobj.push(e.id);
	} else {
		$.each(arrayobj, function(index, item) {
			// index是索引值（即下标） item是每次遍历得到的值；
			if (item == e.id) {
				arrayobj.splice(index, 1);
			}
		});
	}
}
function checkall(e) {
	if (e.checked) {
		$("input[name='incheck']").attr("checked", true);
		var inche = $("input[name='incheck']");
		for (var i = 0; i < inche.length; i++) {
			var newid = inche[i].id;
			arrayobj.push(newid);
		}
	} else {
		$("input[name='incheck']").attr("checked", false);
		var inche = $("input[name='incheck']");
		for (var i = 0; i < inche.length; i++) {
			var newid = inche[i].id;
			$.each(arrayobj, function(index, item) {
				// index是索引值（即下标） item是每次遍历得到的值；
				if (item == newid) {
					arrayobj.splice(index, 5);
				}
			});
		}
	}
};

function haveparma() {
	if (arrayobj.length == 1) {
		window.location = "/NfCrm/customer/queryCustomertoApplygift.do?customer_id="
				+ arrayobj[0];
	} else {
		swal({title: "系统提示",text: "请选择一个客户！！",type: "fail",showCancelButton: false,confirmButtonColor: "#DD6B55",confirmButtonText: "确定"
		},
		function(){
		});
	}
}