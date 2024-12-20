package com.travel.controller;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.ErrorHandle.CustomException;
import com.travel.entity.User;
import com.travel.entity.UserExample;
import com.travel.mapper.UserMapper;
import com.travel.service.impl.FileServiceImpl;
import com.travel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileServiceImpl fileService;

    // 上传头像接口
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadAvatar(
            @RequestParam("file") MultipartFile file,  // 确保这里的参数名与前端提交的表单字段一致
            @RequestParam("connectedId") Long connectedId,
            @RequestParam("type") String type) {

        Map<String, Object> response = new HashMap<>();
        // 校验文件大小
        long maxFileSize = 50 * 1024 * 1024; // 设置最大文件大小为 5MB
        if (file.getSize() > maxFileSize) {
            response.put("status", "error");
            response.put("message", "文件大小超出限制，请上传小于 50MB 的文件");
            return ResponseEntity.ok(response);
        }
        try {
            // 调用 fileService 处理上传文件
            List<String> uploadedFileUrls = fileService.uploadFiles(new MultipartFile[]{file}, connectedId, type);

            // 假设上传后返回的第一个文件是头像
            String newPictureUrl = uploadedFileUrls.isEmpty() ? "" : uploadedFileUrls.get(0);

            // 返回上传结果
            response.put("status", "success");
            response.put("message", "头像上传成功");
            response.put("newPictureUrl", newPictureUrl);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "头像上传失败，请稍后重试");
        }

        return ResponseEntity.ok(response);
    }




    // 查询所有用户
    @GetMapping("/userManagement")
    public String getUserManagementPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin_user_management";
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user, HttpSession session) {
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
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user1,
                                          HttpSession session) {
        User user = userService.loginUser(user1.getUid(), user1.getPassword());
        Map<String, Object> response = new HashMap<>();
        if (user!= null) {
            session.setAttribute("currentUser", user);
            response.put("user", user);          // 用户信息
            response.put("status", user.getStatus()); // 用户状态
            return new ResponseEntity<>(response, HttpStatus.OK);
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
//                User user2 = userService.getUserById(uid);
//                session.setAttribute("currentUser", user2);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }





    // 管理员 - 检索用户
    @GetMapping("/search")
    public String manageUsers(@RequestParam(defaultValue = "1") int pageNo,
                              @RequestParam(defaultValue = "20") int pageSize,
                              @RequestParam(required = false) Long userId,
                              @RequestParam(required = false) String UserName,
                              Model model) {
        // 使用PageHelper设置分页
        PageHelper.startPage(pageNo, pageSize);

        // 获取用户列表
        List<User> users=new ArrayList<User>(); ;
        if (userId != null) {
            // 根据 uid 查询
            users.add(userService.getUsersByUid(userId));
        } else if (UserName != null && !UserName.isEmpty()) {
            // 根据 name 查询
            users = userService.getUsersByName(UserName);
        } else {
            // 获取所有用户
            users = userService.getAllUsers();
        }

        // 使用PageInfo包装查询结果，获取分页信息
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("users", users); // 将用户列表传到前端
        // 将搜索参数传递给前端
        model.addAttribute("uid", userId);
        model.addAttribute("name", UserName);
        return "userList"; // 返回到 Thymeleaf 页面
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
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/enable")
    public ResponseEntity<Integer> enableUser(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int result = userService.enableUser(uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 管理员 - 删除用户账户
        @PostMapping("/delete")
    public ResponseEntity<Integer> deleteUser(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int period = Integer.parseInt(requestData.get("period").toString());
        int result = userService.deleteUser(uid,period);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 管理员 - 彻底删除用户账户
    @PostMapping("/deleteTotally")
    public ResponseEntity<Integer> deleteUserTotally(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = Long.parseLong(requestData.get("uid").toString());
        int result = userService.deleteUserTotally(uid);
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
    // 添加管理员
    @PostMapping("/addAdmin")
    public ResponseEntity<Integer> addAdmin(@RequestBody User newAdmin) {
        Long uid = generateUniqueUid();
        newAdmin.setIdentify(1); // 设置角色为管理员
        newAdmin.setUid(uid);
        int result = userService.addUser(newAdmin); // 假设userService处理逻辑
        return result == 1 ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private Long generateUniqueUid() {
        Long aid;
        do {
            aid = (long) (Math.random() * 1000000000);  // 随机生成一个rid
        } while (userService.isAidExist(aid));  // 检查rid是否存在
        return aid;
    }

}