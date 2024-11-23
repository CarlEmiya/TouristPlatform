package com.qdu.service.impl;

import com.qdu.entity.TravelActivityExample;
import com.qdu.mapper.TravelActivityMapper;
import com.qdu.entity.TravelActivity;
import com.qdu.service.TravelActivityService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TravelActivityServiceImpl implements TravelActivityService {

//	@Autowired
	private TravelActivityMapper activityMapper;

	@Override
	public int addActivity(TravelActivity activity) {
		try {
			activity.setDeletionperiod(365);
			activityMapper.insert(activity);

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<TravelActivity> searchActivities(TravelActivityExample activity) {
		try {
			return activityMapper.selectByExample(activity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public int updateActivityStatus(String activityId, int newStatus) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(activityId);
			if (activity!= null) {
				activity.setStatus(newStatus);
				// 使用updateByPrimaryKey方法根据主键更新活动对象
				activityMapper.updateByPrimaryKey(activity);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int CancelActivity(String activityId, int newStatus, String cancelReason) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(activityId);
			if (activity!= null) {
				activity.setStatus(newStatus);
				if (newStatus == 7) {
					activity.setCancelReason(cancelReason);
					activity.setStatus(7);
				}
				activityMapper.updateByPrimaryKey(activity);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}


	@Override
	public int updateActivity(TravelActivity activity) {
		try {
			activityMapper.updateByPrimaryKey(activity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 预删除活动
	public int deleteActivity(TravelActivity activity,int deletePeriod) {
		try {
			activity.setStatus(3);
			activity.setDeletionperiod(deletePeriod);
			activity.setDeletedat(new Date());
			activityMapper.updateByPrimaryKey(activity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	//撤回删除活动
	public int withdrawActivity(TravelActivity activity) {
		try {
			activity.setStatus(16);
			activity.setDeletionperiod(0);
			activity.setDeletedat(null);
			activityMapper.updateByPrimaryKey(activity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 查询单个活动
	public TravelActivity getActivityByActivityId(String activityId) {
		try {
			return activityMapper.selectByPrimaryKey(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 根据条件查活动
	public List<TravelActivity> searchActivitiesByCondition(TravelActivityExample activity) {
		try {
			// 不要status为2的
			activity.createCriteria().andStatusNotEqualTo(2);
			return activityMapper.selectByExample(activity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 根据可筛选活动列表，查看最近一个月、三个月、半年或一年发布的活动。
// 参数：时间间隔（以天为单位，例如一个月按30天算，三个月按90天算等）
	public List<TravelActivity> searchActivitiesByTime(int timeInterval) {
		try {
			TravelActivityExample example = new TravelActivityExample();
			Date currentTime = new Date();
			Date endTime = new Date(currentTime.getTime() - timeInterval * 24 * 60 * 60 * 1000);

			// 设置基于发布时间字段的时间范围查询条件
			example.createCriteria().andStartdateBetween(endTime, currentTime).andStatusNotEqualTo(2);
			return activityMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<TravelActivity> searchActivitiesByTimeAndUserId(String userId, int timeInterval) {
		try {
			TravelActivityExample example = new TravelActivityExample();
			Date currentTime = new Date();
			Date endTime = new Date(currentTime.getTime() - timeInterval * 24 * 60 * 60 * 1000);
			// 设置基于发布时间字段的时间范围查询条件
			example.createCriteria().andStartdateBetween(endTime, currentTime).andUseridEqualTo(userId);
			return activityMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//隐藏活动
	public int hideActivity(String activityId) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(activityId);
			if (activity!= null) {
				activity.setStatus(2);
				activityMapper.updateByPrimaryKey(activity);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}