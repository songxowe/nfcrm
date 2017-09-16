$(document).ready(function() { 
 	$.ajax({ 
             type: "POST",
             url: "/dreamcardservice/weixin/queryVisitorCredentialList.do",
             async: true,
             dataType: "json",
             success: function(data){
            	  if(data.length>0){
            		  var html = template('personTmpl',{list: data});
          			  document.getElementById('salesList').innerHTML = html;
            	  }
             },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
 
	
}); 


		
	
 
