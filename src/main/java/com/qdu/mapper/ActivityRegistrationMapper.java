package com.qdu.mapper;

import com.qdu.entity.ActivityRegistration;
import com.qdu.entity.ActivityRegistrationExample;
import com.qdu.entity.TravelActivity;
import com.qdu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityRegistrationMapper {
    long countByExample(ActivityRegistrationExample example);

    int deleteByExample(ActivityRegistrationExample example);

    int deleteByPrimaryKey(Long arid);

    int insert(ActivityRegistration record);

    int insertSelective(ActivityRegistration record);

    List<ActivityRegistration> selectByExample(ActivityRegistrationExample example);

    List<User> getActivityRegistrationByAid(Long aid);

    ActivityRegistration selectByPrimaryKey(Long arid);

    int updateByExampleSelective(@Param("record") ActivityRegistration record, @Param("example") ActivityRegistrationExample example);

    int updateByExample(@Param("record") ActivityRegistration record, @Param("example") ActivityRegistrationExample example);

    int updateByPrimaryKeySelective(ActivityRegistration record);

    int updateByPrimaryKey(ActivityRegistration record);

    int updateStatus(String registrationId, int i);

    List<TravelActivity> getActivityByUid(Long uid);
}