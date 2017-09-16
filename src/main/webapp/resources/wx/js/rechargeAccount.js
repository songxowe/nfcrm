$(document).ready(function() {
		 $.ajax({
             type: "POST",
             url: "/dreamcardservice/weixin/rechargeAccount.do",
             async: true,
             dataType: "json",
             success: function(data){
            	 if(data.length>0){
            		 var html = template('personTmpl',{list: data});
           		   document.getElementById('recharges').innerHTML = html;
         	  }
             },error:function(rec){
            	      console.info("网络异常,请稍后再试");
             }
         });
	});	
 function rechargeNow(e){
	//alert(1111);
	var id=$(e).attr("rechargevalue");
	$("#Rechargeid").attr("RechagreID",id);	
	$(".column-list li").removeClass("selectbuy");
	 $(e).addClass("selectbuy");
  }
		
	
 
