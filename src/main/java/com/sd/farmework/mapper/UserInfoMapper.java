package com.sd.farmework.mapper;



import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.UserInfo;
/**
 * 用户管理 
 * @author wangchaochao
 * 2016-10-28 15:25:19
 */
public interface UserInfoMapper extends BaseMapper {

	void updatepwd(UserInfo user);
	public BaseInfo queryall(BaseInfo obj) throws Exception;
	String queryByUserNo(UserInfo obj);
	UserInfo queryByUserName(UserInfo obj);
	
 }
