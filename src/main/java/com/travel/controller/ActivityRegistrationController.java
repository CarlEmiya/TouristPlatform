package com.travel.controller;

import com.travel.entity.ActivityRegistration;
import com.travel.entity.TravelActivity;
import com.travel.entity.User;
import com.travel.service.ActivityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activityRegistration")
public class ActivityRegistrationController {

    @Autowired
    private ActivityRegistrationService activityRegistrationService;

    // 报名活动
    @PostMapping("/register")
    public ResponseEntity<Integer> registerForActivity(@RequestBody ActivityRegistration registration) {
        int result = activityRegistrationService.registerForActivity(registration);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 根据活动id获取报名用户列表
    @GetMapping("/byActivityId")
    public ResponseEntity<List<User>> getRegistrationsByActivityId(@RequestParam("aid") Long aid) {
        List<User> users = activityRegistrationService.getRegistrationsByActivityId(aid);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 查询用户参加的活动列表
    @GetMapping("/byUid")
    public ResponseEntity<List<TravelActivity>> getActivitiesByUid(@RequestParam("uid") Long uid) {
        List<TravelActivity> activities = activityRegistrationService.getActivitiesByUid(uid);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 根据报名id取消报名
    @PutMapping("/cancel")
    public ResponseEntity<Integer> cancelRegistration(@RequestParam("registrationId") String registrationId) {
        int result = activityRegistrationService.cancelRegistration(registrationId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 修改报名状态
    @PutMapping("/updateStatus")
    public ResponseEntity<Integer> updateRegistrationStatus(
            @RequestParam("registrationId") String registrationId,
            @RequestParam("status") Integer status) {
        int result = activityRegistrationService.updateRegistrationStatus(registrationId, status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}