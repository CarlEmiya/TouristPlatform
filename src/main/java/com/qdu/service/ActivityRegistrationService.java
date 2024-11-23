package com.qdu.service;

import com.qdu.entity.ActivityRegistration;
import com.qdu.entity.TravelActivity;
import com.qdu.entity.User;

import java.util.List;

public interface ActivityRegistrationService {

	// 报名活动，设置报名状态为4（已报名）
	int registerForActivity(ActivityRegistration registration);

	// 根据活动id获取报名用户列表
	List<User> getRegistrationsByActivityId(String activityId);

	// 查询用户参加的活动列表
	List<TravelActivity> getActivitiesByUserId(String userId);

	// 根据报名id取消报名
	int cancelRegistration(String registrationId);

	// 修改报名状态
	int updateRegistrationStatus(String registrationId, Integer status);
}