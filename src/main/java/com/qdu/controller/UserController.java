package com.qdu.controller;
import javax.servlet.http.HttpSession;

import com.qdu.ErrorHandle.ApiResponse;
import com.qdu.ErrorHandle.CustomException;
import com.qdu.entity.User;
import com.qdu.entity.UserExample;
import com.qdu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

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
            return "login"; // 如果用户未登录，跳转到登录页
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
    @PutMapping("/updateInfo")
    public ResponseEntity<Integer> updateUserInfo(@RequestBody User user, HttpSession session) {
        try{
            int result = userService.updateUser(user);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 修改密码
    @PutMapping("/updatePassword")
    public ResponseEntity<Integer> updatePassword(@RequestParam("uid") Long uid,
                                                  @RequestParam("oldPassword") String oldPassword,
                                                  @RequestParam("newPassword") String newPassword) {
        User user = userService.loginUser(uid, oldPassword);
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
    public ResponseEntity<Integer> updateUserLevel(@RequestParam("uid") Long uid,
                                                   @RequestParam("newLevel") int newLevel) {
        int result = userService.updateUserLevel(uid, newLevel);
        if (result == 1) {
            // 这里可添加发送通知给用户的逻辑，假设已经有一个发送通知的方法sendNotification
            // sendNotification(uid, "您的用户等级已被提升至" + newLevel);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 管理员 - 禁用用户账户
    @PutMapping("/disable")
    public ResponseEntity<Integer> disableUser(@RequestParam("uid") Long uid) {
        int result = userService.disableUser(uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 管理员 - 删除用户账户
    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteUser(@RequestParam("uid") Long uid) {
        int result = userService.deleteUser(uid);
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
    public ResponseEntity<Integer> adminUpdatePassword(@RequestParam("uid") Long uid,
                                                       @RequestParam("oldPassword") String oldPassword,
                                                       @RequestParam("newPassword") String newPassword) {
        User user = userService.loginUser(uid, oldPassword);
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