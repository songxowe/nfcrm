package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.ProImgMapper;
import com.sd.farmework.pojo.ProjectImg;
import com.sd.farmework.service.ProImgService;

public class ProImgServiceImpl extends BaseInfoServiceImpl implements ProImgService{

	@Autowired
	private ProImgMapper baseMapper;

	public ProImgMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(ProImgMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public void deletebyimgid(ProjectImg proimg) {
		this.baseMapper.deletebyimgid(proimg);
	}
}
