package com.qdu.mapper;

import com.qdu.entity.TravelDiary;
import com.qdu.entity.TravelDiaryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelDiaryMapper {
    long countByExample(TravelDiaryExample example);

    int deleteByExample(TravelDiaryExample example);

    int deleteByPrimaryKey(Long did);

    int insert(TravelDiary record);

    int insertSelective(TravelDiary record);

    List<TravelDiary> selectByExampleWithBLOBs(TravelDiaryExample example);

    List<TravelDiary> selectByExample(TravelDiaryExample example);

    TravelDiary selectByPrimaryKey(Long did);

    int updateByExampleSelective(@Param("record") TravelDiary record, @Param("example") TravelDiaryExample example);

    int updateByExampleWithBLOBs(@Param("record") TravelDiary record, @Param("example") TravelDiaryExample example);

    int updateByExample(@Param("record") TravelDiary record, @Param("example") TravelDiaryExample example);

    int updateByPrimaryKeySelective(TravelDiary record);

    int updateByPrimaryKeyWithBLOBs(TravelDiary record);

    int updateByPrimaryKey(TravelDiary record);
}