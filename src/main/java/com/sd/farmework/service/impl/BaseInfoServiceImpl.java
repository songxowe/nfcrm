package com.sd.farmework.service.impl;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.mapper.AttendInfoMapper;
import com.sd.farmework.mapper.CustomerListMapper;
import com.sd.farmework.mapper.DateDictionaryInfoMapper;
import com.sd.farmework.mapper.FunctionInfoPowerMapper;
import com.sd.farmework.mapper.GiftApplyMapper;
import com.sd.farmework.mapper.GiftMapper;
import com.sd.farmework.mapper.LogInfoMapper;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.ProConfigMapper;
import com.sd.farmework.mapper.ProImgMapper;
import com.sd.farmework.mapper.ProjectInfoMapper;
import com.sd.farmework.mapper.ReceiveMessageInfoMapper;
import com.sd.farmework.mapper.RoleMapper;
import com.sd.farmework.mapper.SmSInfoMapper;
import com.sd.farmework.mapper.SysApprovenFunctionMapper;
import com.sd.farmework.mapper.SysApprovenFunctionRuleMapper;
import com.sd.farmework.mapper.SysApprovenRulePersonMapper;
import com.sd.farmework.mapper.SysApprovenTaskMapper;
import com.sd.farmework.mapper.SysApprovenTaskedMapper;
import com.sd.farmework.mapper.SysLogInfoMapper;
import com.sd.farmework.mapper.TeamOrganizationMapper;
import com.sd.farmework.mapper.TemplateInfoMapper;
import com.sd.farmework.mapper.TestApprovenFuncitonMapper;
import com.sd.farmework.mapper.UserAdminMapper;
import com.sd.farmework.mapper.UserAndRolesMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.mapper.UserPermissionMapper;
import com.sd.farmework.service.BaseInfoService;

public class BaseInfoServiceImpl implements BaseInfoService {

	protected BaseMapper baseMapper;

	public BaseMapper getBaseMapper() {

		return baseMapper;
	}

	public void setBaseMapper(BaseMapper baseMapper) {
		if (baseMapper instanceof UserInfoMapper) {
			this.baseMapper = (UserInfoMapper) baseMapper;
		}
		if (baseMapper instanceof PowerMapper) {
			this.baseMapper = (PowerMapper) baseMapper;
		}
		if (baseMapper instanceof RoleMapper) {
			this.baseMapper = (RoleMapper) baseMapper;
		}
		if (baseMapper instanceof UserAdminMapper) {
			this.baseMapper = (UserAdminMapper) baseMapper;
		}
		if (baseMapper instanceof AttendInfoMapper) {
			this.baseMapper = (AttendInfoMapper) baseMapper;
		}

		if (baseMapper instanceof TemplateInfoMapper) {
			this.baseMapper = (TemplateInfoMapper) baseMapper;
		}

		if (baseMapper instanceof DateDictionaryInfoMapper) {
			this.baseMapper = (DateDictionaryInfoMapper) baseMapper;
		}

		if (baseMapper instanceof LogInfoMapper) {
			this.baseMapper = (LogInfoMapper) baseMapper;
		}
		if (baseMapper instanceof UserAndRolesMapper) {
			this.baseMapper = (UserAndRolesMapper) baseMapper;
		}
		if (baseMapper instanceof UserPermissionMapper) {
			this.baseMapper = (UserPermissionMapper) baseMapper;
		}

		if (baseMapper instanceof TestApprovenFuncitonMapper) {
			this.baseMapper = (TestApprovenFuncitonMapper) baseMapper;
		}

		if (baseMapper instanceof SysApprovenRulePersonMapper) {
			this.baseMapper = (SysApprovenRulePersonMapper) baseMapper;
		}

		if (baseMapper instanceof TeamOrganizationMapper) {
			this.baseMapper = (TeamOrganizationMapper) baseMapper;
		}
		if (baseMapper instanceof SysApprovenFunctionMapper) {
			this.baseMapper = (SysApprovenFunctionMapper) baseMapper;
		}
		if (baseMapper instanceof SysApprovenFunctionRuleMapper) {
			this.baseMapper = (SysApprovenFunctionRuleMapper) baseMapper;
		}
		// libo
		if (baseMapper instanceof SysApprovenTaskMapper) {
			this.baseMapper = (SysApprovenTaskMapper) baseMapper;
		}
		// libo
		if (baseMapper instanceof SysApprovenTaskedMapper) {
			this.baseMapper = (SysApprovenTaskedMapper) baseMapper;
		}
		if (baseMapper instanceof FunctionInfoPowerMapper) {
			this.baseMapper = (FunctionInfoPowerMapper) baseMapper;
		}
		if (baseMapper instanceof SysLogInfoMapper) {

			this.baseMapper = (SysLogInfoMapper) baseMapper;
		}
		// 戴俊鹏
		if (baseMapper instanceof ReceiveMessageInfoMapper) {
			this.baseMapper = (ReceiveMessageInfoMapper) baseMapper;
		}
		if (baseMapper instanceof SmSInfoMapper) {
			this.baseMapper = (SmSInfoMapper) baseMapper;
		}
		// 戴俊鹏
		if (baseMapper instanceof CustomerListMapper) {
			this.baseMapper = (CustomerListMapper) baseMapper;
		}
		if (baseMapper instanceof GiftMapper) {
			this.baseMapper = (GiftMapper) baseMapper;
		}
		if (baseMapper instanceof ProjectInfoMapper) {
			this.baseMapper = (ProjectInfoMapper) baseMapper;
		}
		if (baseMapper instanceof ProConfigMapper) {
			this.baseMapper = (ProConfigMapper) baseMapper;
		}
		if (baseMapper instanceof ProImgMapper) {
			this.baseMapper = (ProImgMapper) baseMapper;
		}

		if (baseMapper instanceof GiftApplyMapper) {
			this.baseMapper = (GiftApplyMapper) baseMapper;
		}

	}

	/**
	 * 查询所有用户
	 */

	public List<BaseInfo> queryList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		List list = null;
		try {
			list = getBaseMapper().queryList(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}

		return list;
	}

	public void add(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			getBaseMapper().add(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void update(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			getBaseMapper().update(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	public BaseInfo queryById(BaseInfo obj) throws Exception {
		BaseInfo objout = null;
		try {
			objout = getBaseMapper().queryById(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new Exception(e);
		}
		return objout;
	}

	public BaseInfo query(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		BaseInfo objout = null;
		try {
			objout = getBaseMapper().query(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
		return objout;
	}

	public int queryCount(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub

		int objout = 0;
		try {
			objout = getBaseMapper().queryCount(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
		return objout;
	}

	@Override
	public void addBatch(List list) throws Exception {
		// TODO Auto-generated method stub
		try {
			getBaseMapper().addBatch(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			getBaseMapper().delete(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public BaseInfo queryByUser(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List queryByUser(String string) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
