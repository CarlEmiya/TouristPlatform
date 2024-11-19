package com.qdu.service.impl;

import com.qdu.mapper.UserMapper;
import com.qdu.entity.User;
import com.qdu.entity.UserExample;
import com.qdu.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        try {
            user.setCreatedAt(new Date());
            user.setLevel(1);
            user.setStatus(1);
            String encryptedPassword = DigestUtils.md5Hex(user.getPassword());
            user.setPassword(encryptedPassword);

            userMapper.insert(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public User loginUser(String userId, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        criteria.andUseridEqualTo(userId);

        String encryptedPassword = DigestUtils.md5Hex(password);
        criteria.andPasswordEqualTo(encryptedPassword);

        try {
            List<User> users = userMapper.selectByExample(example);

            if (users!= null &&!users.isEmpty()) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> searchUsers(UserExample example) {
        try {
            return userMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateUser(User user) {
        try {
            userMapper.updateByPrimaryKey(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateUserLevel(String userId, int newLevel) {
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            if (user!= null) {
                user.setLevel(newLevel);
                return updateUser(user);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateUserStatus(String userId, int newStatus) {
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            if (user!= null) {
                user.setStatus(newStatus);
                return updateUser(user);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int disableUser(String userId) {
        try {
            return updateUserStatus(userId, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteUser(String userId) {
        try {
            userMapper.deleteByPrimaryKey(userId);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isCreatedByExistingAdmin(User admin) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(admin.getUserId());
        criteria.andIdentifyEqualTo(1);
        try {
            List<User> users = userMapper.selectByExample(example);
            return !users.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}