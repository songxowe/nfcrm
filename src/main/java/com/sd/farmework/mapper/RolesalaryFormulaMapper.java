package com.sd.farmework.mapper;

import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.RolesalaryFormula;

public interface RolesalaryFormulaMapper extends BaseMapper {

	/**
	 * 根据角色工资id查询工资详情
	 * 
	 * @param rolesalary_id
	 * @return
	 */
	RolesalaryFormula queryBySId(String rolesalary_id);

}
