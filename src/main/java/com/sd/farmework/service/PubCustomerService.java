package com.sd.farmework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;

public interface PubCustomerService extends BaseInfoService {
	/**
	 * 分页公客查询
	 * @author Auser醴陵老九
	 */
	List<CustomerInfo> queryPubList(CustomerInfo customerInfo);
	
	/**
	 * 添加客户
	 * @author Auser醴陵老九
	 */
	void addBatch(CustomerInfo customerInfo);
	
	/**
	 * 修改客户类型(公客/带看/私客/成交)
	 * @author Auser醴陵老九
	 */
	int  modifyPub(String customer_type);
	
	/**
	 * 修改客户类型(公客/带看/私客/成交)
	 * @author Auser醴陵老九
	 */
	int  modifyPub1(String customer_type);
	
	/**
	 * 修改客户类型(公客/带看/私客/成交)
	 * @author Auser醴陵老九
	 */
	int  modifyPub2(String customer_type);
	
	/**
	 * 根据id查询公客对应的项目
	 * @param customer_id
	 * @author 醴陵老九
	 */
	List queryProNameById(CustomerProJInfo obj);
	
	/**
	 * 修改公客信息
	 * @author Auser醴陵老九
	 */
	int modifyPub3(CustomerInfo customerInfo);
	
	/**
	 * 根据id删除
	 */
	void delete(String customer_id);
	
	/**
	 * 根据客户id来修改所对应项目的状态
	 * @param pro_id
	 */
	void updateTypeById(String pro_id);
	
	/**
	 * 更改当前项目的状态
	 * @param obj
	 */
	void updateCusProInfo(CustomerProJInfo obj);
	
	/**
	 * 根据proid和customerid将项目的状态改为Y
	 * @param customInfo
	 */
	void updateStatus(CustomerProJInfo customInfo);
	
	/**
	 * 添加一个客户所对应的项目
	 * @param cus
	 */
	void addCustomerProName(CustomerProJInfo cus);
	
	/**
	 * 根据多个客户id查询客户信息
	 */
	List queryByManyCustomerId(BaseInfo obj);
	
	/**
	 * 批量修改客户状态
	 * @param baseinfo
	 */
	void upbatch(BaseInfo baseinfo);
	
	/**
	 * 验证客户
	 * @param obj
	 * @return
	 */
	CustomerInfo queryCustomerName(CustomerInfo obj);
	
	/**
	 * 修改推荐人数
	 */
	void upRecommendCount(CustomerInfo obj);
	
	/**
	 * 设置推荐人数为0
	 */
	void upRecommendCount1(CustomerInfo obj);
	
	/**
	 * 通过客户名查询推荐人数
	 */
	CustomerInfo queryRecommendCount(CustomerInfo obj);
}
