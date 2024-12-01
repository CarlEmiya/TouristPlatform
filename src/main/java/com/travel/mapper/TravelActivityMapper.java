package com.travel.mapper;

import com.travel.entity.TravelActivity;
import com.travel.entity.TravelActivityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelActivityMapper {
    long countByExample(TravelActivityExample example);

    int deleteByExample(TravelActivityExample example);

    int deleteByPrimaryKey(Long aid);

    int insert(TravelActivity record);

    int insertSelective(TravelActivity record);

    List<TravelActivity> selectByExampleWithBLOBs(TravelActivityExample example);

    List<TravelActivity> selectByExample(TravelActivityExample example);

    TravelActivity selectByPrimaryKey(Long aid);

    int updateByExampleSelective(@Param("record") TravelActivity record, @Param("example") TravelActivityExample example);

    int updateByExampleWithBLOBs(@Param("record") TravelActivity record, @Param("example") TravelActivityExample example);

    int updateByExample(@Param("record") TravelActivity record, @Param("example") TravelActivityExample example);

    int updateByPrimaryKeySelective(TravelActivity record);

    int updateByPrimaryKeyWithBLOBs(TravelActivity record);

    int updateByPrimaryKey(TravelActivity record);
}