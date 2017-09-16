package com.sd.farmework.services.util.wx.wxpay;

import java.util.SortedMap;
import java.util.TreeMap;

import com.sd.farmework.services.util.wx.WxConstant;
import com.sd.farmework.services.util.wx.wxpay.util.GetWxOrderno;
import com.sd.farmework.services.util.wx.wxpay.util.RequestHandler;
import com.sd.farmework.services.util.wx.wxpay.util.Sha1Util;
import com.sd.farmework.services.util.wx.wxpay.util.TenpayUtil;






/**
 * @author ex_yangxiaoyi
 * 
 */
public class WxChatPayUtil {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String openId="";
		//微信支付jsApi
		WxPayDto tpWxPay = new WxPayDto();
		tpWxPay.setOpenId(openId);
		tpWxPay.setBody("商品信息");
		tpWxPay.setOrderId(getNonceStr());
		tpWxPay.setSpbillCreateIp("127.0.0.1");
		tpWxPay.setTotalFee("0.01");
	    getPackage(tpWxPay);
	    
//	    //扫码支付
//	    WxPayDto tpWxPay1 = new WxPayDto();
//	    tpWxPay1.setBody("商品信息");
//	    tpWxPay1.setOrderId(getNonceStr());
//	    tpWxPay1.setSpbillCreateIp("127.0.0.1");
//	    tpWxPay1.setTotalFee("0.01");
//		getCodeurl(tpWxPay1);

	}
	
//	/**
//	 * 获取微信扫码支付二维码连接
//	 */
//	public static String getCodeurl(WxPayDto tpWxPayDto){
//		
//		// 1 参数
//		// 订单号
//		String orderId = tpWxPayDto.getOrderId();
//		// 附加数据 原样返回
//		String attach = "";
//		// 总金额以分为单位，不带小数点
//		String totalFee = getMoney(tpWxPayDto.getTotalFee());
//		
//		// 订单生成的机器 IP
//		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
//		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
//		String notify_url = notifyurl;
//		String trade_type = "NATIVE";
//
//		// 商户号
//		String mch_id = partner;
//		// 随机字符串
//		String nonce_str = getNonceStr();
//
//		// 商品描述根据情况修改
//		String body = tpWxPayDto.getBody();
//
//		// 商户订单号
//		String out_trade_no = orderId;
//
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", appid);
//		packageParams.put("mch_id", mch_id);
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("body", body);
//		packageParams.put("attach", attach);
//		packageParams.put("out_trade_no", out_trade_no);
//
//		// 这里写的金额为1 分到时修改
//		packageParams.put("total_fee", totalFee);
//		packageParams.put("spbill_create_ip", spbill_create_ip);
//		packageParams.put("notify_url", notify_url);
//
//		packageParams.put("trade_type", trade_type);
//
//		RequestHandler reqHandler = new RequestHandler(null, null);
//		reqHandler.init(appid, appsecret, partnerkey);
//
//		String sign = reqHandler.createSign(packageParams);
//		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
//				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
//				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
//				+ "<body><![CDATA[" + body + "]]></body>" 
//				+ "<out_trade_no>" + out_trade_no
//				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
//				+ "<total_fee>" + totalFee + "</total_fee>"
//				+ "<spbill_create_ip>" + spbill_create_ip
//				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
//				+ "</notify_url>" + "<trade_type>" + trade_type
//				+ "</trade_type>" + "</xml>";
//		String code_url = "";
//		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//		
//		System.out.println(xml);
//	//	code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
//		System.out.println("code_url----------------"+code_url);
//		
//		xml="<xml><appid>wx1d3b41e646164be4</appid><mch_id>1389925502</mch_id><nonce_str>0044008653</nonce_str><sign>A4A751553CDE1BDC7AFCF205CBE99EFE</sign><body><![CDATA[商品信息]]></body><out_trade_no>0044007650</out_trade_no><attach></attach><total_fee>1</total_fee><spbill_create_ip>127.0.0.1</spbill_create_ip><notify_url>http://www.yangytest.com/weChatpay_demo/notifyurl.jsp</notify_url><trade_type>NATIVE</trade_type></xml>";
//		code_url = "weixin://wxpay/bizpayurl?pr=gxtVR0i";
//		return code_url;
//	}
	
	
	/**
	 * 获取请求预支付id报文
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getPackage(WxPayDto tpWxPayDto) {
		
		String openId = tpWxPayDto.getOpenId();
		// 1 参数
		// 订单号
		String orderId = tpWxPayDto.getOrderId();
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = getMoney(tpWxPayDto.getTotalFee());
		
		// 订单生成的机器 IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = WxConstant.notifyurl;
		String trade_type = "JSAPI";

		// ---必须参数
		// 商户号
		String mch_id = WxConstant.partner;
		// 随机字符串
		String nonce_str = getNonceStr();

		// 商品描述根据情况修改
		String body = tpWxPayDto.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", WxConstant.wxAppId);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(WxConstant.wxAppId, WxConstant.wxSecret, WxConstant.partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + WxConstant.wxAppId + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>" + openId + "</openid>"
				+ "</xml>";
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		System.out.println(xml);
		
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);

		System.out.println("获取到的预支付ID：" + prepay_id);
		
		
		//获取prepay_id后，拼接最后请求支付所需要的package
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String packages = "prepay_id="+prepay_id;
		finalpackage.put("appId", WxConstant.wxAppId);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		//要签名
		String finalsign = reqHandler.createSign(finalpackage);
		
		String finaPackage = "\"appId\":\"" + WxConstant.wxAppId+ "\",\"timeStamp\":\"" + timestamp
		+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
		+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		+ finalsign + "\"";

		System.out.println("V3 jsApi \npackage:"+finaPackage);
//		finalpackage=\"package:\"appId\":\"wx1d3b41e646164be4\",\"timeStamp\":\"1475859053\",\"nonceStr\":\"0050489803\",\"package\":\"prepay_id=wx20161008005059a48a2f67d40889187720\",\"signType\" : \"MD5\",\"paySign\":\"3F13DF79D4EF69D9EB2D78A2C31F1B59\"";
		return finaPackage;
	}

	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}

}
