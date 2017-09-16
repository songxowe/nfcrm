package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.TeamOrganizationMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.pojo.TeamOrganization;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.TeamOrganizationService;
import com.sd.farmework.service.UserInfoService;
/**
 * 团队机构
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class TeamOrganizationServiceImpl   extends BaseInfoServiceImpl  implements TeamOrganizationService {

	private static Logger logger = Logger.getLogger(TeamOrganizationServiceImpl.class);

	@Autowired
	private TeamOrganizationMapper baseMapper;

	public TeamOrganizationMapper getBaseMapper() {
		return baseMapper;
	}
	public void setBaseMapper(TeamOrganizationMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	@Override
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryAllList(obj);
	}
	@Override
	public List queryOrganization(TeamOrganization obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryOrganization(obj);
	}
	@Override
	public List<BaseInfo> queryAllteam(BaseInfo obj) {
		return this.baseMapper.queryAllteam(obj);
	}
	@Override
	public void updateteam(BaseInfo obj) {
		this.baseMapper.updateteam(obj);
	}

}
