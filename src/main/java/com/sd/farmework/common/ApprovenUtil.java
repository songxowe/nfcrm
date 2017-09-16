package com.sd.farmework.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.pojo.SysApprovenFunctionRule;
import com.sd.farmework.pojo.SysApprovenRulePerson;
import com.sd.farmework.pojo.SysApprovenTask;
import com.sd.farmework.service.SysApprovenFunctionRuleService;
import com.sd.farmework.service.SysApprovenRulePersonService;
import com.sd.farmework.service.SysApprovenTaskService;

public class ApprovenUtil {
	/**
	 * 添加审批任务
	 * 
	 * @param approvenRuleId
	 * @param sysTaskP
	 * @param sysApprovenTaskService
	 * @param ruleServices
	 */
	public void addApprovenTask(String approvenRuleId,
			SysApprovenTask sysTaskP,
			SysApprovenTaskService sysApprovenTaskService,
			SysApprovenRulePersonService ruleServices,
			SysApprovenFunctionRuleService sysApprovenFunctionRuleService) {
		try {
			SysApprovenRulePerson rulePerson = new SysApprovenRulePerson();
			// 通过sys_approven_function_rule 表获取编号
			rulePerson.setApprovenRuleId(approvenRuleId);
			SysApprovenFunctionRule sysApprovenFunctionRule = (SysApprovenFunctionRule) sysApprovenFunctionRuleService
					.query(rulePerson);

			List list = ruleServices.queryList(rulePerson);
			for (int i = 0; i < list.size(); i++) {
				SysApprovenRulePerson personObj = (SysApprovenRulePerson) list
						.get(i);

				SysApprovenTask sysTask = new SysApprovenTask();

				sysTask.setTaskName(sysTaskP.getTaskName());
				sysTask.setApprovenResult(sysTaskP.getApprovenResult());
				sysTask.setApprovenUserId(personObj.getApprovenUserId());
				sysTask.setApprovenUserName(personObj.getApprovenUserName());
				sysTask.setApprovenSendTime(DateUtil.getCurrentDateString());
				sysTask.setApprovenPersonId(personObj.getApprovenPersonId()
						.toString());
				sysTask.setApprovenRuleId(personObj.getApprovenRuleId());
				sysTask.setApprovenRuleName(personObj.getApprovenRuleName());
				sysTask.setApprovenFunctionId(personObj.getApprovenFunctionId());
				sysTask.setApprovenFunctionName(personObj
						.getApprovenFunctionName());
				sysTask.setSourceTable(sysTaskP.getSourceTable());
				sysTask.setSourceTablePkColumnName(sysTaskP
						.getSourceTablePkColumnName());
				sysTask.setSourceTablePkColumnValue(sysTaskP
						.getSourceTablePkColumnValue());

				if ("2".equals(sysApprovenFunctionRule.getRuleIsOrderly())) {
					sysTask.setRuleOrder("1");
				} else {
					sysTask.setRuleOrder(personObj.getRuleOrder());
				}
				sysTask.setCreateUserId(sysTaskP.getCreateUserId());
				sysTask.setCreateUserName(sysTaskP.getCreateUserName());
				sysApprovenTaskService.add(sysTask);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 处理审批
	 * 
	 * @param obj
	 * @param sysApprovenTaskService
	 */
	public Map<String, Object> doApproven(SysApprovenTask obj,
			SysApprovenTaskService sysApprovenTaskService, String approvenRuleId) {
		String sourceId = "";
		try {
			// 将数据移入已办任务表中
			obj.setApprovenEndTime(DateUtil.getCurrentDateString());
			sysApprovenTaskService.added(obj);
			// 设置下个审批人信息
			SysApprovenTask objP = (SysApprovenTask) sysApprovenTaskService
					.query(obj);
			sourceId = objP.getSourceTablePkColumnValue();
			objP.setRuleOrderOld(""
					+ (Integer.valueOf(objP.getRuleOrder()) + 1));
			System.out.println("审批人的原始序号" + objP.getRuleOrderOld());
			objP.setApprovenSendTime(DateUtil.getCurrentDateString());
			sysApprovenTaskService.updateNext(objP);
			// 删除代办任务表信息
			sysApprovenTaskService.delete(obj);

			SysApprovenTask sysApprovenTask = new SysApprovenTask();
			sysApprovenTask.setApprovenRuleId(approvenRuleId);
			sysApprovenTask.setSourceTablePkColumnValue(sourceId);
			List<BaseInfo> sysApprovenTaskList = sysApprovenTaskService
					.queryList(sysApprovenTask);

			Map<String, Object> map = new HashMap();
			map.put("sourceId", sourceId);
			map.put("sysApprovenTaskList", sysApprovenTaskList);
			return map;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
