package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.SysApprovenTask;
import com.sd.farmework.pojo.TestApprovenFunciton;
import com.sd.farmework.pojo.UserInfo;

/**
 * 审批管理
 * 
 * @author 虎类子	 2016-10-28 15:25:10
 */

public interface SysApprovenTaskService extends BaseInfoService {
	void added(BaseInfo ojb);

	void updateNext(BaseInfo ojb);

	List<BaseInfo> queryAll(SysApprovenTask obj);

}
