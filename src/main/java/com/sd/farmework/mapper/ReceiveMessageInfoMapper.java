package com.sd.farmework.mapper;

import java.util.List;

import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.ReceiveMessageInfo;
import com.sd.farmework.pojo.SendMessageInfo;

public interface ReceiveMessageInfoMapper extends BaseMapper{
	void addRecMsg(ReceiveMessageInfo rece);

	List queryCheckDesig(ReceiveMessageInfo obj);

	void changeState(String rid);

	List queryBySendPerson(SendMessageInfo obj);

	int queryCountBySendPerson(SendMessageInfo obj);

	List querySendList(SendMessageInfo obj);
	
	List<SendMessageInfo> queryCountBySendlogin(String obj);
}
