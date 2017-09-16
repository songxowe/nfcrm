package com.sd.farmework.mapper;

import java.util.List;
import java.util.Map;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.TemplateContentInfo;
import com.sd.farmework.pojo.TemplateInfo;

public interface TemplateInfoMapper extends BaseMapper{

	String queryTempId(TemplateInfo obj);

	void addTempContent(TemplateContentInfo obj1);

	void updateByTempId(TemplateContentInfo obj1);

	List<TemplateContentInfo> queryMulti(TemplateContentInfo obj);

	void updateTempContent(TemplateContentInfo temp);

	void deleteTempContent(TemplateContentInfo temp);

	List queryContentById(TemplateContentInfo obj);

	public List<BaseInfo> queryALL(BaseInfo obj) throws Exception;
	
}
