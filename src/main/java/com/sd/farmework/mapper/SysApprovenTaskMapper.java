package com.sd.farmework.mapper;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.SysApprovenTask;

public interface SysApprovenTaskMapper extends BaseMapper{
   void added(BaseInfo ojb);
   void updateNext(BaseInfo ojb);
   List<BaseInfo> queryAll(SysApprovenTask obj);
}
