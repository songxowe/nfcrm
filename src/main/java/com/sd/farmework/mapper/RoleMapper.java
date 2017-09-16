package com.sd.farmework.mapper;



import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.UserRole;
/**
 * 新增角色
 * @author wangchaochao
 * 2016-10-28 15:25:19
 */
public interface RoleMapper extends BaseMapper {
	 
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception;

	public List queryAllUserAndRole(UserRole obj);

	public int queryCountByUserAndRole(UserRole obj);

	public UserRole queryByRoNo(String user_no);

 }
