$(document).ready(function() { 
	$.ajax({ 
        type: "POST",
        url: "/dreamcardservice/weixin/queryPackageList.do",
        async: true,
        dataType: "json",
        success: function(data){
        	if(data.length>0){
      		  var html = template('personTmpl',{list: data});
    		  document.getElementById('packagelist').innerHTML = html;
      	    }
        },error:function(rec){
       	      console.info("网络异常,请稍后再试");
        }
    });
	$("#buybody").click(function(){
		var data={
				packageId:	$("#buy").attr("Packageinfo")
			};
		$.ajax({ 
	        type: "POST",
	        data:data,
	        url: "/dreamcardservice/weixin/alipay.do",
	        async: true,
	        dataType: "json",
	        success: function(data){
	        	 $("body").append(data.key);
	        	 
	        	    var queryParam = '';

	        	    Array.prototype.slice.call(document.querySelectorAll("input[type=hidden]")).forEach(function (ele) {
	        	        queryParam += ele.name + "=" + encodeURIComponent(ele.value) + '&';
	        	    });

	        	    var gotoUrl = document.querySelector("#alipaysubmit").getAttribute('action') + '&' + queryParam;
	        	    _AP.pay(gotoUrl);
	        },error:function(rec){
	       	      console.info("网络异常,请稍后再试");
	        }
	    });
	})
}); 
function drawcode(code){
	var qrcode = new QRCode(document.getElementById("ckcodeimg"), {
        width : 125,//设置宽高
        height : 125
    });
    qrcode.makeCode(code);
}
function ChangeColor(e)
{
	var id=$(e).attr("id");
	$(".column-list li").removeClass("selectbuy");
	 $(e).addClass("selectbuy");
	 $("#buy").attr("Packageinfo",id);
}


		
	
 
