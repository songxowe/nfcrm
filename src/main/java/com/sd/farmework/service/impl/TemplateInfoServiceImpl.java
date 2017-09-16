package com.sd.farmework.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.mapper.TemplateInfoMapper;
import com.sd.farmework.pojo.TemplateContentInfo;
import com.sd.farmework.pojo.TemplateInfo;
import com.sd.farmework.service.TemplateInfoService;

public class TemplateInfoServiceImpl extends BaseInfoServiceImpl implements TemplateInfoService{

	@Autowired
	private TemplateInfoMapper baseMapper;

	public TemplateInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(TemplateInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public String queryTempId(TemplateInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryTempId(obj);
	}

	@Override
	public void addTempContent(TemplateContentInfo obj1) {
		// TODO Auto-generated method stub
		baseMapper.addTempContent(obj1);
	}

	@Override
	public void updateByTempId(TemplateContentInfo obj1) {
		// TODO Auto-generated method stub
		baseMapper.updateByTempId(obj1);
	}
	
	@Override
	public void addTempRooback(TemplateInfo obj, String  str, String  str2,String keytId, String valtId) {
		// TODO Auto-generated method stub
		try {
			baseMapper.add(obj);
			String[] keystr=str.split("\\|");
			String[] valstr=str2.split("\\|");
			String[] keytid=keytId.split("\\|");
			String[] valtid=valtId.split("\\|");
			List<String> keyList = new ArrayList<String>();
			List<String> valList = new ArrayList<String>();
			List<String> keytidList = new ArrayList<String>();
			List<String> valtidList = new ArrayList<String>();
			for (int i = 0; i < keystr.length; i++) {
				if(keystr[i].equals("-")){
					keystr[i] ="";
				}else if(keystr[i].indexOf("-")!=-1){
					keystr[i] = keystr[i].substring(1);
				}
				keyList.add(i, keystr[i]);
			}
			for (int i = 0; i < valstr.length; i++) {
				if(valstr[i].equals("-")){
					valstr[i] ="";
				}else if(valstr[i].indexOf("-")!=-1){
					valstr[i]=valstr[i].substring(1);
				}
				valList.add(i, valstr[i]);
			}
			for (int i = 0; i < keytid.length; i++) {
				if(keytid[i].equals("-")){
					keytid[i] ="";
				}else if(keytid[i].indexOf("-")!=-1){
					keytid[i]=keytid[i].substring(1);
				}
				keytidList.add(i, keytid[i]);
			}
			for (int i = 0; i < valtid.length; i++) {
				if(valtid[i].equals("-")){
					valtid[i] ="";
				}else if(valtid[i].indexOf("-")!=-1){
					valtid[i]=valtid[i].substring(1);
				}
				valtidList.add(i, valtid[i]);
			}
			if(keystr.length > valstr.length){
				
				int count = keystr.length - valstr.length;
				for (int i = 1; i <= count; i++) {
					valList.add(valList.size(),"");
					valtidList.add(valtidList.size(),"");
				}
			}else{
				int count = valstr.length - keystr.length;
				for (int i = 1; i <= count; i++) {
					keyList.add(keyList.size(),"");
					keytidList.add(keytidList.size(),"");
				}
			}
			
			obj.setT_id(StringUtil.prefixString(obj.getT_id(), 11));
			for(int i=0;i<keyList.size();i++){
				TemplateContentInfo temp=new TemplateContentInfo();
				temp.setKey_name(keyList.get(i));
				temp.setValue(valList.get(i));
				temp.setT_id(obj.getT_id());
				temp.setKey_source_id(keytidList.get(i));
				temp.setValue_source_id(valtidList.get(i));
				temp.setTemplate_name(obj.getTemplate_name());
				temp.setCreateUserId(obj.getCreateUserId());
				baseMapper.addTempContent(temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<TemplateContentInfo> queryMulti(TemplateContentInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryMulti(obj);
	}

	@Override
	public void updateTempContent(TemplateContentInfo temp) {
		// TODO Auto-generated method stub
		baseMapper.updateTempContent(temp);
	}

	@Override
	public void deleteTempContent(TemplateContentInfo temp) {
		// TODO Auto-generated method stub
		baseMapper.deleteTempContent(temp);
	}

	@Override
	public List queryContentById(TemplateContentInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryContentById(obj);
	}

	@Override
	public List<BaseInfo> queryALL(BaseInfo obj) throws Exception {
		return this.baseMapper.queryALL(obj);
	}

	
}
