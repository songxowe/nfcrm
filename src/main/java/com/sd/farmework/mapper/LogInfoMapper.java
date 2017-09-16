package com.sd.farmework.mapper;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.LogInfo;

public interface LogInfoMapper extends BaseMapper{

	List queryLonInfo(LogInfo obj);

	List queryByProId(String pro_id);

	BaseInfo queryTime(LogInfo loginfo);
	
	List queryRFtime(LogInfo loginfo);

}
