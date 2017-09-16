package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.LogInfo;


public interface LogInfoService extends BaseInfoService{

	List queryLonInfo(LogInfo obj);

	List queryByProId(String pro_id);

	BaseInfo queryTime(LogInfo loginfo);

	List queryRFtime(LogInfo loginfo);

}
