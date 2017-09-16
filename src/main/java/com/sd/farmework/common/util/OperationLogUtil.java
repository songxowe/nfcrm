package com.sd.farmework.common.util;

import com.sd.farmework.pojo.SysLogInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.SysLogInfoService;

public class OperationLogUtil {

	public static void writeLog(SysLogInfoService sysLogInfoService,String context,UserInfo userinfo){
		
		SysLogInfo sysLogInfo = new SysLogInfo();
		sysLogInfo.setOperate_person_id(userinfo.getUser_id());
		sysLogInfo.setOperate_person_name(userinfo.getUser_name());
		sysLogInfo.setFunction_name(context);
		try {
			
			sysLogInfoService.add(sysLogInfo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
