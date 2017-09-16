<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
 <%@ page import="com.sd.farmework.services.util.wx.wxpay.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>jsapi微信支付前台demo</title>
  <meta name="keywords" content="关键字">
  <meta name="description" content="描述">
  <meta name="content-type" content="text/html;charset=gbk">
  
  <script src="jquery.min.js" type="text/javascript"></script>
  <style>
	.choose{width:500px;height:300px;margin:0 auto;background:#FCF;margin-top:150px;text-align:center;padding-top:100px;}
  </style>
	
 </head>

 <body>
	
	<div align="center">
		<p >请注意，微信公众平台的授权目录一定要配置到这也页面所在的目录哦</p>
	</div>
	
	<div class="choose" >
		Click me to pay!
		<br><br>
		QQ:553018567
	</div>
 </body>

 <script>
 <%WxPayDto tpWxPay = new WxPayDto();
	tpWxPay.setOpenId("oFYpzwQIXaJSOfnJEb1oeBLINW9w");
	tpWxPay.setBody("商品信息");
	tpWxPay.setOrderId(WxChatPayUtil.getNonceStr());
	tpWxPay.setSpbillCreateIp("127.0.0.1");
	tpWxPay.setTotalFee("0.01");
	String str = WxChatPayUtil.getPackage(tpWxPay);%>
 	$(document).ready( function(){
		//点击测试,注意参数是demo中生成的package
		$(".choose").click(function(){
		    WeixinJSBridge.invoke('getBrandWCPayRequest',{<%=str%>},
		    function(res){
		       //支付成功或失败前台判断
    	       if(res.err_msg=='get_brand_wcpay_request:ok'){
    	    	   alert('恭喜您，支付成功!');
    	       }else{
    	    	   alert('支付失败');
    	       }
		     });
	    
	    });
  });
 </script>
</html>