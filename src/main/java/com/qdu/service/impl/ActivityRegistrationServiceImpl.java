package com.qdu.service.impl;

import com.qdu.entity.TravelActivity;
import com.qdu.entity.User;
import com.qdu.mapper.ActivityRegistrationMapper;
import com.qdu.entity.ActivityRegistration;
import com.qdu.service.ActivityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityRegistrationServiceImpl implements ActivityRegistrationService {

//    @Autowired
    private ActivityRegistrationMapper activityRegistrationMapper;

    /**
     * 报名活动，设置报名状态为4（已报名）
     * @param registration
     * @return
     */
    @Override
    public int registerForActivity(ActivityRegistration registration) {
        try {
            registration.setStatus(4);
            // 设定报名具体时间：当前时间
            registration.setTime(new Date());
            activityRegistrationMapper.insert(registration);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 根据活动id获取报名用户列表
     * @param aid
     * @return
     */
    @Override
    public List<User> getRegistrationsByActivityId(Long aid) {
        try {

            return activityRegistrationMapper.getActivityRegistrationByAid(aid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询用户参加的活动列表
     * @param uid
     * @return
     */
    @Override
    public List<TravelActivity> getActivitiesByUid(Long uid) {
        try {
            return activityRegistrationMapper.getActivityByUid(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据报名id和用户id取消报名
     */
//    @Override
    public int cancelRegistration(String registrationId) {
        try {
            return activityRegistrationMapper.updateStatus(registrationId,7);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 修改报名状态
     */
    public int updateRegistrationStatus(String registrationId, Integer status) {
        try {
            activityRegistrationMapper.updateStatus(registrationId,status);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}