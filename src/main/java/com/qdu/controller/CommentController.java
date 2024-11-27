package com.qdu.controller;

import com.qdu.entity.Comment;
import com.qdu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 发布评论
    @PostMapping("/add")
    public ResponseEntity<Integer> addComment(@RequestBody Comment comment) {
        int result = commentService.addComment(comment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 根据关联对象ID、用户ID和关联对象类型获取评论列表
    @GetMapping("/list")
    public ResponseEntity<List<Comment>> getCommentsByAssociatedIdAndUidandAssociatedType(
            @RequestParam("aid") Long connectid,
            @RequestParam("uid") Long uid,
            @RequestParam("type") String type) {
        List<Comment> comments = commentService.getCommentsByAssociatedIdAndUidandAssociatedType(connectid, uid, type);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // 根据评论ID删除评论
    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteCommentById(@RequestParam("cid") Long cid) {
        int result = commentService.deleteCommentById(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 对评论进行点赞
    @PostMapping("/like")
    public ResponseEntity<Integer> likeComment(@RequestParam("cid") Long cid) {
        int result = commentService.likeComment(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 获取某条评论的点赞数
    @GetMapping("/agreeCount")
    public ResponseEntity<Integer> getCommentAgreeCount(@RequestParam("cid") Long cid) {
        int result = commentService.getCommentAgreeCount(cid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}