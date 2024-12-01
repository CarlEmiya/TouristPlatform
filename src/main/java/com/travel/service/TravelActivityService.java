package com.travel.service;

import com.travel.entity.TravelActivity;
import com.travel.entity.TravelActivityExample;

import java.util.List;

public interface TravelActivityService {
	// 发布组团旅游活动
	int addActivity(TravelActivity activity);

	// 根据条件查询活动列表
	List<TravelActivity> searchActivities(TravelActivityExample activity);

	// 取消活动
	int CancelActivity(Long aid, int newStatus, String cancelReason);

	// 修改活动状态
	public int updateActivityStatus(Long aid, int newStatus);

	// 更新活动内容
	int updateActivity(TravelActivity activity);
}