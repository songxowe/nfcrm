package com.sd.farmework.services.util.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088421836925215";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
 	//自己生成 使用openssl工具 生成公私钥
    public static final String private_key       = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMEzDgDXqgOlffbEiEokppeLbkEZekJSFSYpr9b3aBosDovMBWoXp4UeyH/LzgUVQiuOxTSBZC3XZrkgbhcw3nYjYIELrbxdIS97WsZrIFOnxchvqETlfF+KiaCZKALUN6RvSFXihON7QQsjfM5/46xeXXZh4qtn8JlIQindj97TAgMBAAECgYA2Jpo5MDmN9he9T86ngWhUN5d6laqH5jvZS3vms8ew9kJJerDV0LV6gWtPLo+bKx812Dl8AvxSQPqgzu8sft0LIOCdsOtvjVTnU1qNheNLkIOVbM/rRsGrxpPO8iZSVNzM5HrPkMP4czeMKr+GyeJ/W4M14BxWrvycCNvWi/y9iQJBAO3//MO8Atn7t395yhdapaAvDb9HafxoOOK+dmU4W24P/R6NngDOBGZ4lWlJz1gtb3qeKrcCc6rXancmzyW5WV0CQQDPz6qgezAdh2CmjBYIstv0AYMBzf2lEbf31jyyfW/4jmWkffRUjsl2iKrEADftQhLUPe5te4PpQlZOcJWhVyXvAkEAhptDkEijrDnrnJN7Mx43kpNKSSbQ0Mr5cXSbbcAkJemxFTivZnEel/4XMwdl189Kv6T5L6yZUsSnGGDAw+tCmQJAKHXuasCRZa5uCv9LavYoDSIeN8O7luAUSZTUzIvPuT1AXP6JC82hPArv9Kl2OiObNue3/GkIDep0k2EoQPmO0QJBAJRQtokn2Y1jISnfsnGRf88+1Eyabm481BjktupQOUJNdfOqYLzit0nFV3RtTK0eX/MorhgxGwCr4BNHOlSl4+4=";
    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
 
	
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
    //自己生成 使用openssl工具 生成公私钥
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	public static String ALIPAY_PUBLIC_KEY  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";


	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "/dreamcardservice/weixin/getalipayNotice.do";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问  成功地址
	//购买套餐返回地址
	public static String return_url = "/dreamcardservice/weixin/getalipay.do";
	//充值返回地址
	public static String recharge_return_url = "/dreamcardservice/weixin/getalipayRecharge.do";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

