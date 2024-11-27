package com.qdu.mapper;

import com.qdu.entity.File;
import com.qdu.entity.FileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper {
    long countByExample(FileExample example);

    int deleteByExample(FileExample example);

    int deleteByPrimaryKey(Long fid);

    int insert(File record);

    int insertSelective(File record);

    List<File> selectByExampleWithBLOBs(FileExample example);

    List<File> selectByExample(FileExample example);

    File selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") File record, @Param("example") FileExample example);

    int updateByExampleWithBLOBs(@Param("record") File record, @Param("example") FileExample example);

    int updateByExample(@Param("record") File record, @Param("example") FileExample example);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKeyWithBLOBs(File record);

    int updateByPrimaryKey(File record);
}