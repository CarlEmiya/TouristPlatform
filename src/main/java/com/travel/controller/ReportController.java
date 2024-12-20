package com.travel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Report;
import com.travel.entity.ReportExample;
import com.travel.entity.TravelActivity;
import com.travel.service.ReportService;
import com.travel.service.impl.FileServiceImpl;
import com.travel.service.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;  // 假设有一个服务类来处理业务逻辑
    @Autowired
    private FileServiceImpl fileService;
    @RequestMapping("/list")
    public String listReports(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                              @RequestParam(value = "rid", required = false) Long rid,
                              @RequestParam(value = "type", required = false) String type,
                              @RequestParam(value = "category", required = false) String category,
                              @RequestParam(value = "description", required = false) String description,
                              Model model) {

        List<Report> reports = new ArrayList<>();

        // 设置分页
        PageHelper.startPage(pageNo, pageSize);

        // 创建查询条件
        ReportExample example = new ReportExample();
        ReportExample.Criteria criteria = example.createCriteria();

        if (rid != null) {
            criteria.andRidEqualTo(rid);
        }
        if (type != null && !type.isEmpty() && type!= "") {
            criteria.andTypeEqualTo(type);
        }
        if (category != null && category.isEmpty() && category != "") {
            criteria.andCategoryEqualTo(category);
        }
        if (description != null && !description.isEmpty()    && description!= "") {
            criteria.andDescriptionLike("%" + description + "%");
        }

        // 获取分页后的举报数据
        reports = reportService.selectByExample(example);
        
        //获取其文件列表
        if (!reports.isEmpty()) {
            // 获取文件（图片）信息
            List<Long> reportIds = reports.stream()
                    .map(Report::getRid)  // 获取活动ID
                    .collect(Collectors.toList());

            // 假设文件接口返回的文件是按活动ID关联的，返回的是 List
            List<com.travel.entity.File> fileList = fileService.getFilesByConnectIds(reportIds, "Report");

            Map<Long, com.travel.entity.File> fileMap = fileList.stream()
                    .collect(Collectors.toMap(
                            com.travel.entity.File::getConnectid,  // key: 通过 connectid 作为键
                            file -> file,  // value: 文件对象
                            (existing, replacement) -> existing  // 如果有冲突，选择第一个文件（existing）
                    ));

// 将文件ID传递给活动列表中的每个活动（假设每个活动只有一个文件，且获取第一个文件）
            for (Report report : reports) {
                // 获取活动ID并根据ID获取对应的文件
                Long reportId = report.getRid();
                com.travel.entity.File firstFile = fileMap.get(reportId);

                if (firstFile != null) {
                    // 获取文件的绝对路径
                    String absoluteFilePath = firstFile.getPath();
                    // 将绝对路径替换为虚拟路径
                    // 确保将文件路径从本地路径映射到可以被访问的虚拟路径
                    String virtualPath = absoluteFilePath.replace("D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded", "\\uploaded");

                    // 设置活动的第一张图片路径为虚拟路径
                    report.setFirstFilePath(virtualPath);
                } else {
                    // 如果没有找到文件，设置第一张图片路径为空
                    report.setFirstFilePath(null);
                }
            }
        }
        


        PageInfo<Report> pageInfo = new PageInfo<>(reports);

        model.addAttribute("reports",reports);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("SearchedRid", rid);
        model.addAttribute("SearchedType", type);
        model.addAttribute("SearchedCategory", category);
        model.addAttribute("SearchedDescription", description);
        model.addAttribute("reportTypes", Arrays.asList("Activity", "Comment", "User", "Diary"));
        model.addAttribute("categories", Arrays.asList("dislike", "FalseInformation", "Infringement", "SexualLewd", "CyberBullying", "politicallySensitive", "delinquency", "else"));
        model.addAttribute("reports", reports);
        return "report/list";  // 返回Thymeleaf视图名称
    }
    
    //返回信息，不跳转页面
    @PostMapping("/handle")
    public ResponseEntity<Map<String, Object>> handleReport(@RequestParam("rid") Long rid,
                                               @RequestParam("type") String type,
                                               @RequestParam("handler") String handler,
                                               @RequestParam("action") String action,
                                               Model model) {
        // 创建返回结果的Map
        Map<String, Object> response = new HashMap<>();
        String message = "";
        String status = "error";  // 默认为失败
        // 处理举报
        int result = 0;
        if ("ban".equals(action)) {
            if (reportService.banReportedEntity(rid, type, new Date(), handler) == true) {
                result = 1;
            } // 封禁操作
        } else if ("delete".equals(action)) {
            if (reportService.deleteReportedEntity(rid, type, new Date(), handler)) ;  // 删除操作
            {
                result = 1;
            }
        } else if ("reject".equals(action)) {
            if (reportService.rejectReport(rid, new Date(), handler)) {// 忽略操作
                result = 2;
            }
        }

        // 根据操作结果设置message和status
        if (result == 1) {
            message = "操作成功";
            status = "success";
        } else if (result == 2) {
            message = "操作成功，举报已拒绝";
            status = "success";
        } else {
            message = "操作失败";
            status = "error";
        }

        // 设置返回的响应体
        response.put("message", message);
        response.put("status", status);

        // 返回响应
        return ResponseEntity.ok(response);
    }
}

