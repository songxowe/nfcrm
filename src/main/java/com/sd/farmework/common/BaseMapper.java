package com.sd.farmework.common;

import java.util.List;

public interface BaseMapper {
	
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
	 * 批量添加信息
	 * @param user
	 * @throws Exception
	 */
	public void addBatch(List list) throws Exception;
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

	/**
	 * 删除数据
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void delete(BaseInfo obj) throws Exception;
}
