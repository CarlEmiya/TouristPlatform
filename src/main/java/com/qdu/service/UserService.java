package com.qdu.service;

import com.qdu.entity.Comment;
import com.qdu.entity.UserExample;
import org.springframework.stereotype.Service;
import com.qdu.entity.User;
import java.util.List;

/**
 * CommentService接口，定义学生相关的业务
 * 
 * @author Anna
 *
 */

public interface UserService {
	// 用户注册
	int addUser(User user);

	// 用户登录
	User loginUser(String userId, String password);

	// 根据条件查询用户信息
	List<User> searchUsers(UserExample example);

	// 更新用户信息
	int updateUser(User user);

	// 修改用户等级
	int updateUserLevel(String userId, int newLevel);

	// 修改用户状态
	int updateUserStatus(String userId, int newStatus);

	// 禁用用户
	int disableUser(String userId);

	// 删除用户账户
	int deleteUser(String userId);
}
