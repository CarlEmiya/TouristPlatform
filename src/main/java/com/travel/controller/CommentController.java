package com.travel.controller;

import com.travel.entity.Comment;
import com.travel.entity.Report;
import com.travel.service.CommentService;
import com.travel.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
//@Controller
@RestController
//@ResponseBody
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

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

    @GetMapping("/listByUid")
    public ResponseEntity<List<Comment>> getCommentsByUid(
            @RequestParam Long uid, HttpSession session) {  // 使用 @RequestParam 获取查询参数
        List<Comment> comments = commentService.getCommentsByUid(uid);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    // 根据评论ID删除评论
    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteCommentById(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = (Long) requestData.get("cid");
        int result = commentService.deleteCommentById(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 根据用户ID删除评论
    @DeleteMapping("/deleteAllByUid")
    public ResponseEntity<Integer> deleteAllCommentByUid(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long uid = (Long) requestData.get("uid");
        int result = commentService.deleteAllCommentByUid(uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 对评论进行点赞
    @PostMapping("/like")
    public ResponseEntity<Integer> likeComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = (Long) requestData.get("cid");
        Long uid = (Long) requestData.get("uid");
        int result = commentService.likeComment(cid,uid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 获取某条评论的点赞数
    @GetMapping("/agreeCount")
    public ResponseEntity<Integer> getCommentAgreeCount(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long cid = (Long) requestData.get("cid");
        int result = commentService.getCommentAgreeCount(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 举报评论
    @PostMapping("/report")
    public ResponseEntity<Integer> reportComment(@RequestBody Map<String, Object> requestData, HttpSession session) {
        Long reporter = (Long) requestData.get("reporter");
        Long reported = (Long) requestData.get("cid");
        String type = "comment";
        String category = (String) requestData.get("category");
        String description = (String) requestData.get("reason");
        int result = commentService.reportComment(reporter, reported, type, category, description);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 编辑评论
    @PutMapping("/update")
    public ResponseEntity<Integer> editComment(@RequestBody Comment comment, HttpSession session) {
        int result = commentService.updateComment(comment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}