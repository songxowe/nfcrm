$(document).ready(function() {
	$("a[eventtype='comitRecharge']").click(function(){
		//alert($(this).attr("RechagreID"));
		 var data={
				 rechargeRuleId:	$(this).attr("RechagreID")
				};
				 $.ajax({
		             type: "POST",
		             url: "/dreamcardservice/weixin/alipayRecharge.do",
		             data:data,
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
		});
	});
	


		
	
 
