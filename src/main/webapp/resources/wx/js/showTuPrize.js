$(document).ready(function() { 
	window.code=window.location.search.split("=")[1];
	var data={
			scoreId:window.code
	}
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/queryScore.do",
        data:data,
        async: true,
        dataType: "json",
        success: function(data){
        	if(data!=null){
        		 $("#qrCodeUrl").html(data.codeUrl);
        	}  
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
})