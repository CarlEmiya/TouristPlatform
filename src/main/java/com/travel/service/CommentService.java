package com.travel.service;

import com.travel.entity.Comment;
import java.util.List;

public interface CommentService {

	// 发布评论
	int addComment(Comment comment);

	// 根据关联对象ID和用户ID获取评论列表

	List<Comment> getCommentsByAssociatedIdAndUidandAssociatedType(Long connectid, Long uid, String type);

	// 根据评论ID删除评论
	int deleteCommentById(Long cid);


	int likeComment(Long cid, Long uid);

	// 获取某条评论的点赞数
	int getCommentAgreeCount(Long cid);

	int reportComment(Long reporter, Long reported, String type, String category, String description);
}