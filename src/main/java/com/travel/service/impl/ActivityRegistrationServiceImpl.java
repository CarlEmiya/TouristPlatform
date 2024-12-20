package com.travel.service.impl;

import com.travel.entity.ActivityRegistrationExample;
import com.travel.entity.TravelActivity;
import com.travel.entity.User;
import com.travel.mapper.ActivityRegistrationMapper;
import com.travel.entity.ActivityRegistration;
import com.travel.service.ActivityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityRegistrationServiceImpl implements ActivityRegistrationService {

    @Autowired
    private ActivityRegistrationMapper activityRegistrationMapper;


    @Override
    public int registerForActivity(Long aid,Long uid) {
        try {
            ActivityRegistration registration = new ActivityRegistration();
            Long arid = generateUniqueArid();
            registration.setArid(arid);
            registration.setAid(aid);
            registration.setUid(uid);
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

//    查询单个用户是否已经报名
    public boolean isRegister(Long aid, Long uid) {
        try {
            Boolean registration = activityRegistrationMapper.isRegistedByAidAndUid(aid, uid);
            return registration;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
    public int cancelRegistration(Long aid, Long uid) {
        try {
            return activityRegistrationMapper.deleteByAidAndUid(aid, uid);
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




    private Long generateUniqueArid() {
        Long id;
        do {
            id = (long) (Math.random() * 1000000000);  // 随机生成一个rid
        } while (activityRegistrationMapper.isAridExist(id));  // 检查rid是否存在
        return id;
    }

    public List<ActivityRegistration> selectByExample(ActivityRegistrationExample registrationExample) {
        List<ActivityRegistration> registrations = activityRegistrationMapper.selectByExample(registrationExample);
        return registrations;
    }

    public int deleteByUidAndAid(Long aid, Long userId) {
        try {
            return activityRegistrationMapper.deleteByAidAndUid(aid, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}