$(document).ready(function() { 
	 
	window.mallId=window.location.search.split("=")[1];
    var data={
    		mallId:window.mallId
    };
 	$.ajax({ 
             type: "POST",
             url: "/dreamcardservice/weixin/queryMallInfo.do",
             data:data,
             async: true,
             dataType: "json",
             success: function(data){
                     $("#imageUrl").attr("src","/dreamcardservice/resources/wx/img/"+data.imageUrl);
                     $("#mallName").html(data.mallName);
                      $("#mallPoints").html(data.mallPoints);
                      window.vipId=data.vipId;
                      window.mallId=data.mallId;
                      window.mallName=data.mallName;
                      window.mallPoints=data.mallPoints;
                      window.imageUrl=data.imageUrl;
              },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
 		
 	  $("#exchangeId").click(function(){
 		 $("#changeDiv").show();
  		 $("#imageUrlBox").attr("src","/dreamcardservice/resources/wx/img/"+window.imageUrl);
         $("#mallNameBox").html(window.mallName);
         $("#mallIdBox").html(window.mallId);
          $("#mallPointsBox").html(window.mallPoints);
 	  });
	  $(".closeT").click(function(){
		  $("#changeDiv").hide();
 	  });
 	 $("#changeWare").click(function(){
 		 var mount=$("#shopInventory").val();
 		 window.location="giftListDetialOrder.html?mallId="+window.mallId+"&mount="+mount;
 		 
 	 });
 	 
 	$("#addMount").click(function(){
 		if(""==$("#shopInventory").val()){
 			$("#shopInventory").val("1");
 			return;
 		} 
		   $("#shopInventory").val(parseInt($("#shopInventory").val())+1);
 		 
	 });
 	
 	$("#deductMount").click(function(){
 		if(""==$("#shopInventory").val()){
 			$("#shopInventory").val("1");
 			return;
 		}
 		if($("#shopInventory").val()>1){
 			$("#shopInventory").val(parseInt($("#shopInventory").val())-1);
 		}
	 });
  }); 


		
	
 
