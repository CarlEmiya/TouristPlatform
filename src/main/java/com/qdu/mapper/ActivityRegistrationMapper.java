package com.qdu.mapper;

import com.qdu.entity.ActivityRegistration;
import com.qdu.entity.ActivityRegistrationExample;
import java.util.List;

import com.qdu.entity.TravelActivity;
import com.qdu.entity.User;
import org.apache.ibatis.annotations.Param;

public interface ActivityRegistrationMapper {
    long countByExample(ActivityRegistrationExample example);

    int deleteByExample(ActivityRegistrationExample example);

    int deleteByPrimaryKey(String registrationid);

    int insert(ActivityRegistration record);

    int insertSelective(ActivityRegistration record);

    List<ActivityRegistration> selectByExample(ActivityRegistrationExample example);

    ActivityRegistration selectByPrimaryKey(String registrationid);

    //根据ActivityId查询报名人员
    List<User> getActivityRegistrationByActivityId(String activityId);

    List<TravelActivity> getActivityByUserId(String userId);

    int updateByExampleSelective(@Param("record") ActivityRegistration record, @Param("example") ActivityRegistrationExample example);

    int updateByExample(@Param("record") ActivityRegistration record, @Param("example") ActivityRegistrationExample example);

    int updateByPrimaryKeySelective(ActivityRegistration record);

    int updateByPrimaryKey(ActivityRegistration record);
    //修改报名状态
    int updateStatus(@Param("registrationId") String registrationid, @Param("status") int status);
}