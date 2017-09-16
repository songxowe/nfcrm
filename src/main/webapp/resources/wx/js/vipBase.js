$(document).ready(function() { 
      
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/queryVip.do",
        async: true,
        dataType: "json",
        success: function(data){
        	if(data!=null){
        		$("#nickname").text(data.nickname);
         		$("#vipName").text(data.vipName);
        		$("#vipSex").text(data.sex);
        		$("#vipPhone").text(data.vipPhone);
        		$("#vipAddress").text(data.province+data.city);
        		$("#vipNo").text(data.vipNo);
        	}  
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
})