package com.travel.service.impl;

import com.travel.entity.Like;
import com.travel.entity.LikeExample;
import com.travel.entity.TravelActivityExample;
import com.travel.mapper.ActivityRegistrationMapper;
import com.travel.mapper.LikeMapper;
import com.travel.mapper.TravelActivityMapper;
import com.travel.entity.TravelActivity;
import com.travel.service.TravelActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TravelActivityServiceImpl implements TravelActivityService {

	@Autowired
	private TravelActivityMapper activityMapper;

	private static final Logger logger = LoggerFactory.getLogger(TravelActivityServiceImpl.class);
    @Autowired
    private ActivityRegistrationMapper activityRegistrationMapper;
    @Autowired
    private LikeMapper likeMapper;

	@Override
	public int addActivity(TravelActivity activity) {
		try {
			activity.setPeriod(365);
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
			// 不要status为2的
			activity.createCriteria().andStatusNotEqualTo(2).andStatusNotEqualTo(3);
			// 按发布时间倒序排序
			activity.setOrderByClause("created desc");
			List<TravelActivity> list = activityMapper.selectByExample(activity);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public int updateActivityStatus(Long aid, int newStatus) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(aid);
			if (activity!= null) {
				activity.setStatus(newStatus);
				// 使用updateByPrimaryKey方法根据主键更新活动对象
				int result = activityMapper.updateByPrimaryKey(activity);
				if (result > 0) {
					return 1;
				} else {
					return 0;
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int CancelActivity(Long aid, int newStatus, String cancelReason) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(aid);
			if (activity!= null) {
				activity.setStatus(newStatus);
				if (newStatus == 7) {
					activity.setReason(cancelReason);
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
			activityMapper.updateByPrimaryKeyWithBLOBs(activity);
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
			activity.setPeriod(deletePeriod);
			activity.setDeleted(new Date());
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
			activity.setPeriod(0);
			activity.setDeleted(null);
			activityMapper.updateByPrimaryKey(activity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 查询单个活动
	public TravelActivity getActivityByActivityId(Long aid) {
		try {
			return activityMapper.selectByPrimaryKey(aid);
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
			example.createCriteria().andStartBetween(endTime, currentTime).andStatusNotEqualTo(2);
			return activityMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<TravelActivity> searchActivitiesByTimeAndUid(Long uid, int timeInterval) {
		try {
			TravelActivityExample example = new TravelActivityExample();
			Date currentTime = new Date();
			Date endTime = new Date(currentTime.getTime() - timeInterval * 24 * 60 * 60 * 1000);
			// 设置基于发布时间字段的时间范围查询条件
			example.createCriteria().andStartBetween(endTime, currentTime).andUidEqualTo(uid);
			return activityMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//隐藏活动
	public int hideActivity(Long aid) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(aid);
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

	public boolean isAidExist(Long aid) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(aid);
			if (activity != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

    public void updateActivityCurrent(Long aid) {
		try {
			TravelActivity activity = activityMapper.selectByPrimaryKey(aid);
			if (activity!= null) {
				activity.setCurrent(activityRegistrationMapper.selectCountByAid(aid));
				activityMapper.updateByPrimaryKey(activity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public int totalDeleteActivity(Long aid) {
		TravelActivity activity = activityMapper.selectByPrimaryKey(aid);
		try {
			if (activity != null) {
				activityMapper.deleteByPrimaryKey(aid);
				return 1;
			}else{
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int banActivity(Long reported) {
		try {
			TravelActivityExample example = new TravelActivityExample();
			example.createCriteria().andAidEqualTo(reported);
			TravelActivity activity = activityMapper.selectByExample(example).get(0);
			if(activity != null) {
				TravelActivity newActivity = new TravelActivity();
				newActivity.setAid(reported);
				newActivity.setStatus(2);
				activityMapper.updateByPrimaryKeyWithBLOBs(activity);
				return 1;
			}
			return 0;

		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getCountLike(Long aid,String type) {
		try {
			int likeCount = likeMapper.getCountLike(aid,"activity");
			return likeCount;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Boolean isLiked(Long aid, Long uid) {
		try {
			LikeExample example = new LikeExample();
			example.createCriteria().andConnectidEqualTo(aid).andUidEqualTo(uid).andTypeEqualTo("activity");
			List<Like> list = likeMapper.selectByExample(example);
			if (list.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int cancelLikeActivity(Long aid, Long uid) {
		try {
			LikeExample example = new LikeExample();
			example.createCriteria().andConnectidEqualTo(aid).andUidEqualTo(uid).andTypeEqualTo("activity");
			likeMapper.deleteByExample(example);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int LikeActivity(Long aid, Long uid) {
		try {
			Like like = new Like();
			like.setConnectid(aid);
			like.setUid(uid);
			like.setLid(generateUniqueId());
			like.setStatus(true);
			like.setType("activity");
			like.setCreated(new Date());
			likeMapper.insert(like);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}


	private Long generateUniqueId() {
		Long id;
		do {
			id = (long) (Math.random() * 1000000000);  // 随机生成一个rid
		} while (isIdExist(id));  // 检查rid是否存在
		return id;
	}

	private boolean isIdExist(Long id) {
		// 检查rid是否存在的逻辑
		// 这里可以根据实际情况进行实现，例如查询数据库等
		// 返回true表示rid已存在，返回false表示rid不存在
		TravelActivityExample example = new TravelActivityExample();
		example.createCriteria().andAidEqualTo(id);
		List<TravelActivity> list = activityMapper.selectByExample(example);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}
}