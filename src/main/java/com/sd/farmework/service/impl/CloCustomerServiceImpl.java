package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.CloCustomerMapper;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;
import com.sd.farmework.service.CloCustomerService;

public class CloCustomerServiceImpl extends BaseInfoServiceImpl implements
		CloCustomerService {

	@Autowired
	private CloCustomerMapper baseMapper;

	public CloCustomerMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(CloCustomerMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	
	
	
	

	@Override
	public CustomerInfo findById(String customer_id) {
		return baseMapper.findById(customer_id);
	}

	@Override
	public List<CustomerInfo> queryList(CustomerInfo customer) {
		return baseMapper.queryList(customer);
	}

	@Override
	public int queryCount(CustomerInfo customer) {
		return baseMapper.queryCount(customer);
	}

	@Override
	public List<CustomerProJInfo> queryProNameById(CustomerProJInfo obj) {
		return baseMapper.queryProNameById(obj);
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public void updateTypeById(String pro_id) {
		// TODO Auto-generated method stub
		this.baseMapper.updateTypeById(pro_id);
	}

	@Override
	public void updateCusProInfo(CustomerProJInfo obj) {
		// TODO Auto-generated method stub
		this.baseMapper.updateCusProInfo(obj);
	}

	@Override
	public void delete(String customer_id) {
		// TODO Auto-generated method stub
		this.baseMapper.delete(customer_id);
	}

	@Override
	public void addCustomerProName(CustomerProJInfo cus) {
		// TODO Auto-generated method stub
		this.baseMapper.addCustomerProName(cus);
	}

	@Override
	public void updateStatus(CustomerProJInfo customInfo) {
		// TODO Auto-generated method stub
		this.baseMapper.updateStatus(customInfo);
	}

	@Override
	public List queryByCuid(BaseInfo obj) {
		
		return baseMapper.queryByCuid(obj);
	}


}

