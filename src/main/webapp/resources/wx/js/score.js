$(document).ready(function() {
 	$.ajax({ 
             type: "POST",
             url: "/dreamcardservice/weixin/ScoreInfoList.do",
             async: true,
             dataType: "json",
             success: function(data){
            	  if(data.length>0){
            		  var html = template('personTmpl',{list: data});
          			  document.getElementById('scores').innerHTML = html;
            	  }
             },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });	
}); 


		
	
 
