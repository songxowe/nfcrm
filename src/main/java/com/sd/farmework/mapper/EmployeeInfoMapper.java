package com.sd.farmework.mapper;

import java.util.List;

import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.EmployeeInfo;

public interface EmployeeInfoMapper extends BaseMapper{

	List queryEmployeeInfo(EmployeeInfo obj);

	void changeTryOut(EmployeeInfo emp);

	void changeForMal(EmployeeInfo emp);

	void changeLeav(EmployeeInfo emp);
	
	String queryByOneString(EmployeeInfo emp);
	
}
