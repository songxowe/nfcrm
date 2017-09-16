package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.pojo.EmployeeInfo;

public interface EmployeeInfoService extends BaseInfoService{

	List queryEmployeeInfo(EmployeeInfo obj);

	void changeTryOut(EmployeeInfo emp);

	void changeForMal(EmployeeInfo emp);

	void changeLeav(EmployeeInfo emp);
	String queryByOneString(EmployeeInfo emp);
}
