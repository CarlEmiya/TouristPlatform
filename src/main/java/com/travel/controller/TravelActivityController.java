package com.travel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.TravelActivity;
import com.travel.entity.TravelActivityExample;
import com.travel.service.impl.FileServiceImpl;
import com.travel.service.impl.TravelActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/travelActivity")
public class TravelActivityController {

    @Autowired
    private TravelActivityServiceImpl travelActivityService;
    @Autowired
    private FileServiceImpl fileService;

    @GetMapping("/list")
    public String listActivities(
            @RequestParam(defaultValue = "1") int pageNo,  // 当前页
            @RequestParam(defaultValue = "5") int pageSize,  // 每页条数
            @RequestParam(required = false) String keyword,  // 关键字筛选
            @RequestParam(required = false) Integer status,  // 活动状态筛选（如进行中、结束）
            @RequestParam(required = false) Integer timeInterval,  // 时间筛选（如最近一个月、三个月等）
            Model model) {

        // 设置分页
        PageHelper.startPage(pageNo, pageSize);

        // 创建查询条件
        TravelActivityExample activityExample = new TravelActivityExample();
        TravelActivityExample.Criteria criteria = activityExample.createCriteria();

        // 根据关键字筛选
        if (keyword != null && !keyword.trim().isEmpty()) {
            criteria.andTitleLike("%" + keyword + "%");
        }

        // 根据活动状态筛选
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }

        // 根据时间间隔筛选（按最近一段时间内发布的活动）
        if (timeInterval != null) {
            long currentTime = System.currentTimeMillis();
            long timeIntervalInMillis = 0;

            switch (timeInterval) {
                case 1: // 最近一个月
                    timeIntervalInMillis = 30L * 24 * 60 * 60 * 1000;
                    break;
                case 3: // 最近三个月
                    timeIntervalInMillis = 90L * 24 * 60 * 60 * 1000;
                    break;
                case 6: // 最近半年
                    timeIntervalInMillis = 180L * 24 * 60 * 60 * 1000;
                    break;
                case 12: // 最近一年
                    timeIntervalInMillis = 365L * 24 * 60 * 60 * 1000;
                    break;
            }

            long timeLimit = currentTime - timeIntervalInMillis;
            criteria.andStartGreaterThan(new java.util.Date(timeLimit));
        }

        // 查询符合条件的活动
        List<TravelActivity> activities = travelActivityService.searchActivities(activityExample);

        // 获取文件（图片）信息
        List<Long> activityIds = activities.stream()
                .map(TravelActivity::getAid)  // 获取活动ID
                .collect(Collectors.toList());

        // 假设文件接口返回的文件是按活动ID关联的，返回的是 List
        List<com.travel.entity.File> fileList = fileService.getFilesByConnectIds(activityIds, "Activity");

                Map<Long, com.travel.entity.File> fileMap = fileList.stream()
                .collect(Collectors.toMap(
                        com.travel.entity.File::getConnectid,  // key: 通过 connectid 作为键
                        file -> file,  // value: 文件对象
                        (existing, replacement) -> existing  // 如果有冲突，选择第一个文件（existing）
                ));

// 将文件ID传递给活动列表中的每个活动（假设每个活动只有一个文件，且获取第一个文件）
        for (TravelActivity activity : activities) {
            // 获取活动ID并根据ID获取对应的文件
            Long activityId = activity.getAid();
            com.travel.entity.File firstFile = fileMap.get(activityId);

            if (firstFile != null) {
                // 获取文件的绝对路径
                String absoluteFilePath = firstFile.getPath();

                // 将绝对路径替换为虚拟路径
                // 确保将文件路径从本地路径映射到可以被访问的虚拟路径
                String virtualPath = absoluteFilePath.replace("D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\", "\\uploaded\\");

                // 设置活动的第一张图片路径为虚拟路径
                activity.setFirstFilePath(virtualPath);
            } else {
                // 如果没有找到文件，设置第一张图片路径为空
                activity.setFirstFilePath(null);
            }
        }


        // 使用PageInfo来获取分页信息
        PageInfo<TravelActivity> pageInfo = new PageInfo<>(activities);

        // 将查询结果和分页信息传递到前端
        model.addAttribute("activities", activities);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("timeInterval", timeInterval);

        return "activity/list";  // 返回活动列表页面
    }







    // 获取指定活动的详细信息
    @GetMapping("/detail")
    public String getActivityDetail(@RequestParam("aid") Long aid, Model model) {
        // 根据活动 ID 获取活动详情
        TravelActivity activity = travelActivityService.getActivityByActivityId(aid);

        if (activity != null) {
            model.addAttribute("activity", activity);
            return "activity/detail";  // 返回活动详情页面
        } else {
            model.addAttribute("message", "活动不存在或已被删除");
            return "error";  // 如果活动不存在，跳转到错误页面
        }
    }

    // 访问发布活动页面
    @GetMapping("/publish")
    public String showPublishPage(Model model) {
        model.addAttribute("activity", new TravelActivity());  // 创建空活动对象
        return "activity/publish";  // 返回发布页面视图
    }

    // 发布活动的处理方法
    @PostMapping("/publish")
    public String publishActivity(@ModelAttribute TravelActivity activity, Model model) {
        // 设置默认活动周期为 365 天
        activity.setPeriod(365);

        // 调用 Service 方法保存活动
        int result = travelActivityService.addActivity(activity);

        if (result == 1) {
            model.addAttribute("message", "活动发布成功！");
            return "redirect:/travelActivity/list";  // 成功发布后跳转到活动列表页面
        } else {
            model.addAttribute("message", "活动发布失败，请重试！");
            return "activity/publish";  // 失败返回发布页面，提示错误信息
        }
    }

    // 活动参与
    @PostMapping("/join/{aid}")
    public String joinActivity(@PathVariable Long aid, Model model) {
        // 处理用户参与活动的逻辑
        // 比如：检查用户是否符合条件，更新活动状态等
        model.addAttribute("message", "成功参与活动");
        return "redirect:/travelActivity/detail/" + aid; // 重定向到活动详情
    }



    // 添加旅游活动
    @PostMapping("/add")
    public ResponseEntity<Integer> addActivity(@RequestBody TravelActivity activity) {
        activity.setPeriod(365); // 设置默认删除周期为365天
        int result = travelActivityService.addActivity(activity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 根据条件查询活动
    @PostMapping("/search")
    public ResponseEntity<List<TravelActivity>> searchActivities(@RequestBody TravelActivityExample activity) {
        List<TravelActivity> activities = travelActivityService.searchActivities(activity);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 更新指定活动的状态
    @PutMapping("/updateStatus")
    public ResponseEntity<Integer> updateActivityStatus(
            @RequestParam("aid") Long aid,
            @RequestParam("newStatus") int newStatus) {
        int result = travelActivityService.updateActivityStatus(aid, newStatus);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 取消活动，并记录取消原因
    @PutMapping("/cancel")
    public ResponseEntity<Integer> CancelActivity(
            @RequestParam("aid") Long aid,
            @RequestParam("newStatus") int newStatus,
            @RequestParam("cancelReason") String cancelReason) {
        int result = travelActivityService.CancelActivity(aid, newStatus, cancelReason);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 更新活动的所有信息
    @PutMapping("/update")
    public ResponseEntity<Integer> updateActivity(@RequestBody TravelActivity activity) {
        int result = travelActivityService.updateActivity(activity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 标记活动为“预删除”，设置删除周期和删除时间
    @PutMapping("/delete")
    public ResponseEntity<Integer> deleteActivity(
            @RequestBody TravelActivity activity,
            @RequestParam("deletePeriod") int deletePeriod) {
        int result = travelActivityService.deleteActivity(activity, deletePeriod);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 撤销活动删除状态，恢复为正常状态
    @PutMapping("/withdrawDelete")
    public ResponseEntity<Integer> withdrawActivity(@RequestBody TravelActivity activity) {
        int result = travelActivityService.withdrawActivity(activity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 获取指定ID的活动详细信息
    @GetMapping("/getActivity")
    public ResponseEntity<TravelActivity> getActivity(
            @RequestParam("aid") Long aid) {
        TravelActivity activity = travelActivityService.getActivityByActivityId(aid);
        if (activity!= null) {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 根据条件查询活动（与searchActivities功能相同）
    @PostMapping("/searchByCondition")
    public ResponseEntity<List<TravelActivity>> searchActivitiesByCondition(
            @RequestBody TravelActivityExample activity) {
        List<TravelActivity> activities = travelActivityService.searchActivitiesByCondition(activity);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 根据时间间隔筛选活动（例如最近一个月或三个月）
    @GetMapping("/searchByTime")
    public ResponseEntity<List<TravelActivity>> searchActivitiesByTime(
            @RequestParam("timeInterval") int timeInterval) {
        List<TravelActivity> activities = travelActivityService.searchActivitiesByTime(timeInterval);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 根据用户ID和时间间隔筛选活动
    @GetMapping("/searchByTimeAndUid")
    public ResponseEntity<List<TravelActivity>> searchActivitiesByTimeAndUid(
            @RequestParam("uid") Long uid,
            @RequestParam("timeInterval") int timeInterval) {
        List<TravelActivity> activities = travelActivityService.searchActivitiesByTimeAndUid(uid, timeInterval);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 隐藏活动
    @PutMapping("/hide")
    public ResponseEntity<Integer> hideActivity(@RequestParam("aid") Long aid) {
        int result = travelActivityService.hideActivity(aid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}