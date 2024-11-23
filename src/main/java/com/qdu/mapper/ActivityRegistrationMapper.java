package com.qdu.mapper;

import com.qdu.entity.ActivityRegistration;
import com.qdu.entity.ActivityRegistrationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityRegistrationMapper {
    long countByExample(ActivityRegistrationExample example);

    int deleteByExample(ActivityRegistrationExample example);

    int deleteByPrimaryKey(String registrationid);

    int insert(ActivityRegistration record);

    int insertSelective(ActivityRegistration record);

    List<ActivityRegistration> selectByExample(ActivityRegistrationExample example);

    ActivityRegistration selectByPrimaryKey(String registrationid);

    int updateByExampleSelective(@Param("record") ActivityRegistration record, @Param("example") ActivityRegistrationExample example);

    int updateByExample(@Param("record") ActivityRegistration record, @Param("example") ActivityRegistrationExample example);

    int updateByPrimaryKeySelective(ActivityRegistration record);

    int updateByPrimaryKey(ActivityRegistration record);
}