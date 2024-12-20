package com.travel.service.impl;

import com.travel.entity.File;
import com.travel.entity.FileExample;
import com.travel.mapper.FileMapper;
import com.travel.service.FileService;
import com.travel.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    private Random random = new Random();

    @Autowired
    private ReportService reportService;

    public List<File> getFilesByConnectIds(List<Long> connectIds, String type) {
        FileExample example = new FileExample();
        example.createCriteria().andConnectidIn(connectIds).andTypeEqualTo(type);
        return fileMapper.selectByExample(example);
    }


    @Override
    public int saveFiles(List<File> files) {
        int result = 0;
        for (File file : files) {
            result += fileMapper.insert(file);
        }
        return result;
    }

    @Override
    public List<File> getFilesByConnectedId(Long connectedId) {
        // 通过connected字段查找文件
        FileExample example = new FileExample();
        example.createCriteria().andConnectidEqualTo(connectedId);
        return fileMapper.selectByExample(example);
    }

    @Override
    public int deleteFileByFid(Long fid) {
        try {
            fileMapper.deleteByPrimaryKey(fid);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 实现上传文件
     * @param files
     * @return
     */
    public List<String> uploadFiles(MultipartFile[] files, Long connectedId, String type) {
        List<String> filePaths = new ArrayList<>();
        List<com.travel.entity.File> fileList = new ArrayList<>();
        String uploadDir = "D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded\\";

        for (MultipartFile file : files) {
            // 获取文件后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // 随机生成fid，如果已存在，重新生成
            Long fid = generateUniqueRid(); // 调用方法确保唯一性
            String path = uploadDir + fid + suffix;

            // 检查文件是否已存在
            File existingFile = new File(path);
            if (fileMapper.isFidExist(fid)) {
                // 如果文件已存在，跳过上传
                filePaths.add(path);
                continue;
            }

            try {
                // 上传文件到指定目录
                file.transferTo(new java.io.File(path));
                // 保存文件信息到数据库
                com.travel.entity.File fileEntity = new com.travel.entity.File(fid, connectedId, path, type);
                fileList.add(fileEntity);
                filePaths.add(path);  // 添加文件路径
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("文件上传失败", e);  // 抛出异常，以便外部处理
            }
        }

        // 存储文件信息
        if (!fileList.isEmpty()) {
            saveFiles(fileList);
        }
        return filePaths;
    }


    private Long generateUniqueRid() {
        Long rid;
        do {
            rid = (long) (Math.random() * 1000000000);  // 随机生成一个rid
        } while (reportService.isRidExist(rid));  // 检查rid是否存在
        return rid;
    }

}
