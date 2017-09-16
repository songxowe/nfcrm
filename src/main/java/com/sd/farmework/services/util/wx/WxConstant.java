package com.sd.farmework.services.util.wx;

public class WxConstant {
	public static String wxAppId = "wx1d3b41e646164be4"; // 微信AppId
	public static String wxSecret = "555b435983fb071ee219e109359f9cd9"; // 微信秘钥
	public static String wxServer = "https://api.weixin.qq.com/"; // 微信服务器地址

	
//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	public static String partner = "1389925502";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	public static String partnerkey = "z9YWJT9LfKnKHGlsSr1RYpNYqQLwXYbb";
	//openId 是微信用户针对公众号的标识，授权的部分这里不解释
	//微信支付成功后通知地址 必须要求80端口并且地址不能带参数  需修改
	public static String notifyurl = "http://www.yangytest.com/weChatpay_demo/notifyurl.jsp";																	 // Key

}
