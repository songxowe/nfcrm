$(document).ready(function() { 
    
	window.code=window.location.search.split("=")[1];
	$("#registerBtn").click(function(){
		
		if(""==$("#vipName").val()){
			alert("请输入会员姓名");
			return ;
		}
		
		if($("#vipName").val().length>5){
			alert("会员姓名不能大于5个汉字");
			return ;
		}
		
		if(""==$("#vipPhone").val()){
			alert("请输入会员电话");
			return ;
		}
		 if(!(/^1[3|4|5|7|8]\d{9}$/.test($("#vipPhone").val()))){ 
			 alert("手机号码有误，请重填"); 
			 return; 
		 }
		
		var data={
			 code:window.code,
			 vipName:$("#vipName").val(),
			 vipPhone:$("#vipPhone").val()
		}
		 $.ajax({
             type: "POST",
             url: "/dreamcardservice/weixin/addVipPanel.do",
             data:data,
             async: false,
             dataType: "json",
             success: function(data){
                       if(data.code=="000"){
                    	   location.href="success.html";
                       }else{
                    	   alert("网络异常,请稍后再试");
                       }   
             },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
	})

}); 


		
	
 
