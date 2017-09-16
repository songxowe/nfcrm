package com.sd.farmework.service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.UserInfo;


/**
 * 用户管理
 * @author wangchaochao
 * 2016-10-28 15:25:10
 */

 
public interface UserInfoService extends BaseInfoService{

	void updatepwd(UserInfo user);
	public BaseInfo queryall(BaseInfo obj) throws Exception;
	UserInfo queryByUserName(UserInfo obj);
}
