$(document).ready(function() { 
      
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/account.do",
        async: true,
        dataType: "json",
        success: function(data){
        	$(data).each(function(index){  
        		$("#loginName").text(this.loginName);
        		$("#assistantUserName").text(this.assistantUserName);
        		$("#assistantUserSex").text(this.assistantUserSex);
        		$("#assistantUserNo").text(this.assistantUserNo);
        		$("#assistantUserDesc").text(this.assistantUserDesc);
        		$("#assistantUserPhone").text(this.assistantUserPhone);
        		$("#assistantUserAddress").text(this.assistantUserAddress);
        		$("#storesName").text(this.storesName);
        		
        	});   
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
})