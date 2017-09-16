package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.UserAdminMapper;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.UserAdmin;
import com.sd.farmework.service.UserAdminService;
/**
 * 添加用户
 * @author 
 * 2016-10-28
 */
@Service
public class UserAdminServiceImpl   extends BaseInfoServiceImpl  implements UserAdminService {

	private static Logger logger = Logger.getLogger(UserAdminServiceImpl.class);

	@Autowired
	private UserAdminMapper baseMapper;

	public UserAdminMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(UserAdminMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public List<BaseInfo> queryList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryList(obj);
	}
	@Override
	public void add(BaseInfo obj) throws Exception {
		 baseMapper.add(obj);
	}
	@Override
	public BaseInfo queryById(BaseInfo obj) throws Exception {
		return baseMapper.queryById(obj);
	}
	@Override
	public void update(BaseInfo obj) throws Exception {
		baseMapper.update(obj);
	}

	public void delete(UserAdmin user) {
		baseMapper.delete(user);
		
	}

	public List queryUserInfo(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryUserInfo(obj);
	}

	@Override
	public List queryUserIdAndName(UserAdmin obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryUserIdAndName(obj);
	}

	@Override
	public List<BaseInfo> queryall(BaseInfo obj) {
		return this.baseMapper.queryall(obj);
	}

	@Override
	public UserAdmin queryByEmployeeId(String level_id) {
		// TODO Auto-generated method stub
		return baseMapper.queryByEmployeeId(level_id);
	}

	@Override
	public List queryidbygroup(BaseInfo obj) {
		return this.baseMapper.queryidbygroup(obj);
	}

	@Override
	public void updateByEId(UserAdmin users) {
		// TODO Auto-generated method stub
		baseMapper.updateByEId(users);
	}
	
}
