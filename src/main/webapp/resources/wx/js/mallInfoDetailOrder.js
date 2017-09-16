$(document).ready(function() {
	var data={
			isDefault:"0"
	}
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/queryPointsInfo.do",
        async: false,
        data:data,
        dataType: "json",
        success: function(data){
        	if(data!=""){
         		$("#pointsBlance").html(data.pointsBalance);
          		 
        	}
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/queryVipInfoAddressList.do",
        async: false,
        data:data,
        dataType: "json",
        success: function(data){
        	if(data.length==1){
        		$(".noAddress").hide();
        		$("#addressLink").show();
        		$("#receiverName").html(data[0].receiverName);
        		$("#receiverPhone").html(data[0].receiverPhone);
          		$("#province").html(data[0].province);
        		$("#city").html(data[0].city);
        		$("#zone").html(data[0].zone);
        		$("#addressDetail").html(data[0].postCode);
        		$("#postCode").html(data[0].addressDetail);
        		
        		window.receiverName1=data[0].receiverName;
        		window.receiverPhone1=data[0].receiverPhone;
        		window.province1=data[0].province;
        		window.city1=data[0].city;
        		window.zone1=data[0].zone;
        		window.addressDetail1=data[0].addressDetail;
        		window.postCode1=data[0].postCode;
        	}else if(data.length==0){
        		$(".noAddress").show();
        		$("#addressLink").hide();
        	}
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
	
	window.mallId=window.location.search.split("=")[1];
	window.mount=window.location.search.split("=")[2];
    var data={
    		mallId:window.mallId,
    		mount:window.mount
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
                      $(".total i").html(window.mount*data.mallPoints);
                      $(".price i").html(window.mount);
                      $(".singleAllIntegral i").html(data.mallPoints);
                      
                      window.total=window.mount*data.mallPoints;
                      window.mallId=data.mallId;
                      window.mallName=data.mallName;
                      window.mallPoints=data.mallPoints;
                      window.imageUrl=data.imageUrl;
                     
             },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
	 	$("#submitOrderDiv").click(function(){
	 		var data={
	 				mallId:window.mallId,
	 				mallName:window.mallName,
	 				mallPoints:window.total,
	 				imageUrl:window.imageUrl,
	 				receiverName:window.receiverName1,
					receiverPhone:window.receiverPhone1,
		    		province:window.province1,
		    		city:window.city1,
		    		zone:window.zone1,
		    		addressDetail:window.addressDetail1,
		    		postCode:window.postCode1
	 		};
	 		$.ajax({ 
	             type: "POST",
	             url: "/dreamcardservice/weixin/addVipMallInfo.do",
	             data:data,
	             async: true,
	             dataType: "json",
	             success: function(data){
	                     if(data.code=="000"){
	                    	  window.location="success.html";
	                     }else{
	                    	 alert("网络异常,请稍后再试");
	                     }
	             },error:function(rec){
	            	      console.info("网络异常,请稍后再试");
	             }
	         });	 
		 });
 }); 


		
	
 
