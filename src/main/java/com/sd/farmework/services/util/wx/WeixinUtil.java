package com.sd.farmework.services.util.wx;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.sd.farmework.common.util.HttpClientUtil;


public class WeixinUtil {

 
	public static Map getWeiXinUserInfo(String code) {

		Map<String, String> map = getOpenId(code);
		if(!map.get("code").equals("000")){
			return map;
		}
		// 获取openId
		String openId = map.get("openid").toString();

		// 第二步获取有效的access_token
		Map<String, String> accessTokenMap = getAccessToken();
		if(!map.get("code").equals("000")){
			return map;
		}
		String accessToken = accessTokenMap.get("accessToken");

		// 第三步通过access_token和openId获取用户基本信息
		Map<String, Object> result = getWeixinUserInfo(accessToken, openId);
		return result;
	}

	public static void main(String[] args) {
 		 
		
//		String str= "http://caixiaolian.com/?code=041JQnt82auNfE0wV1u82Xojt82JQntK&state=STATE";
//		System.out.println(str.split("&")[0].split("=")[1]);
//		String code =str.split("&")[0].split("=")[1];
//		Map result = getWeiXinUserInfo(code);
//		System.out.println(result);
       
		 
		Map<String, String>  result = getAccessToken();
		String str =result.get("accessToken");
		
		String url ="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+str;
		String par=" {\"industry_id1\":\"6\",\"industry_id2\":\"4\"}";
		String strRest=HttpClientUtil.post(url, par);
		System.out.println(strRest);
		
	}

	/**
	 * 获取微信用户基本信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	private static Map<String, Object> getWeixinUserInfo(String accessToken,
			String openId) {
		try {
			// 第三步通过access_token和openId获取用户基本信息
			StringBuffer sb = new StringBuffer(WxConstant.wxServer);
			sb.append("cgi-bin/user/info?");
			sb.append("access_token=");
			sb.append(accessToken);
			sb.append("&openId=");
			sb.append(openId);
			System.out.println(sb.toString());
			String result = HttpClientUtil.get(sb.toString(), null);
			JSONObject json = JSONObject.fromObject(result);

			if (json.containsKey("errcode")) {
				String errcode = json.get("errcode").toString();
				String errmsg = json.get("errmsg").toString();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", "001");
				map.put("message", errcode + "," + errmsg);
				return map;

			} else {
				Map<String,String> wxUserInfoMap =new HashMap<String,String>();
				
				wxUserInfoMap.put("nickname",json.get("nickname").toString());
				wxUserInfoMap.put("headimgurl",json.get("headimgurl").toString());
				wxUserInfoMap.put("country",json.get("country").toString());
			    wxUserInfoMap.put("province",json.get("province").toString());
			    wxUserInfoMap.put("city",json.get("city").toString());
			    wxUserInfoMap.put("openId",json.get("openid").toString());
			    wxUserInfoMap.put("sex",json.get("sex").toString());
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("result", wxUserInfoMap);
				map.put("code", "000");
				map.put("message", "success");
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
 			map.put("code", "001");
			map.put("message", e.getMessage());
			return map;
		}

 	}

	/**
	 * 获取AccessToken
	 * 
	 * @return
	 */
	private static Map<String, String> getAccessToken() {
		// 第二步获取有效的access_token
		try {
			StringBuffer sb = new StringBuffer(WxConstant.wxServer);
			sb.append("cgi-bin/token?");
			sb.append("grant_type=client_credential");
			sb.append("&appid=");
			sb.append(WxConstant.wxAppId);
			sb.append("&secret=");
			sb.append(WxConstant.wxSecret);

			String result = HttpClientUtil.get(sb.toString(), null);
			System.out.println(result);
			JSONObject json = JSONObject.fromObject(result);

			if (json.containsKey("errcode")) {
				String errcode = json.get("errcode").toString();
				String errmsg = json.get("errmsg").toString();
				Map<String, String> map = new HashMap<String, String>();
 				map.put("code", "001");
				map.put("message", errcode + "," + errmsg);
				return map;

			} else {
				String accessToken = json.get("access_token").toString();
				Map<String, String> map = new HashMap<String, String>();
				map.put("result", result);
				map.put("code", "000");
				map.put("accessToken", accessToken);
				map.put("message", "success");
				return map;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", "001");
			map.put("message", e.getMessage());
			return map;
		}

	}

	/**
	 * 獲取微信用戶唯一標示信息
	 * @param code
	 * @return
	 */
	private static Map<String, String> getOpenId(String code) {
		// 第一步通过code获取openId
		try {
			StringBuffer sb = new StringBuffer(WxConstant.wxServer);
			sb.append("sns/oauth2/access_token?");
			sb.append("appid=");
			sb.append(WxConstant.wxAppId);
			sb.append("&secret=");
			sb.append(WxConstant.wxSecret);
			sb.append("&code=");
			sb.append(code);
			sb.append("&grant_type=authorization_code");

			String result = HttpClientUtil.get(sb.toString(), null);
			// {"access_token":"ox7LBMb3VucP1k6fnHooaLhYKxj8hNqg9nvOosgGUoU8DglRaQkFQ80TR0Rfo6mjpwJOe_80IVAYIIEj99LVViMgkqXtevo1lOnzBukqWig","expires_in":7200,"refresh_token":"D1vzkvcqxtogtRG5bebNVi0l4LzLzswyMcnOGwnlRLof7gbIvsTZ-hqonJF_6Vpg6yHKm4YT91nX35Y1KTtc5imaxnDje-tBdgOAYF66f88","openid":"oFYpzwQIXaJSOfnJEb1oeBLINW9w","scope":"snsapi_base"}
			JSONObject json = JSONObject.fromObject(result);
			if (json.containsKey("errcode")) {
				String errcode = json.get("errcode").toString();
				String errmsg = json.get("errmsg").toString();
				Map<String, String> map = new HashMap<String, String>();
 				map.put("code", "001");
				map.put("message", errcode + "," + errmsg);
				return map;
				 
				
			} else {
				Map<String, String> map = new HashMap<String, String>();
				String openId = json.get("openid").toString();
				map.put("result", result);
				map.put("openid", openId);
				map.put("code", "000");
				return map;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();

			map.put("code", "001");
			map.put("message", e.getMessage());
			return map;
		}
 	}

	// 第一步 获取 code 的值
	// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1d3b41e646164be4&redirect_uri=http://caixiaolian.com/&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
	// http://caixiaolian.com/?code=0413tEfd2rbOCR0ul8jd2FjJfd23tEfA&state=STATE
	// 第二步
	// https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1d3b41e646164be4&secret=555b435983fb071ee219e109359f9cd9&code=011fseBt11Iqu80sIUyt1x3fBt1fseB-&grant_type=authorization_code
	//
	// 第三步
	//
	// https://api.weixin.qq.com/sns/userinfo?access_token=43UAbjy4RRxbwBzwI2IGfo7e7wAUsYZOUPRVAokf244hIu5SMmxvRKt7jQr_ULlNktAdlYl6dZj38YSPAnEArakmtPaEHAjoJAU_4FwkokY&openid=oFYpzwQIXaJSOfnJEb1oeBLINW9w

}
