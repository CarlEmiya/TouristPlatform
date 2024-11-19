package com.qdu.service;

import com.qdu.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CommentService接口，定义学生相关的业务
 * 
 * @author Anna
 *
 */
@Service
public interface ReportService {
	/**
	 ** 根据学号获取一个学生的所有信息
	 *
	 * @param id 字符串表示的学号
	 * @return 一个Comment对象，包含学生信息
	 */
	Comment getCommentById(String id);

	/**
	 ** 获取所有学生的列表
	 *
	 * @return 一个列表，包含所有学生的信息
	 */
	List<Comment> getCommentList();

	/**
	 ** 获取所有学生的列表
	 *
	 * @param batchName 字符串表示的班级名称
	 * @return 一个列表，包含该班级所有学生的信息
	 */
	List<Comment> getCommentListByBatchName(String batchName);

	/**
	 * 添加一个新学生
	 *
	 * @param Comment 一个包含新学生信息的Comment对象
	 */
	void addComment(Comment Comment);

	/**
	 ** 更新一个新学生
	 *
	 * @param Comment 一个包含新学生信息的Comment对象
	 */
	void updateComment(Comment Comment);

	/**
	 * *根据学号删除一个学生
	 *
	 * @param id 字符串表示的学号
	 */
	void deleteComment(String id);
}
