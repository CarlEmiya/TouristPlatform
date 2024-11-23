package com.qdu.mapper;

import com.qdu.entity.TravelActivity;
import com.qdu.entity.TravelActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TravelActivityMapper {
    long countByExample(TravelActivityExample example);

    int deleteByExample(TravelActivityExample example);

    int deleteByPrimaryKey(String activityid);

    int insert(TravelActivity record);

    int insertSelective(TravelActivity record);

    List<TravelActivity> selectByExampleWithBLOBs(TravelActivityExample example);

    List<TravelActivity> selectByExample(TravelActivityExample example);

    TravelActivity selectByPrimaryKey(String activityid);

    int updateByExampleSelective(@Param("record") TravelActivity record, @Param("example") TravelActivityExample example);

    int updateByExampleWithBLOBs(@Param("record") TravelActivity record, @Param("example") TravelActivityExample example);

    int updateByExample(@Param("record") TravelActivity record, @Param("example") TravelActivityExample example);

    int updateByPrimaryKeySelective(TravelActivity record);

    int updateByPrimaryKeyWithBLOBs(TravelActivity record);

    int updateByPrimaryKey(TravelActivity record);
}