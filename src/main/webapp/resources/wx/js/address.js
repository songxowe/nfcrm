$(document).ready(function() { 
	$("#saveAddress").click(function(){
		var isDefault;
		if($("#isDefault").attr("checked")=="checked"){
			isDefault=0;
		}
		var data={
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
            url: "/dreamcardservice/weixin/addipInfoAddress.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
                    if(data.code=="000"){
                    	window.location="addressList.html";
                    }
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        });
	 
	});
 	
	
}); 


		
	
 
