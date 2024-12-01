package com.travel.mapper;

import com.travel.entity.Report;
import com.travel.entity.ReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper {
    long countByExample(ReportExample example);

    int deleteByExample(ReportExample example);

    int deleteByPrimaryKey(Long rid);

    int insert(Report record);

    int insertSelective(Report record);

    List<Report> selectByExampleWithBLOBs(ReportExample example);

    List<Report> selectByExample(ReportExample example);

    Report selectByPrimaryKey(Long rid);

    int updateByExampleSelective(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExampleWithBLOBs(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByExample(@Param("record") Report record, @Param("example") ReportExample example);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKeyWithBLOBs(Report record);

    int updateByPrimaryKey(Report record);
}