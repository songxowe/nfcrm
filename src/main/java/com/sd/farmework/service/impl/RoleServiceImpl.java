package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.RoleMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.pojo.UserRole;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.RoleService;
import com.sd.farmework.service.UserInfoService;
/**
 * 新增角色
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class RoleServiceImpl   extends BaseInfoServiceImpl  implements RoleService {

	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleMapper baseMapper;

	public RoleMapper getBaseMapper() {
		return baseMapper;
	}
	public void setBaseMapper(RoleMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public void add(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		baseMapper.add(obj);
	}
	@Override
	public List<BaseInfo> queryList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryList(obj);
	}
	@Override
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryAllList(obj);
	}
	@Override
	public List queryAllUserAndRole(UserRole obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryAllUserAndRole(obj);
	}
	@Override
	public int queryCountByUserAndRole(UserRole obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryCountByUserAndRole(obj);
	}
	@Override
	public UserRole queryByRoNo(String user_no) {
		// TODO Auto-generated method stub
		return baseMapper.queryByRoNo(user_no);
	}

}
