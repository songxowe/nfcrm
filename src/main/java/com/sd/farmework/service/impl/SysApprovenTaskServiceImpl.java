package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.SysApprovenTaskMapper;
import com.sd.farmework.pojo.SysApprovenTask;
import com.sd.farmework.service.SysApprovenTaskService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class SysApprovenTaskServiceImpl   extends BaseInfoServiceImpl  implements SysApprovenTaskService {

	private static Logger logger = Logger.getLogger(SysApprovenTaskServiceImpl.class);

	@Autowired
	private SysApprovenTaskMapper baseMapper;

	public SysApprovenTaskMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SysApprovenTaskMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public void added(BaseInfo ojb) {
		// TODO Auto-generated method stub
		baseMapper.added(ojb);
	}

	@Override
	public void updateNext(BaseInfo ojb) {
		// TODO Auto-generated method stub
		baseMapper.updateNext(ojb);
	}

	@Override
	public List<BaseInfo> queryAll(SysApprovenTask obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryAll(obj);
	}

	 
}
