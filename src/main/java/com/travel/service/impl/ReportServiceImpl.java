package com.travel.service.impl;

import com.travel.controller.CommentController;
import com.travel.entity.Report;
import com.travel.entity.ReportExample;
import com.travel.mapper.ReportMapper;
import com.travel.service.CommentService;
import com.travel.service.ReportService;
import com.travel.service.TravelActivityService;
import com.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Lazy
    @Autowired
    private ReportMapper reportMapper;

    @Lazy
    @Autowired
    private UserServiceImpl userService;   // 假设有用户服务

    @Lazy
    @Autowired
    private TravelActivityServiceImpl activityService;  // 假设有活动服务

    @Lazy
    @Autowired
    private CommentController commentController;  // 假设有评论控制器

    @Lazy
    @Autowired
    private TravelDiaryServiceImpl diaryService;    // 假设有日记服务

    @Lazy
    @Autowired
    private CommentServiceImpl commentService;  // 假设有评论服务
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

    @Override
    public List<Report> selectByExample(ReportExample example) {
        try {
            return reportMapper.selectByExampleWithBLOBs(example);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean banReportedEntity(Long rid,
                                     String type,
                                     Date date,
                                     String handler) {
        Report report = reportMapper.selectByPrimaryKey(rid);
        if (report == null){
            return false;    // 举报不存在
        }
        else {
            if (type.equals("User")) {
                // 封禁用户
                int result = userService.disableUser(report.getReported());
                if (result == 1) {
                    Report report1 = new Report(rid, 15, handler, new Date());
                    reportMapper.updateByPrimaryKeySelective(report1);
                } else {
                    return false;    // 封禁失败
                }
            }else if (type.equals("Activity")) {
                // 封禁活动
                activityService.banActivity(report.getReported());
                Report report1 = new Report(rid, 15, handler, new Date());
                reportMapper.updateByPrimaryKeySelective(report1);
            } else if (type.equals("Diary")) {
                // 封禁评论
                diaryService.banComment(report.getReported());
                Report report1 = new Report(rid, 15, handler, new Date());
                reportMapper.updateByPrimaryKeySelective(report1);
            }
            else {
                return false;    // 举报类型错误
            }
            return true;
        }
    }

    public boolean deleteReportedEntity(Long rid,String type, Date date, String handler) {
        Report report = reportMapper.selectByPrimaryKey(rid);
        if (report == null){
            return false;    // 举报不存在
        }
        else {
            if (type.equals("User")) {
                // 删除用户
                userService.deleteUserTotally(report.getReported());
                Report report1 = new Report(rid, 15, handler, new Date());
                reportMapper.updateByPrimaryKeySelective(report1);
            } else if (type.equals("Activity")) {
                // 封禁活动
                activityService.totalDeleteActivity(report.getReported());
                Report report1 = new Report(rid, 15, handler, new Date());
                reportMapper.updateByPrimaryKeySelective(report1);
            } else if (type.equals("Diary")) {
                // 封禁评论
                diaryService.totalDeleteDiary(report.getReported());
                Report report1 = new Report(rid, 15, handler, new Date());
                reportMapper.updateByPrimaryKeySelective(report1);
            } else if (type.equals("Comment")) {
                // 封禁评论
                commentService.deleteCommentById(report.getReported());
                Report report1 = new Report(rid, 15, handler, new Date());
                reportMapper.updateByPrimaryKeySelective(report1);
            }  else {
                return false;    // 举报类型错误
            }
            return true;
        }
    }

    public boolean rejectReport(Long rid, Date date, String handler) {
        try {
            Report report = reportMapper.selectByPrimaryKey(rid);
            if (report == null){
                return false;    // 举报不存在
            }
            else {
                report.setStatus(14);
                report.setHandler(handler);
                report.setHandled(date);
                reportMapper.updateByPrimaryKeySelective(report);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Report> selectAll() {
        try {
            return reportMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
