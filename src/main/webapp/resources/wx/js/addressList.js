$(document).ready(function() { 
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/queryVipInfoAddressList.do",
        async: false,
        dataType: "json",
        success: function(data){
        	if(data.length>0){
             			var html = template('personTmpl',{list: data});
            			document.getElementById('addrList').innerHTML = html;
              }
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
	$('.radio font').live('click',function(e){
		var data={
				vipAddressId:$(this).attr("adi"),
				isDefault:"0"
		}
		$.ajax({ 
            type: "POST",
            url: "/dreamcardservice/weixin/updateAddress.do",
            data:data,
            async: false,
            dataType: "json",
            success: function(data){
            	if(data.code=="000"){
            		window.location="addressList.html";
            		//$('.radio font').removeClass('active');
                    //e.currentTarget.className="active";
            	}
            },error:function(rec){
           	      console.info("网络异常,请稍后再试");
            }
        }); 
 		
    }); 
}); 


		
	
 
