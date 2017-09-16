package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.DateDictionaryInfo;

public interface DateDictionaryInfoService extends BaseInfoService{

	List queryType(DateDictionaryInfo obj);

	void updateNum(DateDictionaryInfo obj);

	BaseInfo querycountdown(DateDictionaryInfo datadictionary);
	
	BaseInfo queryloginfo(DateDictionaryInfo datadictionary);
	
	List queryByModelInfo(DateDictionaryInfo obj);
}
