$(document).ready(function() { 
	window.code=window.location.search.split("=")[1];
    var data={
    		code:window.code
    };
 	$.ajax({ 
             type: "POST",
             url: "/dreamcardservice/weixin/queryAccountInfoList.do",
             data:data,
             async: false,
             dataType: "json",
             success: function(data){
                      $("#accountBlance").html(data.accountBalance);
                      $("#pointsBlance").html(data.pointsBlance);
                      $("#headimgurl").attr("src",data.headimgurl);
                      $("#provice").html(data.provice);
                      $("#vipPhone").html(data.vipPhone);
                      window.vipId=data.vipId;
              },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
	$("#accountHref").click(function(){
			window.location.href="accountDetail.html?vipId="+window.vipId;
	});
	$("#pointsHref").click(function(){
		window.location.href="pointsDetail.html?vipId="+window.vipId;
	});
	
}); 


		
	
 
