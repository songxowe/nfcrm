package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.ProjectInfo;

public interface CustomerListService extends BaseInfoService {
	// 插入礼品申请记录
	public void insertall(BaseInfo baseInfo) throws Exception;

	// 新增信息报备
	public void addReport(BaseInfo baseInfo) throws Exception;

	// 根据客户编号查询客户信息
	public List queryCustomerById(String baseInfo) throws Exception;

	// 根据客户编号查询对应项目信息
	public List<CustomerProJInfo> queryCustomerProj(String baseInfo)
			throws Exception;

	// 根据项目编号查询对应的跟进日志
	public List queryByLogId(String baseInfo) throws Exception;

	// 新增跟进日志
	public void addLog(BaseInfo baseInfo) throws Exception;

	// 新增客户对应项目至中间表
	public void addCusPro(BaseInfo baseInfo) throws Exception;

	// 修改客户信息
	public void modifyPub3(BaseInfo baseInfo) throws Exception;

	// 查询客户是否存在
	public BaseInfo CusisExeit(BaseInfo baseInfo) throws Exception;

	// 根据礼品主键ID查看审核详情
	public BaseInfo queryAll(BaseInfo baseInfo) throws Exception;

	// 查询所有礼品申请
	public List<BaseInfo> queryList1(BaseInfo baseInfo) throws Exception;

	// 统计
	public int queryCount1(BaseInfo baseInfo) throws Exception;

	// 统计
	public int queryCount2(BaseInfo baseInfo) throws Exception;

	public List<BaseInfo> queryList2(BaseInfo baseInfo) throws Exception;

	public void upbatch(BaseInfo baseInfo);

	public List queryByManyCustomerId(BaseInfo baseInfo) throws Exception;

	public CustomerInfo queryById1(CustomerInfo obj) throws Exception;

	public List<CustomerInfo> queryByCuid(CustomerInfo customer)
			throws Exception;

	public String queryByOneString(CustomerInfo customerInfo) throws Exception;

	public String queryByOneString2(EmployeeInfo employee) throws Exception;

	public ProjectInfo queryById2(ProjectInfo obj) throws Exception;

	public String queryByOneString1(ProjectInfo customerInfo) throws Exception;

	public List<CustomerInfo> querynopre() throws Exception;

	public CustomerInfo queryType(String obj) throws Exception;

	public void updategift(CustomerInfo obj) throws Exception;

}
