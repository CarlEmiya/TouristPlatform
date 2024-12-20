package com.travel.service;

import com.travel.entity.File;

import java.util.List;

public interface FileService {
    // 保存文件信息
    int saveFiles(List<File> files);

    // 根据连接ID查询文件
    List<File> getFilesByConnectedId(Long connectedId);

    int deleteFileByFid(Long fid);

}
