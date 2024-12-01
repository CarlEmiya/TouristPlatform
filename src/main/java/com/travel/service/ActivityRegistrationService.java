package com.travel.service;

import com.travel.entity.ActivityRegistration;
import com.travel.entity.TravelActivity;
import com.travel.entity.User;

import java.util.List;

public interface ActivityRegistrationService {

	// 报名活动，设置报名状态为4（已报名）
	int registerForActivity(ActivityRegistration registration);

	// 根据活动id获取报名用户列表
	List<User> getRegistrationsByActivityId(Long aid);

	// 查询用户参加的活动列表
	List<TravelActivity> getActivitiesByUid(Long uid);

	// 根据报名id取消报名
	int cancelRegistration(String registrationId);

	// 修改报名状态
	int updateRegistrationStatus(String registrationId, Integer status);
}