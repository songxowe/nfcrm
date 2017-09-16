package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.CustomerListMapper;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.service.CustomerListService;

public class CustomerListServiceImpl extends BaseInfoServiceImpl implements
		CustomerListService {
	@Autowired
	private CustomerListMapper customer;

	public CustomerListMapper getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerListMapper customer) {
		this.customer = customer;
	}

	@Override
	public void insertall(BaseInfo baseInfo) throws Exception {
		customer.insertall(baseInfo);
	}

	@Override
	public void addReport(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		customer.addReport(baseInfo);
	}

	@Override
	public List<CustomerProJInfo> queryCustomerProj(String baseInfo)
			throws Exception {
		// TODO Auto-generated method stub
		return customer.queryCustomerProj(baseInfo);
	}

	@Override
	public List queryByLogId(String baseInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryByLogId(baseInfo);
	}

	@Override
	public void addLog(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		customer.addLog(baseInfo);
	}

	@Override
	public void addCusPro(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		customer.addCusPro(baseInfo);
	}

	@Override
	public void modifyPub3(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		customer.modifyPub3(baseInfo);
	}

	@Override
	public BaseInfo CusisExeit(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.CusisExeit(baseInfo);
	}

	@Override
	public BaseInfo queryAll(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryAll(baseInfo);
	}

	@Override
	public List<BaseInfo> queryList1(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryList1(baseInfo);
	}

	@Override
	public int queryCount1(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryCount1(baseInfo);
	}

	@Override
	public int queryCount2(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryCount2(baseInfo);
	}

	@Override
	public List<BaseInfo> queryList2(BaseInfo baseInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryList2(baseInfo);
	}

	@Override
	public void upbatch(BaseInfo baseInfo) {
		customer.upbatch(baseInfo);
	}

	@Override
	public List queryByManyCustomerId(BaseInfo baseInfo) throws Exception {
		return customer.queryByManyCustomerId(baseInfo);
	}

	@Override
	public List queryCustomerById(String baseInfo) throws Exception {
		return customer.queryCustomerById(baseInfo);
	}

	@Override
	public CustomerInfo queryById1(CustomerInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryById1(obj);
	}

	@Override
	public List<CustomerInfo> queryByCuid(CustomerInfo customer1)
			throws Exception {
		return customer.queryByCuid(customer1);
	}

	@Override
	public String queryByOneString(CustomerInfo customerInfo) throws Exception {
		return customer.queryByOneString(customerInfo);
	}

	@Override
	public String queryByOneString2(EmployeeInfo employee) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryByOneString2(employee);
	}

	@Override
	public ProjectInfo queryById2(ProjectInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryById2(obj);
	}

	@Override
	public String queryByOneString1(ProjectInfo customerInfo) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryByOneString1(customerInfo);
	}

	@Override
	public List<CustomerInfo> querynopre() throws Exception {
		// TODO Auto-generated method stub
		return customer.querynopre();
	}

	@Override
	public CustomerInfo queryType(String obj) throws Exception {
		// TODO Auto-generated method stub
		return customer.queryType(obj);
	}

	@Override
	public void updategift(CustomerInfo obj) throws Exception {
		customer.updategift(obj);
		
	}

}
