package com.sd.farmework.service;

import com.sd.farmework.pojo.RolesalaryFormula;

public interface RolesalaryFormulaService extends BaseInfoService {

	/**
	 * 根据角色工资id查询工资详情
	 * 
	 * @param rolesalary_id
	 * @return
	 */
	RolesalaryFormula queryBySId(String rolesalary_id);

}
