package com.qdu.controller;

import com.qdu.entity.TravelActivity;
import com.qdu.entity.TravelActivityExample;
import com.qdu.service.TravelActivityService;
import com.qdu.service.impl.TravelActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travelActivity")
public class TravelActivityController {

    @Autowired
    private TravelActivityServiceImpl travelActivityService;

    // 添加旅游活动
    @PostMapping("/add")
    public ResponseEntity<Integer> addActivity(@RequestBody TravelActivity activity) {
        activity.setDeletionperiod(365); // 设置默认删除周期为365天
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
            @RequestParam("activityId") String activityId,
            @RequestParam("newStatus") int newStatus) {
        int result = travelActivityService.updateActivityStatus(activityId, newStatus);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 取消活动，并记录取消原因
    @PutMapping("/cancel")
    public ResponseEntity<Integer> CancelActivity(
            @RequestParam("activityId") String activityId,
            @RequestParam("newStatus") int newStatus,
            @RequestParam("cancelReason") String cancelReason) {
        int result = travelActivityService.CancelActivity(activityId, newStatus, cancelReason);
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
            @RequestParam("activityId") String activityId) {
        TravelActivity activity = travelActivityService.getActivityByActivityId(activityId);
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
    @GetMapping("/searchByTimeAndUserId")
    public ResponseEntity<List<TravelActivity>> searchActivitiesByTimeAndUserId(
            @RequestParam("userId") String userId,
            @RequestParam("timeInterval") int timeInterval) {
        List<TravelActivity> activities = travelActivityService.searchActivitiesByTimeAndUserId(userId, timeInterval);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 隐藏活动
    @PutMapping("/hide")
    public ResponseEntity<Integer> hideActivity(@RequestParam("activityId") String activityId) {
        int result = travelActivityService.hideActivity(activityId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}