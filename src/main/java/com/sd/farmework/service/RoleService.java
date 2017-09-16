package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.UserRole;


/**
 * 新增角色
 * @author wangchaochao
 * 2016-10-28 15:25:10
 */

 
public interface RoleService extends BaseInfoService{
	
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception;

	public List queryAllUserAndRole(UserRole obj);

	public int queryCountByUserAndRole(UserRole obj);

	public UserRole queryByRoNo(String user_no);

}
