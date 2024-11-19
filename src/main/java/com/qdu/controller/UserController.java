package com.qdu.controller;

import com.qdu.entity.User;
import com.qdu.entity.UserExample;
import com.qdu.service.UserService;
import com.qdu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<Integer> registerUser(@RequestBody User user) {
        int result = userService.addUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam("userId") String userId,
                                          @RequestParam("password") String password) {
        User user = userService.loginUser(userId, password);
        if (user!= null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 修改个人信息
    @PutMapping("/updateInfo")
    public ResponseEntity<Integer> updateUserInfo(@RequestBody User user) {
        int result = userService.updateUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 修改密码
    @PutMapping("/updatePassword")
    public ResponseEntity<Integer> updatePassword(@RequestParam("userId") String userId,
                                                  @RequestParam("oldPassword") String oldPassword,
                                                  @RequestParam("newPassword") String newPassword) {
        User user = userService.loginUser(userId, oldPassword);
        if (user!= null) {
            user.setPassword(newPassword);
            int result = userService.updateUser(user);
            if (result == 1) {
                // 密码修改成功，可在这里添加逻辑让前端提示用户重新登录
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 管理员 - 检索用户
    @PostMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestBody UserExample example) {
        List<User> users = userService.searchUsers(example);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 管理员 - 修改用户等级
    @PutMapping("/updateLevel")
    public ResponseEntity<Integer> updateUserLevel(@RequestParam("userId") String userId,
                                                   @RequestParam("newLevel") int newLevel) {
        int result = userService.updateUserLevel(userId, newLevel);
        if (result == 1) {
            // 这里可添加发送通知给用户的逻辑，假设已经有一个发送通知的方法sendNotification
            // sendNotification(userId, "您的用户等级已被提升至" + newLevel);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 管理员 - 禁用用户账户
    @PutMapping("/disable")
    public ResponseEntity<Integer> disableUser(@RequestParam("userId") String userId) {
        int result = userService.disableUser(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 管理员 - 删除用户账户
    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteUser(@RequestParam("userId") String userId) {
        int result = userService.deleteUser(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 用户退出登录
    @GetMapping("/logout")
    public String logoutUser(Model model) {
        // 这里可添加清除session等相关逻辑，假设已经有一个清除session的方法clearSession
        model.addAttribute("message", "已成功退出登录");
        return "redirect:/login";
    }

    // 管理员 - 添加管理员（假设传入的是新管理员的用户信息对象）
    @PostMapping("/addAdmin")
    public ResponseEntity<Integer> addAdmin(@RequestBody User newAdmin) {
        // 这里可添加验证是否为已有管理员创建的逻辑，假设已经有一个验证方法isCreatedByExistingAdmin
        if (userService.isCreatedByExistingAdmin(newAdmin)) {
            int result = userService.addUser(newAdmin);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // 管理员 - 修改密码
    @PutMapping("/adminUpdatePassword")
    public ResponseEntity<Integer> adminUpdatePassword(@RequestParam("userId") String userId,
                                                       @RequestParam("oldPassword") String oldPassword,
                                                       @RequestParam("newPassword") String newPassword) {
        User user = userService.loginUser(userId, oldPassword);
        if (user!= null) {
            user.setPassword(newPassword);
            int result = userService.updateUser(user);
            if (result == 1) {
                // 密码修改成功，可在这里添加逻辑让前端提示管理员重新登录
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}