package com.qdu.service;

import com.qdu.entity.Comment;
import java.util.List;

public interface CommentService {

	// 发布评论
	int addComment(Comment comment);

	// 根据关联对象ID和用户ID获取评论列表

	List<Comment> getCommentsByAssociatedIdAndUserIdandAssociatedType(String associatedId, String userId, String associatedType);

	// 根据评论ID删除评论
	int deleteCommentById(String commentId);

	// 对评论进行点赞
	int likeComment(String commentId);

	// 获取某条评论的点赞数
	int getCommentAgreeCount(String commentId);
}