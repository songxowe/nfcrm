$(document).ready(function() { 
	window.vipId=window.location.search.split("=")[1];
    var data={
    		vipId:window.vipId
    };
 	$.ajax({ 
             type: "POST",
             url: "/dreamcardservice/weixin/queryPercentageInfoDetailList.do",
             data:data,
             async: true,
             dataType: "json",
             success: function(data){
                    if(data.length>0){
                    	var html = template('personTmpl',{list: data});
            			document.getElementById('percentageList').innerHTML = html;
                     }
             },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
	 
	
}); 


		
	
 
