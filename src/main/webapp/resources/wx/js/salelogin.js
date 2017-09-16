$(document).ready(function() { 
    
	//window.code=window.location.search.split("=")[1];
	$("#saleloginBtn").click(function(){
		
		if(""==$("#loginName").val()){
			alert("请输入营业员账号");
			return ;
		}
		var data={
			// code:window.code,
				loginName:$("#loginName").val(),
				loginPass:$("#loginPass").val()
		}
		 $.ajax({
             type: "POST",
             url: "/dreamcardservice/weixin/salelogin.do",
             data:data,
             async: false,
             dataType: "json",
             success: function(data){
                       if(data.code=="000"){
                    	   location.href="assistantuser/index.html";
                       }else if(data.code=="002"){
                    	   alert("用户不存在");
                       }   
             },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
	})

}); 


		
	
 
