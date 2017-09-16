$(document).ready(function() { 
		window.vipAddressId=window.location.search.split("=")[1];
	    var data={
	    		vipAddressId:window.vipAddressId
	    };
		$.ajax({ 
            type: "POST",
            url: "/dreamcardservice/weixin/queryVipInfoAddress.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
                    if(data!=null){
                    	$("#receiverName").val(data.receiverName),
                    	$("#receiverPhone").val(data.receiverPhone);
       	 			 	$("#province").val(data.province);
       	 			 	$("#city").val(data.city);
       	 			 	$("#zone").val(data.zone);
       	 			 	$("#remark").val(data.remark);
       	 			 	$("#addressDetail").val(data.addressDetail);
       				 	$("#postCode").val(data.postCode);
                    }
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        });
	 
 	
		
		$("#editAddress").click(function(){
			var isDefault;
			if($("#isDefault").attr("checked")=="checked"){
				isDefault=0;
			}
			var data={
					 vipAddressId:window.vipAddressId,
		 			 receiverName:$("#receiverName").val(),
					 receiverPhone:$("#receiverPhone").val(),
		 			 province:$("#province").val(),
					 city:$("#city").val(),
					 zone:$("#zone").val(),
					 remark:$("#remark").val(),
					 addressDetail:$("#addressDetail").val(),
					 postCode:$("#postCode").val(),
					 isDefault:isDefault
			};
			$.ajax({ 
	            type: "POST",
	            url: "/dreamcardservice/weixin/updateAddress.do",
	            data:data,
	            async: false,
	            dataType: "json",
	            success: function(data){
	                    if(data.code=="000"){
	                    	location.href="addressList.html";
	                    }else{
	                    	alert("更新失败");
	                    }
	            },error:function(rec){
	           	      console.info("网络异常,请稍后再试");
	            }
	        });
		});
	
}); 


		
	
 
