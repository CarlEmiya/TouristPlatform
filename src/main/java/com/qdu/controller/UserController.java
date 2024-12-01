package com.qdu.controller;
import javax.servlet.http.HttpSession;

import com.qdu.ErrorHandle.ApiResponse;
import com.qdu.ErrorHandle.CustomException;
import com.qdu.entity.User;
import com.qdu.entity.UserExample;
import com.qdu.mapper.UserMapper;
import com.qdu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user, HttpSession session) {
//        System.out.println(user);
        try {
            int result = userService.addUser(user); // 调用 Service 层逻辑添加用户
            if (result > 0) {
                // 添加用户信息到 Session 中
                session.setAttribute("currentUser", user);
                // 返回 200 状态码，并告知前端跳转地址
                return ResponseEntity.ok("/userInfo");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("注册失败，请稍后再试！");
            }
        } catch (CustomException e) {
            // 自定义异常处理
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // 未知异常处理
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("注册失败，请稍后再试！");
        }
    }

    /**
     * 从session中获取当前用户信息并渲染userInfo.html页面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/getUserInfo")
    public String userInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser"); // 从Session中获取当前用户
        if (user == null) {
            return "redirect:/login"; // 如果用户未登录，跳转到登录页
        }
        model.addAttribute("user", user); // 将用户信息添加到模型中
        return "userInfo"; // 渲染userInfo.html页面
    }


    // 用户登录
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user1,
                                          HttpSession session) {
        User user = userService.loginUser(user1.getUid(), user1.getPassword());
        if (user!= null) {
            session.setAttribute("currentUser", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 修改个人信息
//    @PostMapping("/updateInfo")
    @PostMapping("/updateInfo")
    public ResponseEntity<Integer> updateUserInfo(@RequestBody User user, HttpSession session) {
        try{
            int result = userService.updateUser(user);
            User user1 = userService.getUserById(user.getUid());
            session.setAttribute("currentUser", user1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // 修改密码
    @PostMapping("/updatePassword")
    public ResponseEntity<Integer> updatePassword(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        String newPassword = requestData.get("newPassword").toString();
        User user = userService.getUserById(uid);
        if (null != user) {
            User user1 = new User();
            user1.setUid(uid);
            user1.setPassword(newPassword);
            int result = userService.updateUser(user1);
            if (result == 1) {
                // 密码修改成功，可在这里添加逻辑让前端提示用户重新登录
                User user2 = userService.getUserById(uid);
                session.setAttribute("currentUser", user2);
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

    /**
     * 管理员 - 修改用户等级，并发送通知给用户
     * 还没实现发送通知的方法，假设已经有一个发送通知的方法sendNotification
     * @param requestData
     * @param session
     * @return
     */
    // 管理员 - 修改用户等级
    @PostMapping("/updateLevel")
    public ResponseEntity<Integer> updateUserLevel(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int newLevel = Integer.parseInt(requestData.get("newLevel").toString());
        int result = userService.updateUserLevel(uid, newLevel);

        // 这里可添加发送通知给用户的逻辑，假设已经有一个发送通知的方法sendNotification
        // sendNotification(uid, "您的用户等级已被提升至" + newLevel);


        if (result == 1) {
            // 这里可添加发送通知给用户的逻辑，假设已经有一个发送通知的方法sendNotification
            // sendNotification(uid, "您的用户等级已被提升至" + newLevel);
            User user1 = userService.getUserById(uid);
            session.setAttribute("currentUser", user1);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 管理员 - 禁用用户账户
    @PostMapping("/disable")
    public ResponseEntity<Integer> disableUser(@RequestBody Map<String, Object> requestData, HttpSession session ) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int result = userService.disableUser(uid);
        User user1 = userService.getUserById(uid);
        session.setAttribute("currentUser", user1);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 管理员 - 修改用户状态（启用/禁用）
     * @param requestData
     * @return
     */
    @PostMapping("/upstatus")
    public ResponseEntity<Integer> updateStatus(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int status = Integer.parseInt(requestData.get("status").toString());
        int result = userService.updateUserStatus(uid, status);
        User user1 = userService.getUserById(uid);
        session.setAttribute("currentUser", user1);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/enable")
    public ResponseEntity<Integer> enableUser(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int result = userService.enableUser(uid);
        User user1 = userService.getUserById(uid);
        session.setAttribute("currentUser", user1);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 管理员 - 删除用户账户
        @PostMapping("/delete")
    public ResponseEntity<Integer> deleteUser(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int period = Integer.parseInt(requestData.get("period").toString());
        int result = userService.deleteUser(uid,period);
            User user1 = userService.getUserById(uid);
            session.setAttribute("currentUser", user1);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 管理员 - 彻底删除用户账户
    @PostMapping("/deleteTotally")
    public ResponseEntity<Integer> deleteUserTotally(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int period = Integer.parseInt(requestData.get("period").toString());
        int result = userService.deleteUserTotally(uid);
        session.removeAttribute("currentUser");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 用户退出登录
    @GetMapping("/logout")
    public String logoutUser(Model model, HttpSession session) {
        // 这里可添加清除session等相关逻辑，假设已经有一个清除session的方法clearSession
        model.addAttribute("message", "已成功退出登录");
        // 清除session里的用户信息
        session.removeAttribute("currentUser");
        return "redirect:/login";
    }

    // 管理员 - 添加管理员（假设传入的是新管理员的用户信息对象）
    @PostMapping("/addAdmin")
    public ResponseEntity<Integer> addAdmin(@RequestBody Long uid) {
        // 这里可添加验证是否为已有管理员创建的逻辑，假设已经有一个验证方法isCreatedByExistingAdmin
        // 假设新管理员的用户信息对象为newAdmin
        User newAdmin = userMapper.selectByPrimaryKey(uid);
        if (userService.isCreatedByExistingAdmin(newAdmin)) {
            int result = userService.addUser(newAdmin);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}