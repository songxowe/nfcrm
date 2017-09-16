package com.sd.farmework.mapper;



import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
/**
 * 权限菜单
 * @author wangchaochao
 * 2016-10-28 15:25:19
 */
public interface PowerMapper extends BaseMapper {
	 
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception;
 }
