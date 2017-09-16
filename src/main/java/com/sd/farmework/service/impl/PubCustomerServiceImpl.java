package com.sd.farmework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.PubCustomerMapper;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;
import com.sd.farmework.service.PubCustomerService;


public class PubCustomerServiceImpl extends BaseInfoServiceImpl implements PubCustomerService{
	
	@Autowired
	private PubCustomerMapper baseMapper;
	
	
	
	
	public PubCustomerMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(PubCustomerMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	
	
	/**
	 * 添加客户
	 * @author Auser醴陵老九
	 */
	@Override
	public void addBatch(CustomerInfo baseMapper) {
		this.baseMapper.addBatch(baseMapper);
	}
	
	/**
	 * 修改客户类型(公客转带看)
	 * @author Auser醴陵老九
	 */
	@Override
	public int modifyPub(String customer_type) {
		
		return baseMapper.modifyPub(customer_type);
	}
	
	/**
	 * 修改客户类型(公客转私)
	 * @author Auser醴陵老九
	 */
	@Override
	public int modifyPub1(String customer_type) {
		// TODO Auto-generated method stub
		return baseMapper.modifyPub1(customer_type);
	}
	
	/**
	 * 修改客户类型(公客转成)
	 * @author Auser醴陵老九
	 */
	@Override
	public int modifyPub2(String customer_type) {
		// TODO Auto-generated method stub
		return baseMapper.modifyPub2(customer_type);
	}


	/**
	 * 修改公客信息
	 * @author Auser醴陵老九
	 */
	@Override
	public int modifyPub3(CustomerInfo customerInfo) {
		
		return baseMapper.modifyPub3(customerInfo);
	}
	
	
	/**
	 * 分页公客查询
	 * @author Auser醴陵老九
	 */
	@Override
	public List<CustomerInfo> queryPubList(CustomerInfo customerInfo) {
		
		return baseMapper.queryPubList(customerInfo);
	}
	
	/**
	 * 根据id查询公客对应的项目
	 * @param customer_id
	 * @author 醴陵老九
	 */
	@Override
	public List queryProNameById(CustomerProJInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryProNameById(obj);
	}
	
	
	/**
	 * 根据id删除
	 */
	@Override
	public void delete(String customer_id) {
		this.baseMapper.delete(customer_id);;
	}
	
	/**
	 * 根据客户id来修改所对应项目的状态
	 * @param pro_id
	 */
	@Override
	public void updateTypeById(String pro_id) {
		// TODO Auto-generated method stub
		this.baseMapper.updateTypeById(pro_id);
	}
	
	/**
	 * 更改当前项目的状态
	 * @param obj
	 */
	@Override
	public void updateCusProInfo(CustomerProJInfo obj) {
		// TODO Auto-generated method stub
		this.baseMapper.updateCusProInfo(obj);
	}
	
	/**
	 * 根据proid和customerid将项目的状态改为Y
	 * @param customInfo
	 */
	@Override
	public void updateStatus(CustomerProJInfo customInfo) {
		// TODO Auto-generated method stub
		this.baseMapper.updateStatus(customInfo);
	}
	
	/**
	 * 添加一个客户所对应的项目
	 * @param cus
	 */
	@Override
	public void addCustomerProName(CustomerProJInfo cus) {
		// TODO Auto-generated method stub
		this.baseMapper.addCustomerProName(cus);
	}
	
	
	/**
	 * 根据多个客户id查询客户信息
	 */
	@Override
	public List queryByManyCustomerId(BaseInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryByManyCustomerId(obj);
	}
	
	/**
	 * 批量修改客户状态
	 * @param baseinfo
	 */
	@Override
	public void upbatch(BaseInfo baseinfo) {
		// TODO Auto-generated method stub
		this.baseMapper.upbatch(baseinfo);
	}
	
	/**
	 * 验证客户
	 */
	@Override
	public CustomerInfo queryCustomerName(CustomerInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryCustomerName(obj);
	}
	
	/**
	 * 修改推荐人数
	 */
	@Override
	public void upRecommendCount(CustomerInfo obj) {
		// TODO Auto-generated method stub
		this.baseMapper.upRecommendCount(obj);
	}
	
	/**
	 * 设置推荐人数为人
	 */
	@Override
	public void upRecommendCount1(CustomerInfo obj) {
		this.baseMapper.upRecommendCount1(obj);
		
	}
	

	/**
	 * 通过客户名查询推荐人数
	 */
	@Override
	public CustomerInfo queryRecommendCount(CustomerInfo obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
