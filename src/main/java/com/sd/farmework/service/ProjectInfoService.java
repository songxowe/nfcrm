package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.ProjectInfo;


public interface ProjectInfoService extends BaseInfoService{
	List<BaseInfo> updatedquery(BaseInfo baseinfo);
	void addpro(BaseInfo baseinfo);
	void updatepro(BaseInfo baseinfo);
	void updatestatus(BaseInfo baseinfo);
	List queryPro(ProjectInfo obj);
	List<BaseInfo> querytype(BaseInfo baseinfo);
	String queryByOneString(BaseInfo baseinfo);
}
