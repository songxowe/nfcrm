package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.EmployeeInfoMapper;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.service.EmployeeInfoService;

public class EmployeeInfoServiceImpl extends BaseInfoServiceImpl implements EmployeeInfoService{

	@Autowired
	private EmployeeInfoMapper baseMapper;

	public EmployeeInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(EmployeeInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public List queryEmployeeInfo(EmployeeInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryEmployeeInfo(obj);
	}

	@Override
	public void changeTryOut(EmployeeInfo emp) {
		// TODO Auto-generated method stub
		baseMapper.changeTryOut(emp);
	}

	@Override
	public void changeForMal(EmployeeInfo emp) {
		// TODO Auto-generated method stub
		baseMapper.changeForMal(emp);
	}

	@Override
	public void changeLeav(EmployeeInfo emp) {
		// TODO Auto-generated method stub
		baseMapper.changeLeav(emp);
	}

	@Override
	public String queryByOneString(EmployeeInfo emp) {
		// TODO Auto-generated method stub
		return baseMapper.queryByOneString(emp);
	}

}
