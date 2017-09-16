package com.sd.farmework.mapper;



import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.UserAdmin;
/**
 * 用户管理 
 * @author  
 * 2016-10-28 15:25:19
 */
public interface UserAdminMapper extends BaseMapper {

	public void delete(UserAdmin user);
	public List queryUserInfo(BaseInfo obj)throws Exception;
	List queryUserIdAndName(UserAdmin obj);

	public List<BaseInfo> queryall(BaseInfo obj);

	public UserAdmin queryByEmployeeId(String level_id);

	List queryidbygroup(BaseInfo obj);
	public void updateByEId(UserAdmin users);
 }
