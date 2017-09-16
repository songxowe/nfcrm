$(document).ready(function() { 
      
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/queryPercentageInfo.do",
        async: true,
        dataType: "json",
        success: function(data){
        	 if(data!=null){
        		 $("#percentageBalance").html(data.percentageBalance);
        		 $("#percentageRuleName").html(data.percentageRuleName);
        		 $("#assistantUserName").html(data.assistantUserName);
        		 
        	 }  
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
})