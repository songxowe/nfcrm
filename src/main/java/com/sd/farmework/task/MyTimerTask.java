package com.sd.farmework.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.pojo.AttendDetailInfo;
import com.sd.farmework.pojo.AttendInfo;
import com.sd.farmework.service.AttendInfoService;


@Controller
public class MyTimerTask {
	
		@Autowired
		AttendInfoService attendInfoService;
		
	
	public void lookSign(){
		System.out.println("------------1-----------------");
		AttendInfo attendInfo = new AttendInfo();
		//查询主表中的所有数据
		List<AttendInfo> selectAllDayAttendList = attendInfoService.selectAll(attendInfo);
		System.out.println("------------2-----------------");
	
		AttendDetailInfo attendDetailInfo = new AttendDetailInfo();
			Calendar   cal   =   Calendar.getInstance();
			cal.add(Calendar.DATE,   -1);
			String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		attendDetailInfo.setSign_time(yesterday);
		
		List<AttendDetailInfo> allodayList = attendInfoService.selectTodayAllAttend(attendDetailInfo);
		System.out.println("------------3-----------------");
		
		if(selectAllDayAttendList!=null && selectAllDayAttendList.size()!=0){
			boolean f = false;
			for (int i = 0; i < selectAllDayAttendList.size(); i++) {
				
				AttendInfo getAttendInfo =  selectAllDayAttendList.get(i);
				if(allodayList!=null && allodayList.size()!=0){
					for (int j = 0; j < allodayList.size(); j++) {
						AttendDetailInfo getAttendDetailInfo = allodayList.get(j);
						if(getAttendInfo.getEmployee_id().equals(getAttendDetailInfo.getEmployee_id())){
							f = true;
							System.out.println("------------4-----------------");
						}
						if(j==allodayList.size()-1 && !f){
							System.out.println("------------4----"+selectAllDayAttendList.get(i).getEmployee_name()+"不存在签到------------");
							AttendDetailInfo attendDetailInfo1 = new AttendDetailInfo();
							attendDetailInfo1.setEmployee_id(selectAllDayAttendList.get(i).getEmployee_id());
							attendDetailInfo1.setEmployee_name(selectAllDayAttendList.get(i).getEmployee_name());
							attendDetailInfo1.setCreateUserName(selectAllDayAttendList.get(i).getEmployee_name());
							attendDetailInfo1.setEmployee_no(selectAllDayAttendList.get(i).getEmployee_no());
							attendDetailInfo1.setCreateUserId(selectAllDayAttendList.get(i).getEmployee_id());
							String yesterdayTime = yesterday+"23:59:59";
							attendDetailInfo1.setCreateTime(yesterdayTime);
							attendInfoService.insertNoSign(attendDetailInfo1);
						}
					}
					
				}else{
					System.out.println("------------4----没有一个签到的------------");
					AttendDetailInfo attendDetailInfo1 = new AttendDetailInfo();
					attendDetailInfo1.setEmployee_id(selectAllDayAttendList.get(i).getEmployee_id());
					attendDetailInfo1.setEmployee_name(selectAllDayAttendList.get(i).getEmployee_name());
					attendDetailInfo1.setCreateUserName(selectAllDayAttendList.get(i).getEmployee_name());
					attendDetailInfo1.setEmployee_no(selectAllDayAttendList.get(i).getEmployee_no());
					attendDetailInfo1.setCreateUserId(selectAllDayAttendList.get(i).getEmployee_id());
					String yesterdayTime = yesterday+"23:59:59";
					attendDetailInfo1.setCreateTime(yesterdayTime);
					attendInfoService.insertNoSign(attendDetailInfo1);
				}
				
			}
		}
		
		
	}
	
	
}
