package com.sd.farmework.mapper;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;

public interface LeaveInfoMapper extends BaseMapper {
	/**
	 * 新增请假或者调休信息
	 * 
	 * @param baseinfo
	 */
	void inserttoleave(BaseInfo baseinfo);

}
