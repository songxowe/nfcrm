package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.pojo.EmployeeSalary;
import com.sd.farmework.pojo.RolesalaryFormula;

/**
 * 工资计算
 * 
 * @author qushuai
 * 
 */
public interface SalaryService extends BaseInfoService {
	/**
	 * 根据员工编号查询员工工资详情
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List queryByUserNo(EmployeeSalary obj);

	/**
	 * 根据角色id查询角色工资详情
	 * 
	 * @param obj
	 * @return
	 */
	RolesalaryFormula queryByRoId(RolesalaryFormula obj);
}
