package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;


/**
 * 权限菜单
 * @author wangchaochao
 * 2016-10-28 15:25:10
 */

 
public interface PowerService extends BaseInfoService{
	
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception;
}
