package com.sd.farmework.service;

import java.util.List;
import java.util.Map;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.TemplateContentInfo;
import com.sd.farmework.pojo.TemplateInfo;

public interface TemplateInfoService extends BaseInfoService{

	String queryTempId(TemplateInfo obj);

	void addTempContent(TemplateContentInfo obj1);

	void updateByTempId(TemplateContentInfo obj1);

	void addTempRooback(TemplateInfo obj,String  str,String  str2, String keytId, String valtId);

	List<TemplateContentInfo> queryMulti(TemplateContentInfo obj);

	void updateTempContent(TemplateContentInfo temp);

	void deleteTempContent(TemplateContentInfo temp);

	List queryContentById(TemplateContentInfo obj);
	
	public List<BaseInfo> queryALL(BaseInfo obj) throws Exception;

}
