package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;

/**
 * 
 * @author LIAO
 * 
 */
public interface CloCustomerService extends BaseInfoService {

	/**
	 * 根据ID查询成交客户
	 */
	CustomerInfo findById(String customer_id);

	/**
	 * 分页查询
	 */
	public List<CustomerInfo> queryList(CustomerInfo customer);

	/**
	 * 查询所有条数
	 */
	public int queryCount(CustomerInfo customer);

	/**
	 * 通过ID查询项目名称
	 */
	List<CustomerProJInfo> queryProNameById(CustomerProJInfo obj);
	
	
	
	
	void updateTypeById(String pro_id);
	
	void updateCusProInfo(CustomerProJInfo obj);
	
	void delete(String customer_id);
	
	void addCustomerProName(CustomerProJInfo cus);
	
	void updateStatus(CustomerProJInfo customInfo);
	
	List queryByCuid(BaseInfo obj);
}

