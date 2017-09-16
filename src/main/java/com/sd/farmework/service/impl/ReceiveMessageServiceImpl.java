package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.ReceiveMessageInfoMapper;
import com.sd.farmework.pojo.ReceiveMessageInfo;
import com.sd.farmework.pojo.SendMessageInfo;
import com.sd.farmework.service.ReceiveMessageService;

public class ReceiveMessageServiceImpl extends BaseInfoServiceImpl implements ReceiveMessageService{

	@Autowired
	private ReceiveMessageInfoMapper baseMapper;

	public ReceiveMessageInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(ReceiveMessageInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public void addRecMsg(ReceiveMessageInfo rece) {
		// TODO Auto-generated method stub
		baseMapper.addRecMsg(rece);
	}

	@Override
	public List queryCheckDesig(ReceiveMessageInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryCheckDesig(obj);
	}

	@Override
	public void changeState(String rid) {
		// TODO Auto-generated method stub
		baseMapper.changeState(rid);
	}

	@Override
	public List queryBySendPerson(SendMessageInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryBySendPerson(obj);
	}

	@Override
	public int queryCountBySendPerson(SendMessageInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryCountBySendPerson(obj);
	}

	@Override
	public List querySendList(SendMessageInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.querySendList(obj);
	}

	@Override
	public List<SendMessageInfo> queryCountBySendlogin(String obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryCountBySendlogin(obj);
	}

	
	
}
