package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.UserAdmin;



/**
 * 用户管理
 * @author  
 * 2016-10-28 15:25:10
 */

 
public interface UserAdminService extends BaseInfoService{

	void delete(UserAdmin user);
	public List queryUserInfo(BaseInfo obj)throws Exception;
	List queryUserIdAndName(UserAdmin obj);
	public List<BaseInfo> queryall(BaseInfo obj);
	UserAdmin queryByEmployeeId(String level_id);
	List queryidbygroup(BaseInfo obj);
	void updateByEId(UserAdmin users);
}
