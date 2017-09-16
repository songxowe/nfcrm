package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;

/**
 * 用户管理
 * @author yanxiaosan
 * 2016-09-05 15:25:10
 */

 
public interface BaseInfoService{
	/**
	 * 查询单一信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public BaseInfo query(BaseInfo obj) throws Exception;
	/**
	 * 查询所有条数
	 */
 	public int queryCount(BaseInfo obj) throws Exception;
	/**
	 * 查询所有后台信息
	 */
 	public List<BaseInfo> queryList(BaseInfo obj) throws Exception;
 	
 	/**
	 * 添加信息
	 * @param user
	 * @throws Exception
	 */
	public void add(BaseInfo obj) throws Exception;
	/**
	 * 修改信息
	 * @param user
	 * @throws Exception
	 */
	public void update(BaseInfo obj) throws Exception;
	/**
	 * 查询基本信息通过ID
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public BaseInfo queryById(BaseInfo obj) throws Exception;
	
	/*
	 * 调用用户信息的方法
	 */
	public List queryByUser(String string)throws Exception;
	
	/**
	 * 添加信息
	 * @param user
	 * @throws Exception
	 */
	public void addBatch(List list) throws Exception;
	/**
	 * 删除数据
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void delete(BaseInfo obj) throws Exception;
	BaseInfo queryByUser(BaseInfo obj) throws Exception;
}
