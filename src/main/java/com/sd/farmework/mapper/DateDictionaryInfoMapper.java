package com.sd.farmework.mapper;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.DateDictionaryInfo;

public interface DateDictionaryInfoMapper extends BaseMapper{

	List queryType(DateDictionaryInfo obj);

	void updateNum(DateDictionaryInfo obj);
	
	BaseInfo querycountdown(DateDictionaryInfo datadictionary);
	
	BaseInfo queryloginfo(DateDictionaryInfo datadictionary);
	
	List queryByModelInfo(DateDictionaryInfo obj);

	
 }
