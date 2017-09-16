package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.GiftApplyMapper;
import com.sd.farmework.service.GiftApplyService;

public class GiftApplyServiceImpl extends BaseInfoServiceImpl implements
		GiftApplyService {

	@Autowired
	private GiftApplyMapper baseMapper;

	public GiftApplyMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(GiftApplyMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	/**
	 * 礼物申请
	 */
	@Override
	public void insertall(BaseInfo baseinfo) {
		baseMapper.insertall(baseinfo);
	}

}
