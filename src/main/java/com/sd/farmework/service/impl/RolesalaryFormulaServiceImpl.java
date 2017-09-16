package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.RolesalaryFormulaMapper;
import com.sd.farmework.pojo.RolesalaryFormula;
import com.sd.farmework.service.RolesalaryFormulaService;

public class RolesalaryFormulaServiceImpl extends BaseInfoServiceImpl implements
		RolesalaryFormulaService {

	@Autowired
	private RolesalaryFormulaMapper baseMapper;

	public RolesalaryFormulaMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(RolesalaryFormulaMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public RolesalaryFormula queryBySId(String rolesalary_id) {
		return baseMapper.queryBySId(rolesalary_id);
	}

}
