package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.SalaryMapper;
import com.sd.farmework.pojo.EmployeeSalary;
import com.sd.farmework.pojo.RolesalaryFormula;
import com.sd.farmework.service.SalaryService;

public class SalaryServiceImpl extends BaseInfoServiceImpl implements
		SalaryService {
	@Autowired
	private SalaryMapper baseMapper;

	public SalaryMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SalaryMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	/**
	 * 根据员工编号查询工资详情
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List queryByUserNo(EmployeeSalary obj) {
		return baseMapper.queryByUserNo(obj);
	}

	/**
	 * 根据角色id查询角色工资详情
	 */
	@Override
	public RolesalaryFormula queryByRoId(RolesalaryFormula obj) {
		return baseMapper.queryByRoId(obj);
	}

}
