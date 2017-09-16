package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.ProjectInfo1Mapper;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.service.ProjectInfo1Service;


public class ProjectInfo1ServiceImpl extends BaseInfoServiceImpl implements ProjectInfo1Service{

	@Autowired
	private ProjectInfo1Mapper baseMapper;

	public ProjectInfo1Mapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(ProjectInfo1Mapper baseMapper) {
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
