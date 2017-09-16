package com.sd.farmework.mapper;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.ProjectInfo;

/**
 * 用户管理
 * 
 * @author 秦波 2016-10-28 15:25:19
 */
public interface ProjectInfo1Mapper extends BaseMapper {
	List<BaseInfo> updatedquery(BaseInfo baseinfo);
	void addpro(BaseInfo baseinfo);
	void updatepro(BaseInfo baseinfo);
	void updatestatus(BaseInfo baseinfo);
	List queryPro(ProjectInfo obj);
	List<BaseInfo> querytype(BaseInfo baseinfo);
	String queryByOneString(BaseInfo baseinfo);
	
}
