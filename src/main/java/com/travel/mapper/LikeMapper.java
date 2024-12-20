package com.travel.mapper;

import com.travel.entity.Like;
import com.travel.entity.LikeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeMapper {
    long countByExample(LikeExample example);

    int deleteByExample(LikeExample example);

    int deleteByPrimaryKey(Long lid);

    int insert(Like record);

    int insertSelective(Like record);

    List<Like> selectByExample(LikeExample example);

    Like selectByPrimaryKey(Long lid);

    int updateByExampleSelective(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByExample(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);

    int getCountLike(@Param("connectid") Long connectid, @Param("type") String type);
}