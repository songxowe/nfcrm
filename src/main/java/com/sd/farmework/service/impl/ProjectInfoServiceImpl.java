package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.ProjectInfoMapper;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.service.ProjectInfoService;

public class ProjectInfoServiceImpl extends BaseInfoServiceImpl implements ProjectInfoService{

	@Autowired
	private ProjectInfoMapper baseMapper;

	public ProjectInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(ProjectInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public List<BaseInfo> queryList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryList(obj);
	}

	@Override
	public void addpro(BaseInfo baseinfo) {
		this.baseMapper.addpro(baseinfo);
	}

	@Override
	public void updatepro(BaseInfo baseinfo) {
		this.baseMapper.updatepro(baseinfo);
	}

	@Override
	public void updatestatus(BaseInfo baseinfo) {
		this.baseMapper.updatestatus(baseinfo);
	}

	@Override
	public List<BaseInfo> updatedquery(BaseInfo baseinfo) {
		return this.baseMapper.updatedquery(baseinfo);
	}

	@Override
	public List queryPro(ProjectInfo obj) {
		return this.baseMapper.queryPro(obj);
	}

	@Override
	public List<BaseInfo> querytype(BaseInfo baseinfo) {
		return this.baseMapper.querytype(baseinfo);
	}

	@Override
	public String queryByOneString(BaseInfo baseinfo) {
		// TODO Auto-generated method stub
		return baseMapper.queryByOneString(baseinfo);
	}

}
