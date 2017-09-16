package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.TeamOrganization;


/**
 * 团队机构
 * @author wangchaochao
 * 2016-10-28 15:25:10
 */

 
public interface TeamOrganizationService extends BaseInfoService{
	
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception;

	public List queryOrganization(TeamOrganization obj);
	
	public List<BaseInfo> queryAllteam(BaseInfo obj);
	
	public void updateteam(BaseInfo obj);
}
