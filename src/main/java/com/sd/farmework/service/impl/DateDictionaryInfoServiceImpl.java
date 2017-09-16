package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.DateDictionaryInfoMapper;
import com.sd.farmework.pojo.DateDictionaryInfo;
import com.sd.farmework.service.DateDictionaryInfoService;

public class DateDictionaryInfoServiceImpl extends BaseInfoServiceImpl implements DateDictionaryInfoService{
	@Autowired
	private DateDictionaryInfoMapper baseMapper;

	public DateDictionaryInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(DateDictionaryInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public List queryType(DateDictionaryInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryType(obj);
	}

	@Override
	public void updateNum(DateDictionaryInfo obj) {
		// TODO Auto-generated method stub
		baseMapper.updateNum(obj);
	}

	@Override
	public BaseInfo querycountdown(DateDictionaryInfo datadictionary) {
		// TODO Auto-generated method stub
		return baseMapper.querycountdown(datadictionary);
	}

	@Override
	public List queryByModelInfo(DateDictionaryInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryByModelInfo(obj);
	}

	@Override
	public BaseInfo queryloginfo(DateDictionaryInfo datadictionary) {
		// TODO Auto-generated method stub
		return baseMapper.queryloginfo(datadictionary);
	}

}
