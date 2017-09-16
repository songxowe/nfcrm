package com.sd.farmework.service;

import com.sd.farmework.common.BaseInfo;

/**
 * 礼物申请管理
 * 
 * @author e5
 * 
 */
public interface GiftApplyService extends BaseInfoService {
	/**
	 * 礼物申请
	 * 
	 * @param baseinfo
	 */
	void insertall(BaseInfo baseinfo);
}
