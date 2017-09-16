package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.RoleMapper;
import com.sd.farmework.mapper.UserAndRolesMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.RoleService;
import com.sd.farmework.service.UserAndRolesService;
import com.sd.farmework.service.UserInfoService;
/**
 * 用户角色关系
 * @author wangchaochao
 * 2016-11-10
 */
@Service
public class UserAndRolesServiceImpl   extends BaseInfoServiceImpl  implements UserAndRolesService {

	private static Logger logger = Logger.getLogger(UserAndRolesServiceImpl.class);

	@Autowired
	private UserAndRolesMapper baseMapper;
	public UserAndRolesMapper getBaseMapper() {
		return baseMapper;
	}
	public void setBaseMapper(UserAndRolesMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	
	
	@Override
	public List<BaseInfo> queryList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryList(obj);
	}
	@Override
	public void delete(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.delete(obj);
	}
	@Override
	public void add(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.add(obj);
	}

	
	
	



}
