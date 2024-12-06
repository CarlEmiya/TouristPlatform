package com.travel.controller;

import com.travel.entity.Comment;
import com.travel.entity.Report;
import com.travel.entity.User;
import com.travel.mapper.UserMapper;
import com.travel.service.CommentService;
import com.travel.service.impl.CommentServiceImpl;
import com.travel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//@Controller
@RestController
//@ResponseBody
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;

    // 发布评论
    @PostMapping("/add")
    public ResponseEntity<Integer> addComment(@RequestBody Comment comment) {
        int result = commentService.addComment(comment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 根据关联对象ID、用户ID和关联对象类型获取评论列表
    @GetMapping("/list")
    public ResponseEntity<List<Comment>> getCommentsByAssociatedIdAndUidandAssociatedType(
            @RequestBody Map<String, Object> requestData, HttpSession session) {
        Long connectid = (Long) requestData.get("connectid");
        Long uid = (Long) requestData.get("uid");
        String type = (String) requestData.get("type");
        List<Comment> comments = commentService.getCommentsByAssociatedIdAndUidandAssociatedType(connectid, uid, type);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * 根据用户ID获取评论列表，支持分页
     * @param uid
     * @param pageNo
     * @param pageSize
     * @param session
     * @return
     */
    // 根据用户ID获取评论列表，支持分页
    @GetMapping("/listByUid")
    public ResponseEntity<PageInfo<Comment>> getCommentsByUid(
            @RequestParam Long uid,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            HttpSession session) {

        // 启用分页
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> comments = commentService.getCommentsByUid(uid);

        // 包装分页信息
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);

        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }



//根据comment的uid获取用户信息
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserByUid(
            @RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = (Long) requestData.get("uid");
        User user = commentService.getUserByUid(uid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // 获取所有评论后批量获取用户信息
    @GetMapping("/getUsers")
    public ResponseEntity<Map<Long, User>> getUsersByUids(@RequestParam List<Long> uids) {
        List<User> users = userServiceImpl.getUsersByUids(uids);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getUid, user -> user));
        return new ResponseEntity<>(userMap, HttpStatus.OK);
    }





    // 根据评论ID删除评论
    @DeleteMapping("/deleteByCid")
    public ResponseEntity<Integer> deleteCommentById(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = Long.valueOf(requestData.get("cid").toString());  // 确保获取的数据是 Long 类型
        int result = commentService.deleteCommentById(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 根据用户ID删除该用户的所有评论
    @DeleteMapping("/deleteAllByUid")
    public ResponseEntity<Map<String, String>> deleteAllCommentByUid(@RequestParam Long uid, HttpSession session) {
        boolean success = commentService.deleteAllCommentByUid(uid);
        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("message", "success");
        } else {
            response.put("message", "failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 对评论进行点赞
    @PostMapping("/like")
    public ResponseEntity<?> likeComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
        System.out.println("接收到的参数：" + requestData);
        Long cid = Long.valueOf(requestData.get("cid").toString()); // 转换为 Long 类型
        Long uid = Long.valueOf(requestData.get("uid").toString()); // 转换为 Long 类型
        int result = commentService.likeComment(cid, uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 对评论进行点赞
    @PostMapping("/unlike")
    public ResponseEntity<?> unLikeComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
//        System.out.println("接收到的参数：" + requestData);
        Long cid = Long.valueOf(requestData.get("cid").toString()); // 转换为 Long 类型
        Long uid = Long.valueOf(requestData.get("uid").toString()); // 转换为 Long 类型
        int result = commentService.unLikeComment(cid, uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 获取某条评论的点赞数
    @GetMapping("/agreeCount")
    public ResponseEntity<Integer> getCommentAgreeCount(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = (Long) requestData.get("cid");
        int result = commentService.getCommentAgreeCount(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<Integer> reportComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long reporter = (Long) requestData.get("reporter");
        Long reported = (Long) requestData.get("cid");
        String type = "comment"; // 默认是评论类型
        String category = (String) requestData.get("category"); // 举报类别
        String description = (String) requestData.get("reason"); // 举报理由

        int result = commentService.reportComment(reporter, reported, type, category, description);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PutMapping("/update")
    public ResponseEntity<Integer> editComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = (Long) requestData.get("cid");
        String content = (String) requestData.get("content");

        // 更新评论
        Comment comment = new Comment();
        comment.setCid(cid);
        comment.setContent(content);
        int result = commentService.updateComment(comment);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}