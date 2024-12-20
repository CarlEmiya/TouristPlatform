package com.travel.service.impl;

import com.travel.entity.Report;
import com.travel.mapper.ReportMapper;
import com.travel.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    /**
     * 举报评论
     * @param reporter
     * @param reported
     * @param type
     * @param category
     * @param description
     * @return
     */
    @Override
    public int insertReport(Long rid,Long reporter, Long reported, String category, String type,  String description) {
        Report report = new Report(rid, reporter, reported,  type,category, new Date(), 12, null, null, description);
        try {
            reportMapper.insert(report);
            return 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean isRidExist(Long rid) {
        if(reportMapper.isRidExist(rid) != 0)
            return true;
        else
            return false;
    }
}
