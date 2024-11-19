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
    public ResponseEntity<List<Comment>> getCommentsByAssociatedIdAndUserIdandAssociatedType(
            @RequestParam("associatedId") String associatedId,
            @RequestParam("userId") String userId,
            @RequestParam("associatedType") String associatedType) {
        List<Comment> comments = commentService.getCommentsByAssociatedIdAndUserIdandAssociatedType(associatedId, userId, associatedType);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // 根据评论ID删除评论
    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteCommentById(@RequestParam("commentId") String commentId) {
        int result = commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 对评论进行点赞
    @PostMapping("/like")
    public ResponseEntity<Integer> likeComment(@RequestParam("commentId") String commentId) {
        int result = commentService.likeComment(commentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 获取某条评论的点赞数
    @GetMapping("/agreeCount")
    public ResponseEntity<Integer> getCommentAgreeCount(@RequestParam("commentId") String commentId) {
        int result = commentService.getCommentAgreeCount(commentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}