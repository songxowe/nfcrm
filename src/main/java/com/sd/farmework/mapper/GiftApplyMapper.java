package com.sd.farmework.mapper;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;

/**
 * 礼物申请管理
 * 
 * @author e5
 * 
 */
public interface GiftApplyMapper extends BaseMapper {
	/**
	 * 礼物申请
	 * 
	 * @param baseinfo
	 */
	void insertall(BaseInfo baseinfo);
}
